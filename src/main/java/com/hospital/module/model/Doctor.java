package com.hospital.module.model;

import com.hospital.module.db.DatabaseConnection;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

  public static List getAllDoctorAppointments(Integer doctorId){
    List<Timestamp> appointments = new ArrayList<>();
    String searchSQL = "SELECT * FROM appointment WHERE doctor_id = ?";

    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement preparedStatement = conn.prepareStatement(searchSQL)) {

      preparedStatement.setInt(1, doctorId);

      try (ResultSet rs = preparedStatement.executeQuery()) {
        while (rs.next()) {
          Timestamp appointmentTimestamp = rs.getTimestamp("appointment_date");
          String status = rs.getString("status");
              System.out.println(appointmentTimestamp + "with Status" + status);
          if(Objects.equals(status, "Scheduled")){

            appointments.add(appointmentTimestamp);
          }


        }
          System.out.println(appointments);
        return appointments;
      }
    } catch (SQLException e) {
      System.err.println("SQL Error: " + e.getMessage());
    } catch (Exception e) {
      System.err.println("Error: " + e.getMessage());
    }

    return appointments;
  }

  public static ArrayList getDoctorAppointments(Integer doctorId) {
    ArrayList<Timestamp> appointments = new ArrayList<>();
    String searchSQL = "SELECT appointment_date, status FROM appointment WHERE doctor_id = ?";

    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement preparedStatement = conn.prepareStatement(searchSQL)) {

      preparedStatement.setInt(1, doctorId);

      try (ResultSet rs = preparedStatement.executeQuery()) {
        while (rs.next()) {
          Timestamp appointmentTimestamp = rs.getTimestamp("appointment_date");
          String status = rs.getString("status");

          if(status == "Scheduled") {
            appointments.add(appointmentTimestamp);
          }


        }
      }
    } catch (SQLException e) {
      System.err.println("SQL Error: " + e.getMessage());
    } catch (Exception e) {
      System.err.println("Error: " + e.getMessage());
    }

    return appointments;
  }


  public static Integer getDoctorId(String name){
    try{
      Connection conn = DatabaseConnection.getConnection();
      String SQL = "Select doctor_id from doctor where name = ? ";
      PreparedStatement preparedStatement = conn.prepareStatement(SQL);
      preparedStatement.setString(1, "name");
      try (ResultSet rs = preparedStatement.executeQuery()) {
        return rs.getInt("doctor_id");
      }
    }catch(Exception e){
      System.out.println("Error: " + e.getMessage());
    }

    return 1;
  };
}
