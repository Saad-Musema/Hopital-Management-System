package com.hospital.module.model;

import java.util.Date;

public class MedicalDocument {
  private int documentId;
  private Patient patient;
  private String documentType;
  private Date documentDate;
  private String details;

  // Constructors
  public MedicalDocument() {
  }

  public MedicalDocument(int documentId, Patient patient, String documentType, Date documentDate, String details) {
    this.documentId = documentId;
    this.patient = patient;
    this.documentType = documentType;
    this.documentDate = documentDate;
    this.details = details;
  }

  // Getters and Setters
  public int getDocumentId() {
    return documentId;
  }

  public void setDocumentId(int documentId) {
    this.documentId = documentId;
  }

  public Patient getPatient() {
    return patient;
  }

  public void setPatient(Patient patient) {
    this.patient = patient;
  }

  public String getDocumentType() {
    return documentType;
  }

  public void setDocumentType(String documentType) {
    this.documentType = documentType;
  }

  public Date getDocumentDate() {
    return documentDate;
  }

  public void setDocumentDate(Date documentDate) {
    this.documentDate = documentDate;
  }

  // toString method
  @Override
  public String toString() {
    return "MedicalDocument{" +
        "documentId=" + documentId +
        ", patient=" + patient +
        ", documentType='" + documentType + '\'' +
        ", documentDate=" + documentDate +
        ", details='" + details + '\'' +
        '}';
  }
}
