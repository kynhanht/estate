package com.laptrinhjavaweb.exception.handler;

import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

public class ErrorMessage implements Serializable {

    private HttpStatus status;

    private List<String> messages;


    public ErrorMessage() {
    }

    public ErrorMessage(HttpStatus status, List<String> messages) {
        this.status = status;
        this.messages = messages;
    }

    public ErrorMessage(HttpStatus status, String message) {
        this.status = status;
        this.messages = Arrays.asList(message);
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }
}
