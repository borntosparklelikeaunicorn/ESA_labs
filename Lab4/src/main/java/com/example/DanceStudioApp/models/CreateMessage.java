package com.example.DanceStudioApp.models;

import lombok.Data;

@Data
public class CreateMessage extends AuditMessage {

    private Object createdObject;

    @Override
    public String getInfo() {
        return "Объект создан %s".formatted(createdObject);
    }

}
