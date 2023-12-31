package com.example.reservasapi.configuration;

import com.example.reservasapi.service.DBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.text.ParseException;

@Configuration
@Profile({"test", "prod"})
public class TestConfig {

    @Autowired
    private DBService dbService;

    @Bean
    public boolean instancia() throws ParseException {
        this.dbService.instanciaDB();
        return true;
    }
}
