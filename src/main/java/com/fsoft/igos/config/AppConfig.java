package com.fsoft.igos.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableCaching
@Configuration("appConfiguration")
@ComponentScan(basePackages = "com.fsoft.igos")
@PropertySource(value = {"classpath:application.properties"})
@EnableScheduling
public class AppConfig {
    @Value("${build.date}")
    private String buildDate;

    @Value("${version}")
    private String version;

    public String getBuildDate() {
        return buildDate;
    }

    public String getVersion() {
        return version;
    }
}
