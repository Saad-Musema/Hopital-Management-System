//package com.hospital.module.controller;
//
//import com.hospital.module.db.DatabaseConnection;
//import com.hospital.module.model.Appointment;
//import com.sun.tools.javac.util.List;
//
//import java.sql.*;
//import java.util.ArrayList;
//
//public class AppointmentController {
//
////    public List<Appointment> getAppointmentsByDoctor(int doctorId) {
////        List<Appointment> appointments = new ArrayList<>();
////
////        try (Connection connection = DatabaseConnection.getConnection();
////             PreparedStatement statement = connection.prepareStatement("SELECT * FROM appointment WHERE doctor_id = ?")) {
////
////            statement.setInt(1, doctorId);
////            ResultSet resultSet = statement.executeQuery();
////
////            while (resultSet.next()) {
////                int appointmentId = resultSet.getInt("appointment_id");
////                int patientId = resultSet.getInt("patient_id");
////                Timestamp appointmentDate = resultSet.getTimestamp("appointment_date");
////                String reason = resultSet.getString("reason");
////                String status = resultSet.getString("status");
////
////                Appointment appointment = new Appointment(appointmentId, doctorId, patientId, appointmentDate, reason, status);
////                appointments.add(appointment);
////            }
////        } catch (SQLException e) {
////            e.printStackTrace();
////        } catch (SQLException e) {
////            throw new RuntimeException(e);
////        }
////
////        return appointments;
//    }
//
//
//}
