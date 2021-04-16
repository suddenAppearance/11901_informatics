package ru.itis.springbootdemo.services;

public interface SmsService {
    void sendSms(String to, String text);
}
