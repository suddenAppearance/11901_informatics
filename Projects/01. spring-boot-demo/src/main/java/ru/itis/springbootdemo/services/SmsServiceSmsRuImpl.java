package ru.itis.springbootdemo.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
public class SmsServiceSmsRuImpl implements SmsService {
    @Autowired
    private RestTemplate restTemplate;
    @Value("${sms.ru.url}")
    private String url;
    @Value("${sms.ru.api_id}")
    private String api_id;

    @Override
    public void sendSms(String to, String text) {
        String url = String.format("%s?api_id=%s&to=%s&msg=%s", this.url, this.api_id, to, text);
        log.info(url);
        restTemplate.getForObject(url, String.class);
    }
}
