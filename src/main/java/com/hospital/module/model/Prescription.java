package com.hospital.module.model;

import java.util.Date;

public class Prescription {
  private int patientId;
  private String patientName; // New field for patient's name
  private Date date;
  private String notes;

  // Constructors
  public Prescription() {
  }

  public Prescription(int patientId, String patientName, Date date, String notes) {
    this.patientId = patientId;
    this.patientName = patientName;
    this.date = date;
    this.notes = notes;
  }

  public Prescription(int patientId, Date date, String notes) {
    this.patientId = patientId;
    this.date = date;
    this.notes = notes;
  }

  // Getters and Setters
  public int getPatientId() {
    return patientId;
  }

  public void setPatientId(int patientId) {
    this.patientId = patientId;
  }

  public String getPatientName() {
    return patientName;
  }

  public void setPatientName(String patientName) {
    this.patientName = patientName;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public String getNotes() {
    return notes;
  }

  public void setNotes(String notes) {
    this.notes = notes;
  }

  // toString method
  @Override
  public String toString() {
    return "Prescription{" +
            "patientId=" + patientId +
            ", patientName='" + patientName + '\'' +
            ", date=" + date +
            ", notes='" + notes + '\'' +
            '}';
  }
}
