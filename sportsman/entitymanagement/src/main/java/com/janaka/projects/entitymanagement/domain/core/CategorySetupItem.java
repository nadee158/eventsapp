package com.janaka.projects.entitymanagement.domain.core;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.envers.Audited;

import com.janaka.projects.entitymanagement.domain.common.AuditEntity;

@Audited
@Entity
@Table(name = "category_setup_item",
    indexes = {@Index(name = "category_setup_item_id_pk_index", unique = true, columnList = "id"),
        @Index(name = "category_setup_item_id_index", unique = true, columnList = "uuid")})
public class CategorySetupItem extends AuditEntity implements Serializable {


  private static final long serialVersionUID = 1L;

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(name = "item_name")
  private String itemName = StringUtils.EMPTY;

  @Column(name = "text1")
  private String text1;

  @Column(name = "text2")
  private String text2;

  @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
  @JoinColumn(name = "category_setup_id", referencedColumnName = "id")
  private CategorySetup categorySetup;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getItemName() {
    return itemName;
  }

  public void setItemName(String itemName) {
    this.itemName = itemName;
  }

  public static long getSerialversionuid() {
    return serialVersionUID;
  }

  public CategorySetup getCategorySetup() {
    return categorySetup;
  }

  public void setCategorySetup(CategorySetup categorySetup) {
    this.categorySetup = categorySetup;
  }

  public String getText1() {
    return text1;
  }

  public void setText1(String text1) {
    this.text1 = text1;
  }

  public String getText2() {
    return text2;
  }

  public void setText2(String text2) {
    this.text2 = text2;
  }

  @Override
  public String toString() {
    return "CategorySetupItem [id=" + id + ", itemName=" + itemName + ", text1=" + text1 + ", text2=" + text2
        + ", categorySetup=" + categorySetup + "]";
  }



}
