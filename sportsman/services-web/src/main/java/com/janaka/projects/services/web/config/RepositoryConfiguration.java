package com.janaka.projects.services.web.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.auditing.DateTimeProvider;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.datatables.repository.DataTablesRepositoryFactoryBean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.janaka.projects.services.web.util.AuditingDateTimeProvider;
import com.janaka.projects.services.web.util.AuditorAwareImpl;
import com.janaka.projects.services.web.util.CurrentDateTimeService;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaAuditing(dateTimeProviderRef = "dateTimeProvider", auditorAwareRef = "auditorProvider")
@EnableJpaRepositories(basePackages = {"com.janaka.projects.entitymanagement.dataaccessobjects.*"},
    repositoryFactoryBeanClass = DataTablesRepositoryFactoryBean.class)
@EnableAutoConfiguration(exclude = {org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration.class})
public class RepositoryConfiguration {


  @Bean
  AuditorAware<String> auditorProvider() {
    return new AuditorAwareImpl();
  }

  @Bean
  DateTimeProvider dateTimeProvider(CurrentDateTimeService currentDateTimeService) {
    return new AuditingDateTimeProvider(currentDateTimeService);
  }

  @Bean(destroyMethod = "close")
  DataSource dataSource(Environment env) {
    HikariConfig dataSourceConfig = new HikariConfig();
    dataSourceConfig.setDriverClassName(env.getRequiredProperty("spring.datasource.driver"));
    dataSourceConfig.setJdbcUrl(env.getRequiredProperty("spring.datasource.url"));
    dataSourceConfig.setUsername(env.getRequiredProperty("spring.datasource.username"));
    dataSourceConfig.setPassword(env.getRequiredProperty("spring.datasource.password"));
    dataSourceConfig.setPoolName("eventsapp");
    return new HikariDataSource(dataSourceConfig);

  }

  @Bean
  LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource, Environment env) {
    LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
    entityManagerFactoryBean.setDataSource(dataSource);
    entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
    entityManagerFactoryBean.setPackagesToScan("com.janaka.projects.entitymanagement.domain.*",
        "org.springframework.data.jpa.convert.threeten");

    Properties jpaProperties = new Properties();

    jpaProperties.put("hibernate.hbm2ddl.auto", env.getRequiredProperty("spring.jpa.hibernate.ddl-auto"));

    jpaProperties.put("hibernate.dialect", env.getRequiredProperty("spring.jpa.properties.hibernate.dialect"));

    jpaProperties.put("hibernate.ejb.naming_strategy", env.getRequiredProperty("spring.jpa.hibernate.naming-strategy"));

    jpaProperties.put("hibernate.show_sql", env.getRequiredProperty("spring.jpa.show-sql"));

    jpaProperties.put("hibernate.format_sql", env.getRequiredProperty("spring.jpa.format_sql"));

    entityManagerFactoryBean.setJpaProperties(jpaProperties);

    return entityManagerFactoryBean;
  }

  @Bean
  JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
    JpaTransactionManager transactionManager = new JpaTransactionManager();
    transactionManager.setEntityManagerFactory(entityManagerFactory);
    return transactionManager;
  }


}
