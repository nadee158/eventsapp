package com.janaka.projects.services.web.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.datatables.repository.DataTablesRepositoryFactoryBean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableAutoConfiguration
@EntityScan(basePackages = {"com.janaka.projects.entitymanagement.domain.*"})
@EnableJpaRepositories(basePackages = {"com.janaka.projects.entitymanagement.dataaccessobjects.*"},
    repositoryFactoryBeanClass = DataTablesRepositoryFactoryBean.class)
@EnableTransactionManagement
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
public class RepositoryConfiguration {


  @Bean
  AuditorAware<String> auditorProvider() {
    return new AuditorAwareImpl();
  }

}
