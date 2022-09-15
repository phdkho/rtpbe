package com.hsbc.rtpbe.config;

import com.hsbc.rtpbe.service.ServerWebSocketHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.*;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(webSocketHandler(), "/payment");
    }

    @Bean
    public WebSocketHandler webSocketHandler() {
        return new ServerWebSocketHandler();
    }
}