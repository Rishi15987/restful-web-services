package com.rest.webservices.restful_web_services.Versioning;

public class PersonV1 {
    private String name;
    public PersonV1(String name) {
        this.name=name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "PersonV1{" +
                "name='" + name + '\'' +
                '}';
    }
}
