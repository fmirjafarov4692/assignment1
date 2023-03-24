package com.example.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@RequiredArgsConstructor
@Configuration
public class DatabaseIntializor {
    private final JdbcTemplate jdbcTemplate;

    @Bean
    CommandLineRunner loadDatabase( ) {
        return args -> {
            jdbcTemplate.execute("INSERT INTO users (lastname, firstname, email, username, birth_date, role)\n" +
                                         "VALUES ('Laurent', 'GINA', 'laurentgina@mail.com', 'laurent','2007-12-03', " +
                                         "'ADMIN')");

            jdbcTemplate.execute("INSERT INTO users (lastname, firstname, email, username, birth_date, role)\n" +
                                         "VALUES ('Anjeli', 'Jonu', 'anjeli@mail.com', 'anj','2017-11-04', " +
                                         "'USER')");

            jdbcTemplate.execute("INSERT INTO users (lastname, firstname, email, username, birth_date, role)\n" +
                                         "VALUES ('Bred', 'Pit', 'bred1@mail.com', 'pit','2011-10-01', " +
                                         "'USER')");

            jdbcTemplate.execute("INSERT INTO accounts (number, balance, currency, status, user_id)\n" +
                                         "VALUES (12, 2222, 'AZN', 'NEW','1')");

            jdbcTemplate.execute("INSERT INTO accounts (number, balance, currency, status, user_id)\n" +
                                         "VALUES (152, 23522, 'USD', 'PAID','2')");

            jdbcTemplate.execute("INSERT INTO accounts (number, balance, currency, status, user_id)\n" +
                                         "VALUES (1542, 4235222, 'RUB', 'CANCEL','2')");

            jdbcTemplate.execute("INSERT INTO accounts (number, balance, currency, status, user_id)\n" +
                                         "VALUES (15422, 42352222, 'RUB', 'CANCEL','3')");

        };
    }

}
