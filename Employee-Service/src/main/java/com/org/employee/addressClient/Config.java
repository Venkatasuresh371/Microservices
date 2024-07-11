package com.org.employee.addressClient;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class Config
{
//   @Bean
//    public RestTemplate restTemplateBean() {
//        return new RestTemplate();
//    }

    @Bean
    public WebClient webClientBean()
    {
        return WebClient.builder().build();
    }
}
