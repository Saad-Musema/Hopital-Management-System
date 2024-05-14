package com.hospital.module.view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

public class DoctorDashboardScene {

  @FXML
  private ListView<String> appointmentsListView;
  @FXML
  private Button writePrescriptionButton;
  @FXML
  private Button addDiagnosisButton;

  @FXML
  private void onWritePrescription() {
    System.out.println("Navigate to write prescription scene");
    // Logic to open write prescription interface
  }

  @FXML
  private void onAddDiagnosis() {
    System.out.println("Navigate to add diagnosis scene");
    // Logic to open add diagnosis interface
  }
}
