package com.hospital.module.controller;

import java.awt.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import com.hospital.module.db.DatabaseConnection;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import javax.swing.*;

public class AdminDashboardController {

    @FXML
    private TextField nameField;

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private ComboBox<String> userTypeComboBox;

    public void handleAddUserAction() {

        // Get user input from the UI components
        String name = nameField.getText();
        String email = emailField.getText();
        String password = passwordField.getText();
        String userType = userTypeComboBox.getValue();

        // Hash the password
        String hashedPassword = hashPassword(password);

        // Insert user into the database
        if (name != null && !name.isEmpty() && email != null && !email.isEmpty()
                && password != null && !password.isEmpty() && userType != null && !userType.isEmpty()) {

            if (!isValidEmail(email)) {
                JOptionPane.showMessageDialog(null, "Invalid email format. Please enter a valid email address.", "Error", JOptionPane.WARNING_MESSAGE);
                return; // Exit method if email format is invalid
            }

            addUserToDatabase(name, email, hashedPassword, userType);
            JOptionPane.showMessageDialog(null, "User added successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
        } else {
            System.out.println("Please fill in all fields.");
            JOptionPane.showMessageDialog(null, "Failed to add user. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean isValidEmail(String email) {
        // Regular expression for basic email validation
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return email.matches(emailRegex);
    }

    private String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes());

            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void addUserToDatabase(String name, String email, String hashedPassword, String role) {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement("INSERT INTO employee (email, password, role, name) VALUES (?, ?, ?, ?)")) {
            pstmt.setString(1, email);
            pstmt.setString(2, hashedPassword);
            pstmt.setString(3, role);
            pstmt.setString(4, name);

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("User added successfully.");
            } else {
                System.out.println("Failed to add user.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

