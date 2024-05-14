package com.hospital.module.controller;

import com.hospital.module.model.Prescription;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PrescriptionFileHandler {
  private static final String FILE_PATH = "prescriptions.txt";

  // Method to write a prescription to a file
  public void writePrescription(Prescription prescription) {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
      SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
      String formattedDate = dateFormat.format(prescription.getDate());
      writer.write(prescription.getPatientId() + "," + formattedDate + "," + prescription.getNotes() + "\n");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  // Method to read all prescriptions from a file
  public List<Prescription> readPrescriptions() {
    List<Prescription> prescriptions = new ArrayList<>();
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
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
}
