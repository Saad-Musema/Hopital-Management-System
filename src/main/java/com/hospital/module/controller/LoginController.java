package com.hospital.module.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button loginButton;

    @FXML
    private Button cancelButton;

    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    private void handleLogin() {
        // Add logic to validate credentials and authenticate user
        // Close the login window if login is successful
        stage.close();
    }

    @FXML
    private void handleCancel() {
        // Close the login window if Cancel button is clicked
        stage.close();
    }
}
