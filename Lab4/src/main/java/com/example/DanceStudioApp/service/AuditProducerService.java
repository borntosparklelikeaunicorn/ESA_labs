package com.example.DanceStudioApp.service;

import com.example.DanceStudioApp.models.AuditEvent;
import com.example.DanceStudioApp.models.AuditMessage;
import com.example.DanceStudioApp.models.CreateMessage;
import com.example.DanceStudioApp.models.DeleteMessage;
import com.example.DanceStudioApp.utils.EventLogger;
import jakarta.persistence.Table;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuditProducerService implements EventLogger {

    private final JmsTemplate jmsTemplate;

    @Value("${application.topic.audit}")
    private String topicName;

    @Override
    public void log(Object entity, AuditEvent event) {
        AuditMessage auditMessage = null;
        switch (event) {
            case CREATE -> {
                CreateMessage createMessage = new CreateMessage();
                createMessage.setCreatedObject(entity);
                auditMessage = createMessage;
            }
            case DELETE -> {
                DeleteMessage deleteMessage = new DeleteMessage();
                deleteMessage.setDeletedObject(entity);
                auditMessage = deleteMessage;
            }
        }

        auditMessage.setDatetime(LocalDateTime.now());
        auditMessage.setEvent(event);

        auditMessage.setTable(entity.getClass().getSimpleName().toLowerCase());

        jmsTemplate.convertAndSend(topicName, auditMessage);
    }


}