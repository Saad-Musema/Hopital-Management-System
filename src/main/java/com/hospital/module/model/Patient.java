package com.hospital.module.model;

import com.hospital.module.db.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;


public class Patient extends Person {
    private int PatientID;
    private Date admissionDate;
    private Date dischargeDate;

    // Constructors
    public Patient() {
        super();
    }

    public Patient(String name, int age, String gender, String phoneNumber, String address ) {
        super(name, age, gender, phoneNumber, address);
    }

    public static int getPatientId(String patientName) throws SQLException {
        String query = "SELECT PatientID FROM patient WHERE Name = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, patientName);

            try (ResultSet resultSet = pstmt.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("PatientID");
                } else {
                    throw new SQLException("Patient not found");
                }
            }
        }
    }


    // Getters and Setters
    public Date getAdmissionDate() {
        return admissionDate;
    }

    public void setAdmissionDate(Date admissionDate) {
        this.admissionDate = admissionDate;
    }

    public Date getDischargeDate() {
        return dischargeDate;
    }

    public void setDischargeDate(Date dischargeDate) {
        this.dischargeDate = dischargeDate;
    }

    // toString method
    @Override
    public String toString() {
        return "Patient{" +
                "Patient Id" + getId() + '\'' +
                "name='" + getName() + '\'' +
                ", age=" + getAge() +
                ", gender='" + getGender() + '\'' +
                ", Phone Number='" + getPhoneNumber() + '\'' +
                ", address='" + getAddress() + '\'' +
                ", admissionDate=" + admissionDate +
                ", dischargeDate=" + dischargeDate +
                '}';
    }

    public static boolean checkPatientExistence(String name) {
        try {
            Connection conn = DatabaseConnection.getConnection();

            String searchSQL = "SELECT * FROM PATIENT WHERE NAME = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(searchSQL);
            preparedStatement.setString(1, name); // Use the name parameter

            try (ResultSet rs = preparedStatement.executeQuery()) {
                // Print the ResultSet for debugging
                while (rs.next()) {
                    System.out.println("Patient found: " + rs.getString("NAME"));
                    return true; // Return true if at least one row is found
                }
                // No patient found with the given name
                System.out.println("No patient found with name: " + name);
                return false;
            }

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println("Error Checking Patient Existence!");
            e.printStackTrace();
            return false;
        }
    }

}

