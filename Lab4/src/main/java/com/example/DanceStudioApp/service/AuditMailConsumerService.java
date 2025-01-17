package com.example.DanceStudioApp.service;

import com.example.DanceStudioApp.models.AuditMessage;
import com.example.DanceStudioApp.repository.MailingRulesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuditMailConsumerService {
    private final MailingRulesRepository mailingRulesRepository;

    private final MailingService mailingService;

    @JmsListener(destination = "${application.topic.audit}")
    public void receive(AuditMessage auditMessage) {
        List<String> emails = mailingRulesRepository.findEmailByTableName(auditMessage.getTable());
        emails.forEach(email -> mailingService.sendSimpleEmail(email, auditMessage));
    }
}
