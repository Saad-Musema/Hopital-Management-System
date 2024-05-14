package com.hospital.module.view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class SearchPatientScene {

  @FXML
  private TextField searchField;
  @FXML
  private ListView<String> searchResults;
  @FXML
  private Button searchButton;

  @FXML
  private void onSearchClicked() {
    String searchText = searchField.getText();
    // Implement search logic here
    System.out.println("Search for: " + searchText);
    // Assume you add search results to the ListView
    searchResults.getItems().addAll("Patient 1", "Patient 2", "Patient 3"); // Example data
  }
}
