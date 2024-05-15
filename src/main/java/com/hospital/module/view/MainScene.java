package com.hospital.module.view;

import com.hospital.module.controller.MainController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MainScene {

    @FXML
    private Button receptionistBtn;

    @FXML
    private Button doctorBtn;

    @FXML
    private Button nurseBtn;

    MainController mainController = new MainController();

    @FXML
    private void onReceptionistClicked() {

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
