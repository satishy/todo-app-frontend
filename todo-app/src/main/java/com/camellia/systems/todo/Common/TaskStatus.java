package com.camellia.systems.todo.Common;

public enum TaskStatus {

    PENDING("PENDING","Pending"),
    INPROGRESS("INPROGRESS","In Progress"),
    REVIEW("REVIEW","Review"),
    TESTING("TESTING","Testing"),
    COMPLETED("COMPLETED","Completed");

    private String name;
    private String value;

    TaskStatus(String name, String value) {
        this .value = value;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
