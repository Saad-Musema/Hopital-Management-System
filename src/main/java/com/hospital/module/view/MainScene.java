package com.hospital.module.view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class MainScene {

    @FXML
    private Button receptionistBtn;

    @FXML
    private Button doctorBtn;

    @FXML
    private Button nurseBtn;

    @FXML
    private void onReceptionistClicked() {
        // Add action for receptionist button click
        System.out.println("Receptionist button clicked");
    }

    @FXML
    private void onDoctorClicked(javafx.event.ActionEvent event) {
        // Add action for doctor button click
        System.out.println("Doctor button clicked");
        try {
            // Load the DoctorDashboardScene FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("DoctorDashboardScene.fxml"));
            Parent doctorDashboardRoot = loader.load();
            
            // Get the current stage
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            
            // Set the new scene
            stage.setScene(new Scene(doctorDashboardRoot));
            stage.setTitle("Doctor Dashboard");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onNurseClicked() {
        // Add action for nurse button click
        System.out.println("Nurse button clicked");
    }
}
