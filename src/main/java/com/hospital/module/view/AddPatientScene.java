package com.hospital.module.view;

import com.hospital.module.db.DatabaseConnection;
import com.hospital.module.db.ReceptionistCont;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import java.sql.*;

import java.sql.PreparedStatement;

public class AddPatientScene {

  @FXML
  private TextField nameField;
  @FXML
  private TextField ageField;
  @FXML
  private ComboBox<String> genderComboBox;
  @FXML
  private TextField phoneNumberField;
  @FXML
  private TextField addressField;
  @FXML
  private TextField emailField;
  @FXML
  private Button addButton;
  @FXML
  private Button clearButton;



  @FXML
  private void handleAddButtonAction() {
    // Code to add the patient
    String name = nameField.getText();
    String age = ageField.getText();
    String gender = genderComboBox.getValue();
    String phoneNumber = phoneNumberField.getText();
    String address = addressField.getText();
    String email = emailField.getText();

    try {
      // Establish database connection
      Connection conn = DatabaseConnection.getConnection();

      ReceptionistCont receptionistCont = new ReceptionistCont();

      // Create the SQL query
      String insertQuery = "INSERT INTO patient (Name, Age, Gender, PhoneNumber, Address, email) " +
              "VALUES (?, ?, ?, ?, ?, ?)";

      // Create PreparedStatement
      PreparedStatement pstmt = conn.prepareStatement(insertQuery);
      pstmt.setString(1, name);
      pstmt.setString(2, age);
      pstmt.setString(3, gender);
      pstmt.setString(4, phoneNumber);
      pstmt.setString(5, address);
      pstmt.setString(6, email);

      // Execute the query
      int rowsAffected = pstmt.executeUpdate();

      if (rowsAffected > 0) {
        System.out.println("Patient added successfully.");

        receptionistCont.appointmentCreated();
        clearFormFields();
      } else {
        System.out.println("Failed to add patient.");
      }

      // Close the PreparedStatement and Connection

      pstmt.close();
      conn.close();
    } catch (SQLException e) {
      System.out.println("Error adding patient: " + e.getMessage());
    }
  }

  private void clearFormFields() {
    // Clear all form fields after adding patient
    nameField.clear();
    ageField.clear();
    genderComboBox.getSelectionModel().clearSelection();
    phoneNumberField.clear();
    addressField.clear();
    emailField.clear();
  }

  @FXML
  private void handleClearButtonAction() {
    // Clear all fields
    nameField.clear();
    ageField.clear();
    genderComboBox.setValue(null);
    phoneNumberField.clear();
    addressField.clear();
    emailField.clear();
  }
}
