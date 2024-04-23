package com.beautyLifeShop.ecom.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {


    @Bean
    public WebMvcConfigurer corsConfiguration(){
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
               registry.addMapping("/**")
                       .allowedOrigins("http://localhost:4200/"," https://www.sandbox.paypal.com/")
                       .allowedMethods(HttpMethod.GET.name(),
                               HttpMethod.POST.name(),HttpMethod.PUT.name(), HttpMethod.DELETE.name(), HttpMethod.OPTIONS.name() )
                       .allowedHeaders("*").allowCredentials(true);;
            }
        };
    }
}
