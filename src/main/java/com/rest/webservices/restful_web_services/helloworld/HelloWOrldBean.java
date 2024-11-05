package com.rest.webservices.restful_web_services.helloworld;

public class HelloWOrldBean {
    private String message;
    public HelloWOrldBean(String message) {
        this.message=message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "HelloWOrldBean{" +
                "message='" + message + '\'' +
                '}';
    }
}
