package com.hospital.module.view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

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
    private void onDoctorClicked() {
        // Add action for doctor button click
        System.out.println("Doctor button clicked");
    }

    @FXML
    private void onNurseClicked() {
        // Add action for nurse button click
        System.out.println("Nurse button clicked");
    }
}
