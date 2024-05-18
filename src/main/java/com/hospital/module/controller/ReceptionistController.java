package com.hospital.module.controller;

import com.hospital.module.db.DatabaseConnection;
import com.hospital.module.model.Patient;

import java.sql.*;


public class ReceptionistController {



    public static void addNewPatient(String name, int age, String gender, String phoneNumber,String address) {
        // Validate inputs
        if (isValidName(name) && isValidAge(age) && isValidAddress(address)) {
            // If inputs are valid, create a Patient object and add it to the database
            Patient patient = new Patient(name, age, gender, phoneNumber, address);
            System.out.println(patient);
            if (insertPatient(patient)) {
                System.out.println("Patient added successfully.");
            } else {
                System.out.println("Failed to add patient.");
            }
        } else {
            // If inputs are not valid, display an error message to the user
            System.out.println("Invalid input. Please check the entered values.");
        }
    }

    private static boolean isValidName(String name) {
        // Implement name validation logic (e.g., length, format, etc.)
        return !name.trim().isEmpty();
    }

    private static boolean isValidAge(int age) {
        // Implement age validation logic (e.g., range)
        return age >= 0 && age <= 120; // Assuming reasonable age range
    }

    private static boolean isValidAddress(String address) {
        // Implement address validation logic (e.g., length, format, etc.)
        return !address.trim().isEmpty();
    }

//    private static boolean isValidPhoneNumber(String phoneNumber){
//        return
//    }

//    private static boolean checkUserExistence(String phoneNumber){
//        String checkPhoneNumber = "S";
//    }

    public boolean searchPatient(String PhoneNumber) {
        String searchSQL = "SELECT * FROM PATIENT WHERE PhoneNumber = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(searchSQL)) {
            pstmt.setString(1, PhoneNumber);
            try (ResultSet rs = pstmt.executeQuery()) {
                // If the ResultSet has at least one row, the patient with the given ID exists
                return rs.next();
            }
        } catch (SQLException e) {
            System.err.println("Error searching for patient: " + e.getMessage());
            return false;
        }
    }



    private static boolean insertPatient(Patient patient) {
        String sql = "INSERT INTO patient (Name, Age, Gender, PhoneNumber, Address)\n" +
                "VALUES (?, ?, ?, ?, ?);\n";

        try (
                Connection conn = DatabaseConnection.getConnection();
                PreparedStatement statement = conn.prepareStatement(sql);
        ) {
            statement.setString(1, patient.getName());
            statement.setInt(2, patient.getAge());
            statement.setString(3, patient.getGender());
            statement.setString(4, patient.getPhoneNumber());
            statement.setString(5, patient.getAddress());


            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            System.err.println("Error inserting patient: " + e);
            return false;
        }
    }
}
