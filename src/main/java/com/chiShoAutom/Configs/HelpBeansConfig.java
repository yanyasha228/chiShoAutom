package com.chiShoAutom.Configs;


import com.chiShoAutom.Handlers.ErrorHandlers.RestTemplateResponseErrorHandler;
import org.modelmapper.ModelMapper;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableScheduling
@ComponentScan
@EnableAsync
@PropertySource("classpath:rest_prom_api.properties")
@PropertySource("classpath:rest_bitrix_api.properties")
@PropertySource("classpath:co2.properties")
@PropertySource("classpath:rest_novapochta_api.properties")
@PropertySource("classpath:receipt.properties")
public class HelpBeansConfig {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplateBuilder().errorHandler(new RestTemplateResponseErrorHandler()).build();
    }


}
