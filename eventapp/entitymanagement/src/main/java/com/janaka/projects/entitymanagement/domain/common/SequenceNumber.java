package com.janaka.projects.entitymanagement.domain.common;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.envers.Audited;

@Audited
@Entity
@Table(name = "sequence_number",
    indexes = {@Index(name = "sequence_number_id_pk_index", unique = true, columnList = "id"),
        @Index(name = "sequence_number_id_index", unique = true, columnList = "uuid")})
public class SequenceNumber extends AuditEntity implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @NotNull
  @Column(name = "current_sequence_number")
  private long currentSequenceNumber;

  @NotNull
  @Column(name = "prefix_value")
  private String prefixValue;

  @NotNull
  @Column(name = "number_length")
  private int numberLength;

  @Column(name = "description")
  private String description;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public long getCurrentSequenceNumber() {
    return currentSequenceNumber;
  }

  public void setCurrentSequenceNumber(long currentSequenceNumber) {
    this.currentSequenceNumber = currentSequenceNumber;
  }

  public String getPrefixValue() {
    return prefixValue;
  }

  public void setPrefixValue(String prefixValue) {
    this.prefixValue = prefixValue;
  }

  public int getNumberLength() {
    return numberLength;
  }

  public void setNumberLength(int numberLength) {
    this.numberLength = numberLength;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  @Override
  public String toString() {
    return "SequenceNumber [id=" + id + ", currentSequenceNumber=" + currentSequenceNumber + ", prefixValue="
        + prefixValue + ", numberLength=" + numberLength + ", description=" + description + "]";
  }



}
