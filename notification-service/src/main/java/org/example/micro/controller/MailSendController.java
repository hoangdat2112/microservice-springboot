package org.example.micro.controller;

import lombok.RequiredArgsConstructor;

import org.example.event.OrderPlacedEvent;
import org.example.micro.service.ClientService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class MailSendController {


    private final ClientService clientService;

    @KafkaListener(topics = "notificationTopic", groupId = "my-group-id")
    public void listen(String event) {
      clientService.createMail(event);
    }

}
