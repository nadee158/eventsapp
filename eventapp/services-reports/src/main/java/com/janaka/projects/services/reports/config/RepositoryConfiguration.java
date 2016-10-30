package com.janaka.projects.services.reports.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.datatables.repository.DataTablesRepositoryFactoryBean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableAutoConfiguration
@EntityScan(basePackages = {"com.janaka.projects.services.reports.domains",
    "com.janaka.projects.entitymanagement.domain"})
@EnableJpaRepositories(basePackages = {"com.janaka.projects.services.reports.dao",
    "com.janaka.projects.entitymanagement.dataaccessobjects"},
      repositoryFactoryBeanClass = DataTablesRepositoryFactoryBean.class)
@EnableTransactionManagement
public class RepositoryConfiguration {

}
