package com.hospital.module.model;

import java.util.List;

public class Doctor extends Person {
  private String specialty;
  private String department; // Added field for department
  private List<Appointment> appointments;

  // Constructors
  public Doctor() {
    super();
  }

  public Doctor(String name, int age, String gender, String phoneNumber, String address, String specialty,
      String department) {
    super(name, age, gender, phoneNumber, address);
    this.specialty = specialty;
    this.department = department; // Initialize the new field
  }

  // Getters and Setters
  public String getSpecialty() {
    return specialty;
  }

  public void setSpecialty(String specialty) {
    this.specialty = specialty;
  }

  public String getDepartment() {
    return department;
  }

  public void setDepartment(String department) {
    this.department = department;
  }

  public List<Appointment> getAppointments() {
    return appointments;
  }

  public void setAppointments(List<Appointment> appointments) {
    this.appointments = appointments;
  }

  // toString method
  @Override
  public String toString() {
    return "Doctor{" +
        "specialty='" + specialty + '\'' +
        ", department='" + department + '\'' +
        ", appointments=" + appointments +
        "} " + super.toString();
  }
}
