package com.hospital.module.view;

import com.hospital.module.controller.MainController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;

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
        try {
            String absolutePath = "file:/C:/Users/DELL/IdeaProjects/Hopital-Management-System/src/main/java/com/hospital/module/view/receptionist.fxml";
            URL resourceUrl = new URL(absolutePath);
            System.out.println(resourceUrl);

            if (resourceUrl != null){
                Parent root = FXMLLoader.load(resourceUrl);

            // Create a new stage
            Stage newStage = new Stage();

            // Set the scene on the new stage
            Scene scene = new Scene(root);
            newStage.setScene(scene);

            // Set any other properties for the new stage if needed
            newStage.setTitle("Receptionist Page");

            // Show the new stage
            newStage.show();}
            else{
                System.err.println("MainScene.fxml not found");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Caught an exception: " + e.getMessage());
            e.printStackTrace();
        }}

        @FXML
        private void onDoctorClicked() {
            try {
                String absolutePath = "file:/C:/Users/DELL/IdeaProjects/Hopital-Management-System/src/main/java/com/hospital/module/view/DoctorDashboardScene.fxml";
                URL resourceUrl = new URL(absolutePath);
                System.out.println(resourceUrl);

                // Check if the resource URL is not null
                if (resourceUrl != null) {
                    // Load the DoctorDashboardScene.fxml using the absolute path
                    Parent root = FXMLLoader.load(resourceUrl);

                    // Create a new stage
                    Stage newStage = new Stage();

                    // Set the scene on the new stage
                    Scene scene = new Scene(root);
                    newStage.setScene(scene);

                    // Set any other properties for the new stage if needed
                    newStage.setTitle("Doctor Dashboard");

                    // Show the new stage
                    newStage.show();
                } else {
                    System.err.println("DoctorDashboardScene.fxml not found");
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                System.out.println("Caught an exception: " + e.getMessage());
                e.printStackTrace();
            }
            System.out.println("Doctor button clicked");
        }


    @FXML
        private void onNurseClicked() {
            try {
                // Load the nurse dashboard FXML file
                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/hospital/module/view/NurseDashboardScene.fxml")));

                // Create a new stage
                Stage newStage = new Stage();

                // Set the scene on the new stage
                Scene scene = new Scene(root);
                newStage.setScene(scene);

                // Set any other properties for the new stage if needed
                newStage.setTitle("Nurse Dashboard");

                // Show the new stage
                newStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                System.out.println("Caught an exception: " + e.getMessage());
                e.printStackTrace();
            }
            System.out.println("Nurse button clicked");
        }

    }

