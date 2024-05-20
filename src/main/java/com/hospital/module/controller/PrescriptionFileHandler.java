package com.hospital.module.controller;

import com.hospital.module.model.Prescription;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PrescriptionFileHandler {
  private static final String FILE_DIRECTORY = "prescriptions/";

  // Method to write a prescription to a file based on patient name
  public void writePrescription(Prescription prescription) {
    String fileName = getFilePath(prescription.getPatientName());
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
      SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
      String formattedDate = dateFormat.format(prescription.getDate());
      writer.write(prescription.getPatientId() + "," + formattedDate + "," + prescription.getNotes() + "\n");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  // Method to read all prescriptions from a file based on patient name
  public List<Prescription> readPrescriptions(String patientName) {
    List<Prescription> prescriptions = new ArrayList<>();
    String fileName = getFilePath(patientName);
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
      String line;
      while ((line = reader.readLine()) != null) {
        String[] data = line.split(",");
        int patientId = Integer.parseInt(data[0]);
        Date date = dateFormat.parse(data[1]);
        String notes = data[2];
        Prescription prescription = new Prescription(patientId, date, notes);
        prescriptions.add(prescription);
      }
    } catch (IOException | ParseException e) {
      e.printStackTrace();
    }
    return prescriptions;
  }

  // Helper method to get the file path based on patient name
  private String getFilePath(String patientName) {
    File directory = new File(FILE_DIRECTORY);
    if (!directory.exists()) {
      directory.mkdirs(); // Create the directory if it doesn't exist
    }
    return FILE_DIRECTORY + patientName + "_prescriptions.txt";
  }
}
