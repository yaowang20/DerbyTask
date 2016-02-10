package com.example;

import org.hibernate.cfg.AvailableSettings;
import org.hibernate.dialect.DerbyTenSevenDialect;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by yao on 1/13/2016.
 */

@Configuration
@EnableTransactionManagement(mode = AdviceMode.ASPECTJ)
@EnableJpaRepositories(basePackageClasses = TaskRepository.class, entityManagerFactoryRef = "taskEntityManagerFactory")

public class DBConfig
{
    @Value("${resource.root.dir}")
    private String resourceRootDir;

    @Bean
    public DataSource getDataSource()
    {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setDriverClassName("org.apache.derby.jdbc.EmbeddedDriver");
        dataSource.setUrl(String.format("jdbc:derby:%s;create=true", getDatabasePath()));

        return dataSource;
    }



    protected String getDatabasePath()
    {
        File dbFile = new File(resourceRootDir, "task.db");
        return dbFile.getAbsolutePath();
    }


    @Bean(name = "taskEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean(
            EntityManagerFactoryBuilder builder)
    {
        LocalContainerEntityManagerFactoryBean lef = new LocalContainerEntityManagerFactoryBean();

        return builder.dataSource(getDataSource())
                .packages(Task.class)
                .properties(getJpaProperties())
                .build();
    }

    protected Map<String, String> getJpaProperties()
    {
        Map<String, String> jpaProperties = new HashMap<>();
        jpaProperties.put(AvailableSettings.DIALECT, DerbyTenSevenDialect.class.getName());
        jpaProperties.put("hibernate.hbm2ddl.auto", "update");
        jpaProperties.put("hibernate.show_sql", "false");

        return jpaProperties;
    }
}
