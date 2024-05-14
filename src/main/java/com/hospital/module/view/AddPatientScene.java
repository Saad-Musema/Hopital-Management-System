package com.hospital.module.view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

public class AddPatientScene {

  @FXML
  private TextField nameField;
  @FXML
  private TextField ageField;
  @FXML
  private CheckBox maleCheckBox;
  @FXML
  private CheckBox femaleCheckBox;
  @FXML
  private TextField phoneField;
  @FXML
  private TextField addressField;
  @FXML
  private Button addPatientButton;

  @FXML
  private void onAddPatientClicked() {
    // Add logic to handle patient addition
    String name = nameField.getText();
    int age = Integer.parseInt(ageField.getText());
    CheckBox maleTCheckBox;
    boolean isMale = maleCheckBox.isSelected();
    boolean isFemale = femaleCheckBox.isSelected();
    String phone = phoneField.getText();
    String address = addressField.getText();

    System.out.println("Adding Patient: " + name + ", Age: " + age + ", Gender: " +
        (isMale ? "Male" : "Female") + ", Phone: " + phone + ", Address: " + address);
    // Implement your logic to add the patient to the database or a data structure
  }
}