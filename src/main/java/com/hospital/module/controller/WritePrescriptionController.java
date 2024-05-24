package com.hospital.module.controller;

import javafx.fxml.FXML;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.util.Callback;

import javax.swing.*;
import javax.swing.text.Document;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;


public class WritePrescriptionController {
    @FXML
    private DatePicker dateField;
    @FXML
    private TextField patientIdField;
    @FXML
    private TextArea noteField;

    private void initialize() {
        restrictDatePicker();
    }

    private void restrictDatePicker() {
        dateField.setDayCellFactory(new Callback<DatePicker, DateCell>() {
            @Override
            public DateCell call(final DatePicker datePicker) {
                return new DateCell() {
                    @Override
                    public void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item.isBefore(LocalDate.now())) {
                            setDisable(true);
                            setStyle("-fx-background-color: #ffc0cb;");
                        }
                    }
                };
            }
        });
    }

//    @FXML
//    private void writePrescription() {
//        // Get the selected date from the DatePicker
//        LocalDate date = dateField.getValue();
//
//        // Get the text from other fields
//        String patientId = patientIdField.getText();
//        String note = noteField.getText();
//
//        // Write data to the file
//        try (BufferedWriter writer = new BufferedWriter(new FileWriter("prescriptions.txt", true))) {
//            writer.write(date.toString() + "," + patientId + "," + note + "\n");
//            JOptionPane.showMessageDialog(null, "Prescription saved successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
//        } catch (IOException e) {
//            e.printStackTrace();
//            JOptionPane.showMessageDialog(null, "Failed to save prescription. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
//        }
//    }
    @FXML
    private void writePrescription() {
        // Get the selected date from the DatePicker
        LocalDate date = dateField.getValue();

        // Get the text from other fields
        String patientId = patientIdField.getText();
        String note = noteField.getText();

        // Create the file name using patient ID and date
        String fileName = patientId + "_" + date.toString() + ".txt";

        // Write data to the file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.write(date.toString() + "," + patientId + "," + note + "\n");
            JOptionPane.showMessageDialog(null, "Prescription saved successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);

        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Failed to save prescription. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void openPrescriptionWithNotepad(LocalDate date, String patientId) {
        // Create the file name using patient ID and date
        String fileName = patientId + "_" + date.toString() + ".txt";

        try {
            // Open the TXT file with Notepad
            Runtime.getRuntime().exec("notepad " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Failed to open prescription with Notepad. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


}
