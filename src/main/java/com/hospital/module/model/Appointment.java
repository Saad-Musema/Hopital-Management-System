package com.hospital.module.model;

import java.util.Date;

public class Appointment {
  private int appointmentId;
  private Patient patient;
  private Doctor doctor;
  private Date appointmentDate;
  private String appointmentTime; // Added field for appointment time
  private String reason;

  // Constructors
  public Appointment() {
  }

  public Appointment(Patient patient, Doctor doctor, Date appointmentDate, String appointmentTime, String reason) {
    this.patient = patient;
    this.doctor = doctor;
    this.appointmentDate = appointmentDate;
    this.appointmentTime = appointmentTime; // Initialize the new field
    this.reason = reason;
  }

  // Getters and Setters
  public int getAppointmentId() {
    return appointmentId;
  }

  public void setAppointmentId(int appointmentId) {
    this.appointmentId = appointmentId;
  }

  public Patient getPatient() {
    return patient;
  }

  public void setPatient(Patient patient) {
    this.patient = patient;
  }

  public Doctor getDoctor() {
    return doctor;
  }

  public void setDoctor(Doctor doctor) {
    this.doctor = doctor;
  }

  public Date getAppointmentDate() {
    return appointmentDate;
  }

  public void setAppointmentDate(Date appointmentDate) {
    this.appointmentDate = appointmentDate;
  }

  public String getAppointmentTime() {
    return appointmentTime;
  }

  public void setAppointmentTime(String appointmentTime) {
    this.appointmentTime = appointmentTime;
  }

  public String getReason() {
    return reason;
  }

  public void setReason(String reason) {
    this.reason = reason;
  }

  // toString method
  @Override
  public String toString() {
    return "Appointment{" +
        "appointmentId=" + appointmentId +
        ", patient=" + patient +
        ", doctor=" + doctor +
        ", appointmentDate=" + appointmentDate +
        ", appointmentTime='" + appointmentTime + '\'' +
        ", reason='" + reason + '\'' +
        '}';
  }
}
