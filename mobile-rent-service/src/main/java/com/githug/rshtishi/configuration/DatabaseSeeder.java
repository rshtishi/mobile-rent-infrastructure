package com.githug.rshtishi.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;

import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

@Component
public class DatabaseSeeder implements ApplicationRunner {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        boolean dataExists = checkIfDataExists();
        if(!dataExists){
            initializeDatabase();
        }
    }

    private boolean checkIfDataExists() {
        int count = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM phone", Integer.class);
        return count > 0;
    }

    private void initializeDatabase() throws Exception {
        ClassPathResource resource = new ClassPathResource("data.sql");
        InputStreamReader reader = new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8);
        String sqlScript = FileCopyUtils.copyToString(reader);
        // Execute the SQL script
        jdbcTemplate.execute(sqlScript);
    }
}
