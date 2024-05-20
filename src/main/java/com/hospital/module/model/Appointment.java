package com.hospital.module.model;

import java.sql.Timestamp;

public class Appointment {
  private int appointmentId;
  private int doctorId;
  private int patientId;
  private Timestamp appointmentDate;
  private String reason;
  private String status;

  public Appointment(int appointmentId, int doctorId, int patientId, Timestamp appointmentDate, String reason, String status) {
    this.appointmentId = appointmentId;
    this.doctorId = doctorId;
    this.patientId = patientId;
    this.appointmentDate = appointmentDate;
    this.reason = reason;
    this.status = status;
  }

  public int getAppointmentId() {
    return appointmentId;
  }

  public int getDoctorId() {
    return doctorId;
  }

  public int getPatientId() {
    return patientId;
  }

  public Timestamp getAppointmentDate() {
    return appointmentDate;
  }

  public String getReason() {
    return reason;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  @Override
  public String toString() {
    return "Appointment{" +
            "appointmentId=" + appointmentId +
            ", patient=" + patientId +
            ", doctor=" + doctorId +
            ", appointmentDate=" + appointmentDate +
            ", reason='" + reason + '\'' +
            '}';
  }
}



