package Otus.Jetty;

import Otus.Cache.CacheEngine;
import Otus.Cache.CacheEngineInterface;
import Otus.Support.DBExecutor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@Configuration
@ComponentScan(basePackages = {"Otus.Cache","Otus.Support"})
public class AppConfiguration {
    @Bean(name = "dbExecutorBean")
    public DBExecutor dbServiceBean() {
        return new DBExecutor();
    }

    @Bean(name = "cacheEngine")
    public CacheEngine cacheEngine() {
        return new CacheEngine(5, 0, 0, true);
    }
}