package com.hospital.module.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Modality;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;

public class SceneManager {

    private static Stage primaryStage;

    public SceneManager(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public SceneManager(){};

    public void showMainScene() {
        try {
            String absolutePath = "file:/C:/Users/DELL/IdeaProjects/Hopital-Management-System/src/main/java/com/hospital/module/view/MainScene.fxml";
            URL resourceUrl = new URL(absolutePath);
            System.out.println(resourceUrl);
            if (resourceUrl != null) {
                Parent root = FXMLLoader.load(resourceUrl);
                primaryStage.setTitle("Hospital Management System");
                Scene scene = new Scene(root, 600, 400);
                scene.getStylesheets().add(getClass().getResource("/css/MainScene.css").toExternalForm());
                primaryStage.setScene(scene);
                primaryStage.show();
            } else {
                System.err.println("MainScene.fxml not found");
            }
        } catch (IOException e) {
            e.printStackTrace();
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



    public void closeStage(){
        primaryStage.close();
    }
    // Add methods for other scenes as needed
}
