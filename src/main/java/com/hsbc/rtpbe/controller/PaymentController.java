package com.hsbc.rtpbe.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class PaymentController {

    @GetMapping(value = "/check-health")
    String index(Model model) {
        return "OK";
    }

    @GetMapping(value = "/test")
    String test1() {
        return "OK1213123";
    }


    @MessageMapping("/pay")
    @SendToUser("/topic")
    public String greeting(@DestinationVariable String userId, final String message) throws Exception {
        System.out.println(userId);
        System.out.println(message);
        return message;
    }

    @SubscribeMapping("/topic/socket")
    public String test() {
        return ">>>>>";
    }

}
