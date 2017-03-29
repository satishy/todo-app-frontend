package com.camellia.systems.todo.domain;

import java.sql.Timestamp;

public class BasicEntity {

    protected Integer id;

    private Timestamp createdDate;

    private Timestamp updatedDate;

    private String status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public Timestamp getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Timestamp updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getTaskStatus() {
        return status;
    }

    public void setTaskStatus(String taskStatus) {
        this.status = taskStatus;
    }
}
