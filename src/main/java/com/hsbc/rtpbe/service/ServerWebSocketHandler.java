package com.hsbc.rtpbe.service;

import com.google.gson.Gson;
import com.hsbc.rtpbe.model.PaymentInvoice;
import com.hsbc.rtpbe.model.PaymentRequest;
import com.sun.jdi.LongType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.SubProtocolCapable;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import org.springframework.web.util.HtmlUtils;

import java.io.IOException;
import java.time.LocalTime;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

public class ServerWebSocketHandler extends TextWebSocketHandler implements SubProtocolCapable {

    private static final Logger logger = LoggerFactory.getLogger(ServerWebSocketHandler.class);

    private final Set<WebSocketSession> sessions = new CopyOnWriteArraySet<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        logger.info("Server new connection: {}", session.getId());
        sessions.add(session);

        TextMessage message = new TextMessage("Connected TxID: {}" + session.getId());
        logger.info("Server sends: {}", message);
        session.sendMessage(message);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        logger.info("Server connection closed: {}", status);
        sessions.remove(session);
    }

    @Scheduled(fixedRate = 2000)
    void sendPeriodicMessages() throws IOException {
        for (WebSocketSession session : sessions) {
            if (session.isOpen()) {
                String mess = "in-progress " + LocalTime.now();
                logger.info("Server: session {} is {}", session.getId(), mess);
                session.sendMessage(new TextMessage(mess));
            }
        }
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String request = message.getPayload();
        logger.info("Server received: {}", request);
        Gson gson=new Gson();
        PaymentRequest payment = gson.fromJson(request, PaymentRequest.class);

        if (payment.getAmount().compareTo(0L) == 0) {
            session.sendMessage(new TextMessage("close session"));
            session.close();
        }

        PaymentInvoice invoice = new PaymentInvoice();
        invoice.setId(session.getId());
        invoice.setDetail(payment);

        String response = String.format("Payment invoice:  '%s'", HtmlUtils.htmlEscape(invoice.toString()));
        logger.info("Server sends: {}", response);
        session.sendMessage(new TextMessage(response));

    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) {
        logger.info("Server transport error: {}", exception.getMessage());
    }

    @Override
    public List<String> getSubProtocols() {
        return Collections.singletonList("subprotocol.demo.websocket");
    }
}