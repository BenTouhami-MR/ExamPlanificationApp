package com.exam.core.web.models;

public class ErrorResponse {

    private String message;
    private String url;

    public ErrorResponse(String message, String url) {
        this.message = message;
        this.url = url;
    }

    // Getters and setters
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
