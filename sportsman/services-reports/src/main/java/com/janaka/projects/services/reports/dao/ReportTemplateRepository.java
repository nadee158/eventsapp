package com.janaka.projects.services.reports.dao;

import org.springframework.data.repository.CrudRepository;

import com.janaka.projects.services.reports.domains.ReportTemplate;


public interface ReportTemplateRepository extends CrudRepository<ReportTemplate, Long> {

}
