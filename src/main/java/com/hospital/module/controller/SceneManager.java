package com.hospital.module.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Modality;
import java.io.IOException;
import java.util.Objects;

public class SceneManager {

    private Stage primaryStage;

    public SceneManager(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public SceneManager(){};

    public void showMainScene() {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/hospital/module/view/MainScene.fxml")));
            primaryStage.setTitle("Hospital Management System");
            primaryStage.setScene(new Scene(root, 600, 400));
            primaryStage.show();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public Runnable showLoginPopup = () -> {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("fxml/userFound.fxml")));
            Stage loginStage = new Stage();
            loginStage.initModality(Modality.APPLICATION_MODAL);
            loginStage.setScene(new Scene(root));
            loginStage.setTitle("Patient Information");
            loginStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    };


    // Add methods for other scenes as needed
}
