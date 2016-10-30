package com.janaka.projects.services.reports.domains;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.janaka.projects.entitymanagement.domain.common.BaseDomain;
import com.janaka.projects.services.reports.util.GeneralEnumConstants.ReportType;

@Entity
@Table(name = "report_template",
    indexes = {@Index(name = "report_template_id_pk_index", unique = true, columnList = "id"),
        @Index(name = "report_name_index", unique = true, columnList = "report_name"),
        @Index(name = "uploaded_file_name_index", unique = true, columnList = "uploaded_file_name")})
public class ReportTemplate extends BaseDomain {

  private static final long serialVersionUID = 1L;

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id = 0;

  @Column(name = "report_name")
  private String reportName;

  @Column(name = "uploaded_file_name")
  private String uploadedFileName;

  @Column(name = "original_file_name")
  private String originalFileName;

  @Column(name = "report_description")
  private String reportDescription;

  @Enumerated(EnumType.ORDINAL)
  private ReportType reportType;

  @Version
  private int version;

  @Transient
  private MultipartFile file;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getReportName() {
    return reportName;
  }

  public void setReportName(String reportName) {
    this.reportName = reportName;
  }

  public String getUploadedFileName() {
    return uploadedFileName;
  }

  public void setUploadedFileName(String uploadedFileName) {
    this.uploadedFileName = uploadedFileName;
  }

  public String getReportDescription() {
    return reportDescription;
  }

  public void setReportDescription(String reportDescription) {
    this.reportDescription = reportDescription;
  }

  @Override
  public String toString() {
    return "ReportTemplate [id=" + id + ", reportName=" + reportName + ", uploadedFileName=" + uploadedFileName
        + ", reportDescription=" + reportDescription + "]";
  }

  public MultipartFile getFile() {
    return file;
  }

  public void setFile(MultipartFile file) {
    this.file = file;
  }

  public String getOriginalFileName() {
    return originalFileName;
  }

  public void setOriginalFileName(String originalFileName) {
    this.originalFileName = originalFileName;
  }

  public int getVersion() {
    return version;
  }

  public void setVersion(int version) {
    this.version = version;
  }

  public ReportType getReportType() {
    return reportType;
  }

  public void setReportType(ReportType reportType) {
    this.reportType = reportType;
  }

  public void updateFromReportTemplateFromUI(ReportTemplate reportTemplate) {
    this.reportName = reportTemplate.getReportName();
    this.reportDescription = reportTemplate.getReportDescription();
    if (StringUtils.isNotEmpty(reportTemplate.getUploadedFileName())) {
      this.uploadedFileName = reportTemplate.getUploadedFileName();
    }
    if (StringUtils.isNotEmpty(reportTemplate.getOriginalFileName())) {
      this.originalFileName = reportTemplate.getOriginalFileName();
    }
    this.setModifiedByUser(reportTemplate.getModifiedByUser());
    this.setModificationTime(reportTemplate.getModificationTime());
  }



}
