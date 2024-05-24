package com.hospital.module.controller;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import javax.swing.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

public class WriteDocumentController {
    @FXML
    private DatePicker dateField;
    @FXML
    private TextField patientIdField;
    @FXML
    private TextArea noteField;

    @FXML
    private void writeMedicalDocument(String patientId) {
        // Get the selected date from the DatePicker
        LocalDate date = dateField.getValue();

        // Generate the file name using the patient ID and date
        String fileName = patientId + "_" + date.toString() + ".txt";

        // Get the text from other fields
        String note = noteField.getText();

        // Write data to the file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.write(date.toString() + "," + patientId + "," + note + "\n");
            JOptionPane.showMessageDialog(null, "Medical document saved successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Failed to save medical document. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
