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
        System.out.println("Heyy");

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
            addUserToDatabase(name, email, hashedPassword, userType);
        } else {
            System.out.println("Please fill in all fields.");
        }
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

