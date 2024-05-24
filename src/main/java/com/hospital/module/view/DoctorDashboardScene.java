package com.hospital.module.view;

import com.hospital.module.db.DatabaseConnection;
import com.hospital.module.model.Appointment;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import static com.hospital.module.model.Doctor.getAllDoctorAppointments;
import static com.hospital.module.model.Doctor.getDoctorAppointments;

public class DoctorDashboardScene {

  @FXML
  private ListView<Appointment> appointmentsListView;

  @FXML
  private Button writePrescriptionButton;

  @FXML
  private Button addDiagnosisButton;

  @FXML
  private void initialize() {
    loadAppointmentsFromDatabase();
  }

  private void loadAppointmentsFromDatabase() {
    CompletableFuture.runAsync(() -> {
      try {
        // Simulate database access delay
        Thread.sleep(1000);

        int doctorId = 1; // Replace with the actual doctor's ID

        List<Appointment> appointments = getDoctorAppointments(doctorId);

        Collections.sort(appointments, Comparator.comparing(Appointment::getAppointmentDate));

        Platform.runLater(() -> {
          ObservableList<Appointment> observableList = FXCollections.observableArrayList(appointments);
          appointmentsListView.setItems(observableList);
          appointmentsListView.setCellFactory(new Callback<ListView<Appointment>, ListCell<Appointment>>() {
            @Override
            public ListCell<Appointment> call(ListView<Appointment> param) {
              return new AppointmentCell();
            }
          });
        });
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    });
  }

  private List<Appointment> fetchDoctorAppointments(int doctorId) throws SQLException {
    List<Appointment> appointments = new ArrayList<>();

    String query = "SELECT * FROM appointment WHERE doctor_id = ?";

    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(query)) {
      pstmt.setInt(1, doctorId);

      try (ResultSet resultSet = pstmt.executeQuery()) {
        while (resultSet.next()) {
          int appointmentId = resultSet.getInt("appointment_id");
          int patientId = resultSet.getInt("patient_id");
          Timestamp appointmentDate = resultSet.getTimestamp("appointment_date");
          String reason = resultSet.getString("reason");
          String status = resultSet.getString("status");

          appointments.add(new Appointment(appointmentId, doctorId, patientId, appointmentDate, reason, status));
        }
      }
    }

    return appointments;
  }

  @FXML
  private void onWritePrescription() {
    try {
      System.out.println("Navigate to write prescription scene");

      // Define the path to the FXML file
      String absolutePath = "file:/C:/Users/DELL/IdeaProjects/Hopital-Management-System/src/main/java/com/hospital/module/view/writePrescription.fxml";
      URL resourceUrl = new URL(absolutePath);
      System.out.println(resourceUrl);

      if (resourceUrl != null) {
        // Load the FXML file
        Parent root = FXMLLoader.load(resourceUrl);

        // Create a new stage
        Stage newStage = new Stage();

        // Set the scene on the new stage
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/css/WritePrescription.css").toExternalForm());

        newStage.setScene(scene);

        // Set any other properties for the new stage if needed
        newStage.setTitle("Write Prescription Page");

        // Show the new stage
        newStage.show();
      } else {
        System.err.println("write_prescription.fxml not found");
      }
    } catch (IOException e) {
      e.printStackTrace();
    } catch (Exception e) {
      System.out.println("Caught an exception: " + e.getMessage());
      e.printStackTrace();
    }

  }

  @FXML
  private void onAddDiagnosis() {
    try {
      System.out.println("Navigate to write prescription scene");

      // Define the path to the FXML file
      String absolutePath = "file:/C:/Users/DELL/IdeaProjects/Hopital-Management-System/src/main/java/com/hospital/module/view/writeDocument.fxml";
      URL resourceUrl = new URL(absolutePath);
      System.out.println(resourceUrl);

      if (resourceUrl != null) {
        // Load the FXML file
        Parent root = FXMLLoader.load(resourceUrl);

        // Create a new stage
        Stage newStage = new Stage();

        // Set the scene on the new stage
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/css/WriteDocument.css").toExternalForm());

        newStage.setScene(scene);

        // Set any other properties for the new stage if needed
        newStage.setTitle("Write Prescription Page");

        // Show the new stage
        newStage.show();
      } else {
        System.err.println("write_prescription.fxml not found");
      }
    } catch (IOException e) {
      e.printStackTrace();
    } catch (Exception e) {
      System.out.println("Caught an exception: " + e.getMessage());
      e.printStackTrace();
    }
  }

  static class AppointmentCell extends ListCell<Appointment> {
    @Override
    protected void updateItem(Appointment appointment, boolean empty) {
      super.updateItem(appointment, empty);
      if (appointment != null) {
        Button changeStatusButton = new Button("Change Status");
        changeStatusButton.setOnAction(event -> changeAppointmentStatus(appointment));

        setText("Appointment with Patient ID: " + appointment.getPatientId() +
                " on " + appointment.getAppointmentDate() + "\nReason: " + appointment.getReason());

        setGraphic(changeStatusButton);
      } else {
        setText(null);
        setGraphic(null);
      }
    }

    private void changeAppointmentStatus(Appointment appointment) {
      CompletableFuture.runAsync(() -> {
        try {
          String newStatus = "Completed"; // Change to the desired new status

          String updateSQL = "UPDATE appointment SET status = ? WHERE appointment_id = ?";

          try (Connection conn = DatabaseConnection.getConnection();
               PreparedStatement pstmt = conn.prepareStatement(updateSQL)) {
            pstmt.setString(1, newStatus);
            pstmt.setInt(2, appointment.getAppointmentId());
            pstmt.executeUpdate();
          }

          Platform.runLater(() -> {
            appointment.setStatus(newStatus);
            updateItem(appointment, false);
          });

        } catch (SQLException e) {
          e.printStackTrace();
        }
      });
    }
  }
}
