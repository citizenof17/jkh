package com.jkh.BE;

import com.codahale.metrics.MetricRegistry;
import com.jkh.BE.clients.AuthClient;
import com.jkh.BE.database.*;
import com.jkh.utils.NonFollowedRedirectsClient;
import com.mysql.cj.jdbc.MysqlDataSource;
import feign.Feign;
import feign.Logger;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.slf4j.Slf4jLogger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class ConfigurationBE {

    @Value("${server.address:http://localhost:8080}")
    private String address;

    @Value("${database.url:jdbc:mysql://localhost:3306/jkh}")
    private String jdbcUrl;

    @Value("${database.user:jkh}")
    private String userName;

    @Value("${database.password:jkhPass}")
    private String userPassword;

    // DB
    @Bean
    public DataSource dataSource() {
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setURL(jdbcUrl);
        dataSource.setUser(userName);
        dataSource.setPassword(userPassword);
        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean
    public MetricRegistry metricRegistry() {
        return new MetricRegistry();
    }

    @Bean
    public UserDaoImpl usersDao(JdbcTemplate jdbcTemplate, MetricRegistry metricRegistry){
        return new UserDaoImpl(jdbcTemplate, metricRegistry);
    }

    @Bean
    public CounterDaoImpl counterDao(JdbcTemplate jdbcTemplate, MetricRegistry metricRegistry){
        return new CounterDaoImpl(jdbcTemplate, metricRegistry);
    }

    @Bean
    public FlatDaoImpl flatDao(JdbcTemplate jdbcTemplate, MetricRegistry metricRegistry){
        return new FlatDaoImpl(jdbcTemplate, metricRegistry);
    }

    @Bean
    public HouseDaoImpl houseDao(JdbcTemplate jdbcTemplate, MetricRegistry metricRegistry){
        return new HouseDaoImpl(jdbcTemplate, metricRegistry);
    }

    @Bean
    public IndicationDaoImpl indicationDao(JdbcTemplate jdbcTemplate, MetricRegistry metricRegistry){
        return new IndicationDaoImpl(jdbcTemplate, metricRegistry);
    }

    @Bean
    public TariffDaoImpl tariffDao(JdbcTemplate jdbcTemplate, MetricRegistry metricRegistry){
        return new TariffDaoImpl(jdbcTemplate, metricRegistry);
    }

    // Clients
    @Bean
    public AuthClient authClient() {
        return Feign.builder()
                    .client(new NonFollowedRedirectsClient(null, null))
                    .encoder(new JacksonEncoder())
                    .decoder(new JacksonDecoder())
                    .logger(new Slf4jLogger(AuthClient.class))
                    .logLevel(Logger.Level.FULL)
                    .target(AuthClient.class, address);
    }
}

