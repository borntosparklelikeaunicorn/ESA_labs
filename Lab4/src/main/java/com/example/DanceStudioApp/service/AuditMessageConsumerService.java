package com.example.DanceStudioApp.service;

import com.example.DanceStudioApp.models.AuditEntity;
import com.example.DanceStudioApp.models.AuditMessage;
import com.example.DanceStudioApp.repository.AuditRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuditMessageConsumerService {
    private final AuditRepository auditRepository;

    @Transactional
    @JmsListener(destination = "${application.topic.audit}")
    public void receive(AuditMessage auditMessage) {
        AuditEntity audit = new AuditEntity();
        audit.setId(UUID.randomUUID());
        audit.setEvent(auditMessage.getEvent());
        audit.setTable(auditMessage.getTable());
        audit.setDatetime(auditMessage.getDatetime());
        audit.setInfo(auditMessage.getInfo());

        auditRepository.save(audit);
    }
}
