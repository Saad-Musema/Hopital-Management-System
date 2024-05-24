package com.hospital.module.controller;

import com.hospital.module.Main;
import com.hospital.module.db.DatabaseConnection;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.CountDownLatch;

import static com.hospital.module.view.MainScene.latch;

public class LoginController {

    private Stage loginStage;

    public void setLoginStage(Stage stage) {
        this.loginStage = stage;
    }

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button loginButton;

    @FXML
    private Button cancelButton;

    private Stage stage;

    private static boolean loggedIn = false;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public static boolean checkLogin(){
        return loggedIn;
    }

    @FXML
    private void handleLogin() {
        String email = emailField.getText(); // Assuming you have a TextField for the email
        String password = passwordField.getText(); // Assuming you have a PasswordField for the password

        boolean isValidUser = validateCredentials(email, password);

        if (isValidUser) {
            JOptionPane.showMessageDialog(null, "Login successful.", "Success", JOptionPane.INFORMATION_MESSAGE);
            System.out.println("Login successful. User role: " + userRole);

            latch.countDown();

            if ("Doctor".equals(userRole)) {
                System.out.println("Doctor ID: " + doctorId);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Login failed. Please check your email and password.", "Error", JOptionPane.ERROR_MESSAGE);
            System.out.println("Login failed. Please check your email and password.");

        }
    }

    private String userRole;
    private int doctorId = -1; // -1 indicates that the user is not a doctor or the ID has not been fetched

    private boolean validateCredentials(String email, String password) {
        try {
            Connection conn = DatabaseConnection.getConnection();

            String query = "SELECT * FROM employee WHERE email = ? AND password = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                userRole = resultSet.getString("role");
                if ("Doctor".equals(userRole)) {
                    doctorId = resultSet.getInt("employee_id");
                }
                return true;
            } else {
                System.out.println("Invalid credentials");
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    @FXML
    private void handleCancel() {
        // Close the login window if Cancel button is clicked
        stage.close();
    }
}
