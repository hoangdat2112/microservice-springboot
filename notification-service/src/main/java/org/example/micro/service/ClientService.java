package org.example.micro.service;


import org.example.event.OrderPlacedEvent;
import org.example.micro.dto.ClientSdi;
import org.springframework.stereotype.Service;

@Service
public interface ClientService {
    Boolean create(ClientSdi sdi);
    Boolean createMail(String event );
}
