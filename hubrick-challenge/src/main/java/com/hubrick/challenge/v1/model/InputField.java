package com.hubrick.challenge.v1.model;

/**
 * Created by kobi on 07/03/16.
 */
public enum InputField {
    USER_ID("User Id"),
    FIRST_NAME("First Name"),
    LAST_NAME("Last Name"),
    EMAIL("Email"),
    PASSWORD("Password");

    private String fieldName;

    private InputField(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldName() {
        return this.fieldName;
    }
}
