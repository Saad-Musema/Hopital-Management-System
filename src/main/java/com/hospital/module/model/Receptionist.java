package com.hospital.module.model;

public class Receptionist extends Person {
  private String department;

  // Constructors
  public Receptionist() {
    super();
  }

  public Receptionist(String name, int age, String gender, String phoneNumber, String address, String department) {
    super(name, age, gender, phoneNumber, address);
    this.department = department;
  }

  // Getters and Setters
  public String getDepartment() {
    return department;
  }

  public void setDepartment(String department) {
    this.department = department;
  }

  // toString method
  @Override
  public String toString() {
    return "Receptionist{" +
        "department='" + department + '\'' +
        "} " + super.toString();
  }
}
