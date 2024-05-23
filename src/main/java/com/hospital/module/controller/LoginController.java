package com.hospital.module.controller;

import com.hospital.module.db.DatabaseConnection;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginController {

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
            System.out.println("Login successful. User role: " + userRole);
            if ("Doctor".equals(userRole)) {
                System.out.println("Doctor ID: " + doctorId);
            }

            // Close the current stage
            SceneManager sceneManager = new SceneManager();
            sceneManager.closeStage();
            stage.close();


        } else {
            System.out.println("Login failed. Please check your email and password.");
            // Optionally, display an error message to the user
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
