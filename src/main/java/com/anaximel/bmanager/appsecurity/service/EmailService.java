package com.anaximel.bmanager.appsecurity.service;

import com.sun.mail.smtp.SMTPTransport;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import static com.anaximel.bmanager.appsecurity.constant.EmailConstant.*;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

@Service
public class EmailService {

    public void sendNewPasswordEmail(String firstName,String password,String email) throws MessagingException {
        Message message = createEmail(firstName,password,email);
        SMTPTransport smtpTransport = (SMTPTransport) getEmailSession().getTransport(SIMPLE_EMAIL_TRANSFER_PROTOCOL);
        smtpTransport.connect(GMAIL_SMTP_SERVER,USERNAME, PASSWORD);
        smtpTransport.sendMessage(message,message.getAllRecipients());
        smtpTransport.close();

    }
    private Message createEmail (String firstName,String password,String email) throws MessagingException {
        Message message = new MimeMessage(getEmailSession());
        message.setFrom(new InternetAddress(FROM_EMAIL));
        message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(email,false));
        message.setSubject(EMAIL_SUBJECT);
        message.setText("hello" + firstName + "Your account password is:" + password);
        message.setSentDate(new Date());
        message.saveChanges();
        return message;
    }

    private Session getEmailSession(){
        Properties properties = System.getProperties();
        properties.put(SMTP_HOST,GMAIL_SMTP_SERVER);
        properties.put(SMTP_AUTH,true);
        properties.put(SMTP_STARTTLS_ENABLE,true);
        properties.put(SMTP_STARTTLS_REQUIRED,true);
        return Session.getInstance(properties,null);

    }


}
