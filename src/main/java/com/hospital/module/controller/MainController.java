package com.hospital.module.controller;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {

    @FXML
    public void handleReceptionistButton() {
        showLoginPopup("Receptionist Login");
    }

    @FXML
    public void handleDoctorButton() {
        showLoginPopup("Doctor Login");
    }

    @FXML
    public void handleNurseButton() {
        showLoginPopup("Nurse Login");
    }

    private void showLoginPopup(String title) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/login_view.fxml"));
            Parent root = loader.load();
            Stage loginStage = new Stage();
            loginStage.initModality(Modality.APPLICATION_MODAL);
            loginStage.setScene(new Scene(root));
            loginStage.setTitle(title);
            loginStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

