package org.pl.serwis_logownia.config;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfig {
    @Bean
    public static DataSource getDataSource() {
        return DataSourceBuilder.create()
                .driverClassName("com.mysql.cj.jdbc.Driver")
                .url("jdbc:mysql://192.168.1.29:3306/lotnisko_baza")
                .username("root")
                .password("haslo")
                .build();
    }

    @Bean
    public static JdbcTemplate getConfiguredTemplate() {
        return new JdbcTemplate(getDataSource());
    }
}
