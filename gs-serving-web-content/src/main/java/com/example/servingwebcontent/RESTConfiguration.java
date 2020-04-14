package com.example.servingwebcontent;

import com.example.servingwebcontent.model.RESTServer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;


@Configuration
@PropertySource("classpath:application.properties")
public class RESTConfiguration
{

    @Bean
    public RESTServer restServer(Environment env)
    {
        return new RESTServer(env.getProperty("rest.user"),
                env.getProperty("rest.password"),
                env.getProperty("rest.host"));
    }
}
