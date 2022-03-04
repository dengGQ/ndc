package com.ndc.channel;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@MapperScan("com.ndc.channel.mapper")
@EnableAsync
@EnableCaching
@SpringBootApplication
public class ChannelApplication extends SpringBootServletInitializer {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {

            @Override
            public void addCorsMappings(final CorsRegistry registry) {
                registry.addMapping("/")
                        .allowedHeaders("Content-Type, X-Requested-With, X-PINGOTHER, X-File-Name, Cache-Control")
                        .allowedMethods("POST,PUT,GET,OPTIONS,DELETE").allowedOrigins("*");
            }
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(ChannelApplication.class, args);
    }

}
