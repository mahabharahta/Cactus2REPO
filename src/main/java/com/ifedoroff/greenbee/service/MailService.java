package com.ifedoroff.greenbee.service;

/**
 * Created by Rostik on 14.05.2017.
 */
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailService {

    public static void send(String to, String msg) {

        final String username = "rostik.nikolaenko@gmail.com";
        final String password = "";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("rostik.nikolaenko@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse("yankobogdan@gmail.com"));
            message.setSubject("Notificatewion");
            message.setText("Hellof");

            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            System.out.println(e);
        }
    }
}