package com.hospital.module.view;

import com.hospital.module.Services.EmailService;
import com.hospital.module.Services.TimeSlotGenerator;
import com.hospital.module.db.DatabaseConnection;
import com.hospital.module.model.Doctor;
import com.hospital.module.model.Patient;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.control.DateCell;
import javafx.scene.paint.Color;
import javafx.util.Callback;

import javax.mail.MessagingException;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class CreateAppointmentScene {

  @FXML
  private TextField patientNameField;

  @FXML
  private Label patientExistenceLabel;

  @FXML
  private ComboBox<String> doctorNameField;

  @FXML
  private DatePicker datePicker;

  @FXML
  private ComboBox<LocalTime> timeSlotComboBox;

  @FXML
  private TextArea reasonField;

  @FXML
  private void initialize() {

    loadDoctorsFromDatabase();

    // Set DatePicker to only allow future dates
    restrictDatePicker();

    doctorNameField.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
      if (newValue != null && !newValue.isEmpty()) {
        loadAppointmentsFromDatabase();
      }
    });

    datePicker.valueProperty().addListener((observable, oldValue, newValue) -> {
      if (newValue != null) {
        loadAppointmentsFromDatabase();
      }
    });

//    loadAppointmentsFromDatabase();

    // Add listener to patient name field for live feedback
//    patientNameField.textProperty().addListener((observable, oldValue, newValue) -> {
//      checkPatientExists(newValue);
//    });
  }

  private void loadDoctorsFromDatabase() {
    // Simulate fetching data from database
    CompletableFuture.runAsync(() -> {
      try {
        // Simulate database access delay
        Thread.sleep(1000);

        String searchSQL = "SELECT name FROM doctor";

        // Fetch doctor names from database (simulated here with dummy data)
        ArrayList<String> doctorNames = new ArrayList<>();


        Connection conn = DatabaseConnection.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(searchSQL);

        ResultSet resultSet = pstmt.executeQuery();

        while (resultSet.next()) {
          String name = resultSet.getString("name");
          doctorNames.add(name);
        }

        System.out.println(doctorNames);

        Platform.runLater(() -> {
          doctorNameField.setItems(FXCollections.observableArrayList(doctorNames));
        });
      } catch (InterruptedException e) {
        e.printStackTrace();
      } catch (SQLException e) {
        throw new RuntimeException(e);
      }
    });
  }

  private void restrictDatePicker() {
    datePicker.setDayCellFactory(new Callback<DatePicker, DateCell>() {
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

  private void checkPatientExists(String patientName) {
    CompletableFuture.runAsync(() -> {
      try {
        Thread.sleep(500);

        Patient patient = new Patient();

        boolean patientExists = patient.checkPatientExistence("patientName");

        Platform.runLater(() -> {
          if (patientExists) {
            patientExistenceLabel.setText("Patient exists");
            patientExistenceLabel.setTextFill(Color.GREEN);
          } else {
            patientExistenceLabel.setText("Patient does not exist");
            patientExistenceLabel.setTextFill(Color.RED);
          }
        });
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    });
  }

  private void loadAppointmentsFromDatabase() {
    CompletableFuture.runAsync(() -> {
      try {
        // Simulate database access delay
        Thread.sleep(1000);

        String selectedDoctor = doctorNameField.getValue();
        LocalDate selectedDate = datePicker.getValue();

        if (selectedDoctor == null || selectedDoctor.isEmpty() || selectedDate == null) {
          // Either doctor or date is not selected, do nothing
          return;
        }
        int doctorId = Doctor.getDoctorId(selectedDoctor);
        List<Timestamp> bookedAppointments = Doctor.getAllDoctorAppointments(doctorId);

        List<LocalTime> allSlots = TimeSlotGenerator.generateTimeSlots(LocalTime.of(8, 30), LocalTime.of(17, 0), 20);

        System.out.println(allSlots);


        List<LocalTime> bookedSlots = bookedAppointments.stream()
                .map(timestamp -> timestamp.toLocalDateTime().toLocalTime())
                .collect(Collectors.toList());

        System.out.println(bookedSlots);

        List<LocalTime> availableSlots = allSlots.stream()
                .filter(slot -> !bookedSlots.contains(slot))
                .collect(Collectors.toList());

        System.out.println(availableSlots);

        Platform.runLater(() -> {
          timeSlotComboBox.setItems(FXCollections.observableArrayList(availableSlots));
        });

      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    });
  }

  @FXML
  private void onBookAppointmentClicked() {
    System.out.println("Book Appointment Button Clicked");
    CompletableFuture.runAsync(() -> {
      try {
        // Retrieve form values
        String patientName = patientNameField.getText();
        String doctorName = doctorNameField.getValue();
        LocalDate appointmentDate = datePicker.getValue();
        LocalTime appointmentTime = timeSlotComboBox.getValue();
        String reason = reasonField.getText();

        // Check if all fields are filled
        if (patientName.isEmpty() || doctorName == null || appointmentDate == null || appointmentTime == null) {
          Platform.runLater(() -> {
            showAlert("Error", "Please fill in all required fields.", Alert.AlertType.ERROR);
          });
          return;
        }

//        // Check if patient exists
        System.out.println(patientName);

        if (!Patient.checkPatientExistence(patientName)) {
          Platform.runLater(() -> {
            showAlert("Error", "Patient does not exist.", Alert.AlertType.ERROR);
          });
          return;
        }

        // Fetch IDs
        int doctorId = Doctor.getDoctorId(doctorName);
        int patientId = Patient.getPatientId(patientName);


        // Check if the selected slot is still available
        if (!isSlotAvailable(doctorId, Timestamp.valueOf(appointmentDate.atTime(appointmentTime)))) {
          Platform.runLater(() -> {
            showAlert("Error", "The selected time slot is no longer available.", Alert.AlertType.ERROR);
          });
          return;
        }

        // Insert new appointment into database
        insertAppointment(doctorId, patientId, Timestamp.valueOf(appointmentDate.atTime(appointmentTime)), reason);

        Platform.runLater(() -> {
          showAlert("Appointment Created!", "Appointment has been created successfully!", Alert.AlertType.ERROR);
        });

        Platform.runLater(() -> {
          EmailService emailService = new EmailService("musemasaad3@gmail.com", "H@rveySpect0r");
            try {
                emailService.sendEmail("saadmusema3@gmail.com", "Hello", "This is a trail!!");
            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }
            showAlert("Success", "Appointment successfully booked.", Alert.AlertType.INFORMATION);
        });
      } catch (SQLException e) {
        e.printStackTrace();
      }
    });
  }

  private boolean isSlotAvailable(int doctorId, Timestamp appointmentTimestamp) throws SQLException {
    String query = "SELECT COUNT(*) FROM appointment WHERE doctor_id = ? AND appointment_date = ?";
    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(query)) {
      pstmt.setInt(1, doctorId);
      pstmt.setTimestamp(2, appointmentTimestamp);
      ResultSet resultSet = pstmt.executeQuery();
      if (resultSet.next()) {
        return resultSet.getInt(1) == 0;
      }
    }
    return false;
  }

  private void insertAppointment(int doctorId, int patientId, Timestamp appointmentTimestamp, String reason) throws SQLException {
    String insertSQL = "INSERT INTO appointment (doctor_id, patient_id, appointment_date, reason) VALUES (?, ?, ?, ?)";
    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {
      pstmt.setInt(1, doctorId);
      pstmt.setInt(2, patientId);
      pstmt.setTimestamp(3, appointmentTimestamp);
      pstmt.setString(4, reason);
      pstmt.executeUpdate();
    }
  }

  private void showAlert(String title, String message, Alert.AlertType alertType) {
    Alert alert = new Alert(alertType);
    alert.setTitle(title);
    alert.setHeaderText(null);
    alert.setContentText(message);
    alert.showAndWait();
  }

}
