package com.example.DanceStudioApp.models;

import lombok.Data;

@Data
public class DeleteMessage extends AuditMessage {

    private Object deletedObject;

    @Override
    public String getInfo() {
        return "Объект был удален %s".formatted(deletedObject);
    }

}