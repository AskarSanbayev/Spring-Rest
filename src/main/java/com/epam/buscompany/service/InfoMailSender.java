package com.epam.buscompany.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

public class InfoMailSender {

    @Autowired
    private JavaMailSender mailSender;

    private static final String ADMIN_MAIL ="sanbayev54@gmail.com" ;
    private static final String BOSS_MAIL = "bossbuscompany@gmail.com";


    public void sendMail(String subject, String msg) {

        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom(ADMIN_MAIL);
        message.setTo(BOSS_MAIL);
        message.setSubject(subject);
        message.setText(msg);

        mailSender.send(message);
    }
}
