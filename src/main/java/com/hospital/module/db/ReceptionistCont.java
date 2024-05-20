package com.hospital.module.db;

import com.hospital.module.Services.EmailService;
import com.hospital.module.controller.ReceptionistController;
import com.hospital.module.controller.SceneManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.mail.MessagingException;
import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;

public class ReceptionistCont {

    @FXML
    private TextField phoneNumberField;

    @FXML
    private void handleAddPatient() throws MessagingException {
        System.out.println("Add Patient button clicked");
        try {
            String absolutePath = "file:/C:/Users/DELL/IdeaProjects/Hopital-Management-System/src/main/java/com/hospital/module/view/AddPatientScene.fxml";
            URL resourceUrl = new URL(absolutePath);

            // Check if the resource URL is not null
            if (resourceUrl != null) {
                // Load the AddPatientScene.fxml using the absolute path
                Parent root = FXMLLoader.load(resourceUrl);

                // Create a new stage
                Stage newStage = new Stage();

                // Set the scene on the new stage
                Scene scene = new Scene(root);
                newStage.setScene(scene);

                // Set any other properties for the new stage if needed
                newStage.setTitle("Receptionist Page");

                // Show the new stage
                newStage.show();
            } else {
                System.err.println("AddPatientScene.fxml not found");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Caught an exception: " + e.getMessage());
        }
    }



    @FXML
    private void handleCreateAppointment() {
        // Implement logic for creating an appointment
        System.out.println("Create Appointment button clicked");

        try {
            String absolutePath = "file:/C:/Users/DELL/IdeaProjects/Hopital-Management-System/src/main/java/com/hospital/module/view/CreateAppointmentScene.fxml";
            URL resourceUrl = new URL(absolutePath);

            // Check if the resource URL is not null
            if (resourceUrl != null) {
                // Load the CreateAppointmentScene.fxml using the absolute path
                Parent root = FXMLLoader.load(resourceUrl);

                // Create a new stage
                Stage newStage = new Stage();

                // Set the scene on the new stage
                Scene scene = new Scene(root);
                newStage.setScene(scene);

                // Set any other properties for the new stage if needed
                newStage.setTitle("Receptionist Page");

                // Show the new stage
                newStage.show();
            } else {
                System.err.println("CreateAppointmentScene.fxml not found");
            }
        } catch (IOException e) {
            System.err.println("IOException occurred while loading the FXML file.");
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("An unexpected error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }




    @FXML
    private void handleSearchPatient() {
        // Implement logic for searching a patient
        String phoneNumber = phoneNumberField.getText();
        ReceptionistController receptionistController = new ReceptionistController();
        SceneManager sceneManager = new SceneManager();

        // Show a popup indicating that the search is in progress
        JOptionPane.showMessageDialog(null, "Searching for patient with phone number: " + phoneNumber, "Search in Progress", JOptionPane.INFORMATION_MESSAGE);

        // Create and start a new thread for search process
        Thread searchThread = new Thread(() -> {
            // Simulate the search process
            boolean result = receptionistController.searchPatient(phoneNumber);

            // Show the result in another popup
            if (result) {
                JOptionPane.showMessageDialog(null, "Patient found!", "Search Result", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Patient not found.", "Search Result", JOptionPane.INFORMATION_MESSAGE);
            }

            System.out.println("Search Patient button clicked with phone number: " + phoneNumber);
        });

        // Start the search thread
        searchThread.start();
    }
}


