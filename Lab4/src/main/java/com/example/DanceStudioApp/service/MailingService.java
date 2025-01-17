package com.example.DanceStudioApp.service;

import com.example.DanceStudioApp.models.AuditMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailingService {
    private static final String MESSAGE_TEMPLATE = "Изменения в таблице:\n %s в %s";
    private static final String SUBJECT_TEMPLATE = "Изменения в таблице: %s";



    private final JavaMailSender emailSender;

    public void sendSimpleEmail(String toAddress, AuditMessage auditMessage) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("maria.makaaarova@gmail.com");
        simpleMailMessage.setTo(toAddress);
        simpleMailMessage.setSubject(SUBJECT_TEMPLATE.formatted(auditMessage.getTable()));
        simpleMailMessage.setText(MESSAGE_TEMPLATE.formatted(auditMessage.getInfo(), auditMessage.getDatetime()));
        emailSender.send(simpleMailMessage);
    }

}