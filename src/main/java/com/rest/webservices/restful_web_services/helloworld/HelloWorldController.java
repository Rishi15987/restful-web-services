package com.rest.webservices.restful_web_services.helloworld;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
public class HelloWorldController {
    private MessageSource messageSource;
    public HelloWorldController( MessageSource messageSource){
        this.messageSource=messageSource;
    }
    @GetMapping(value = "/hello")
    public String helloworld(){
        return "Hello World";
    }
    @GetMapping(value = "/hello-bean")
    public HelloWOrldBean helloworldbean(){
        return new HelloWOrldBean("Hello World");
    }
    @GetMapping(value = "/hello-path/{name}")
    public HelloWOrldBean helloworldpath(@PathVariable String name){
        return new HelloWOrldBean(String.format("Hello World, %s", name));
    }
    @GetMapping(value = "/hello-internationalized")
    public String hellointernationalized(){
        Locale locale= LocaleContextHolder.getLocale();
        return messageSource.getMessage("good.morning.message",null,"Default Message",locale);
    }
}
