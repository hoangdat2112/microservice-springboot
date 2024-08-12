package org.example.micro.service.imlp;



import jakarta.mail.MessagingException;
import lombok.extern.slf4j.Slf4j;
import org.example.event.OrderPlacedEvent;
import org.example.micro.dto.ClientSdi;
import org.example.micro.dto.DataMailDTO;
import org.example.micro.service.ClientService;
import org.example.micro.service.MailService;
import org.example.micro.utils.Const;
import org.example.micro.utils.DataUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class ClientServiceImpl implements ClientService {
    @Autowired
    private MailService mailService;

    @Override
    public Boolean create(ClientSdi sdi) {
        try {
            DataMailDTO dataMail = new DataMailDTO();

            dataMail.setTo(sdi.getEmail());
            dataMail.setSubject(Const.SEND_MAIL_SUBJECT.CLIENT_REGISTER);

            Map<String, Object> props = new HashMap<>();
            props.put("name", sdi.getName());
            props.put("username", sdi.getUsername());
            props.put("password", DataUtils.generateTempPwd(6));
            dataMail.setProps(props);

            log.info("Sending email to {}", sdi.getEmail());
            mailService.sendHtmlMail(dataMail, Const.TEMPLATE_FILE_NAME.CLIENT_REGISTER);
            return true;
        } catch (MessagingException exp) {
            log.error("Failed to send email", exp);
        }
        return false;
    }

    public Boolean createMail(String event ) {
        try {
            DataMailDTO dataMail = new DataMailDTO();

            dataMail.setTo(event);
            dataMail.setSubject(Const.SEND_MAIL_SUBJECT.CLIENT_REGISTER);

            Map<String, Object> props = new HashMap<>();
            props.put("name","hoang van dat");
            props.put("phoneNumber","09999999");
            props.put("price", 2);
            dataMail.setProps(props);


            mailService.sendHtmlMail(dataMail, Const.TEMPLATE_FILE_NAME.CLIENT_REGISTER);
            return true;
        } catch (MessagingException exp) {
            log.error("Failed to send email", exp);
        }
        return false;
    }
}

