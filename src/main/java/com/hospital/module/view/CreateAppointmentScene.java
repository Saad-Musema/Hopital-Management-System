package com.hospital.module.view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class CreateAppointmentScene {

  @FXML
  private TextField patientNameField;
  @FXML
  private ComboBox<String> doctorNameField;
  @FXML
  private DatePicker datePicker;
  @FXML
  private ComboBox<String> timeSlotComboBox;
  @FXML
  private TextArea reasonField;
  @FXML
  private Button bookAppointmentButton;

  @FXML
  private void onBookAppointmentClicked() {
    // Implement the logic to book an appointment
    System.out.println("Appointment Booked for: " + patientNameField.getText());
    // More implementation would go here
  }
}
