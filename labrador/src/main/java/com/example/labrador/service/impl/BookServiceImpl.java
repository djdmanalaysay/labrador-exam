package com.example.labrador.service.impl;

import com.example.labrador.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private RestTemplate restTemplate;

    @Value("${external.api.url}")
    private String externalApiUrl;

    public String getExternalApiData() {
        return restTemplate.getForObject(externalApiUrl, String.class);
    }
}
