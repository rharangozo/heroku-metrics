package com.hr.pr.config;

import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.List;

@org.springframework.context.annotation.Configuration
class Configuration extends WebMvcConfigurationSupport {

    protected void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        //TODO: replace it with supported converter
        MappingJacksonHttpMessageConverter jacksonHttpMessageConverter = new MappingJacksonHttpMessageConverter();
        converters.add(jacksonHttpMessageConverter);
        addDefaultHttpMessageConverters(converters);
    }

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/*");
    }
}