package com.example.DanceStudioApp.utils;

import com.example.DanceStudioApp.models.AuditEvent;

public interface EventLogger {

    void log(Object entity, AuditEvent event);

}
