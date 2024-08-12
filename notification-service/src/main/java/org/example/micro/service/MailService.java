package org.example.micro.service;

import jakarta.mail.MessagingException;
import org.example.micro.dto.DataMailDTO;

public interface MailService {
    void sendHtmlMail(DataMailDTO dataMail, String templateName) throws MessagingException;

}
