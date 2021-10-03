package ru.mirea.coursework.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailService {


    @Autowired
    private JavaMailSender emailSender;

    public void sendSimpleMessage(String text) {
        String to = "LighterST@yandex.ru";
        String subject = "Pls work";
//        String text = "If you see this, you're a good fella";
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("LighterST@yandex.ru");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);
    }
}