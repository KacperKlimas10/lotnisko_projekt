package org.pl.serwis_logowania.config;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfig {

    static String ip = "192.168.8.67";

    @Bean
    public static DataSource getDataSource() {
        return DataSourceBuilder.create()
                .driverClassName("com.mysql.cj.jdbc.Driver")
                .url("jdbc:mysql://%s:3306/lotnisko_baza".formatted(ip))
                .username("root")
                .password("haslo")
                .build();
    }

    @Bean
    public static JdbcTemplate getConfiguredTemplate() {
        return new JdbcTemplate(getDataSource());
    }
}
