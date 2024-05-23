package com.hospital.module.view;

import com.hospital.module.controller.LoginController;
import com.hospital.module.controller.MainController;
import com.hospital.module.controller.SceneManager;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;
import javafx.concurrent.Task;
import java.util.concurrent.CountDownLatch;

public class MainScene {

    @FXML
    private Button receptionistBtn;

    @FXML
    private Button doctorBtn;

    @FXML
    private Button nurseBtn;

    MainController mainController = new MainController();

    private CountDownLatch latch;

    @FXML
    private void onReceptionistClicked() {
        try {
            loginFunction();

            // Wait for the login function to complete
            latch.await();

            boolean check = LoginController.checkLogin();
            System.out.println(check);

            if (check) {
                String absolutePath = "file:/C:/Users/DELL/IdeaProjects/Hopital-Management-System/src/main/java/com/hospital/module/view/receptionist.fxml";
                URL resourceUrl = new URL(absolutePath);
                System.out.println(resourceUrl);

                if (resourceUrl != null) {
                    Parent root = FXMLLoader.load(resourceUrl);

                    // Create a new stage
                    Stage newStage = new Stage();

                    // Set the scene on the new stage
                    Scene scene = new Scene(root);

                    newStage.setScene(scene);

                    // Set any other properties for the new stage if needed
                    newStage.setTitle("Receptionist Page");

                    // Show the new stage
                    newStage.show();
                } else {
                    System.err.println("receptionist.fxml not found");
                }
            } else {
                System.out.println("Login failed, cannot proceed to the receptionist page.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Caught an exception: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void loginFunction() {
        latch = new CountDownLatch(1);

        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                try {
                    String loginPath = "file:/C:/Users/DELL/IdeaProjects/Hopital-Management-System/src/main/resources/fxml/login_view.fxml";
                    URL loginUrl = new URL(loginPath);
                    Parent loginRoot = FXMLLoader.load(loginUrl);



                    Platform.runLater(() -> {
                        Stage newStage = new Stage();
                        Scene loginScene = new Scene(loginRoot);

                        newStage.setScene(loginScene);
                        newStage.setTitle("Receptionist Page");

                        newStage.setOnHiding(event -> {
                            latch.countDown(); // Release the latch when the login window is closed
                        });

                        newStage.show();
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                    latch.countDown(); // Ensure the latch is released in case of an exception
                } catch (Exception e) {
                    System.out.println("Caught an exception: " + e.getMessage());
                    e.printStackTrace();
                    latch.countDown(); // Ensure the latch is released in case of an exception
                }
                return null;
            }
        };

        new Thread(task).start();
    }
//        private void loginFunction() throws IOException {
//           try {
//               String loginPath = "file:/C:/Users/DELL/IdeaProjects/Hopital-Management-System/src/main/resources/fxml/login_view.fxml";
//               URL loginUrl = new URL(loginPath);
//               Parent loginRoot = FXMLLoader.load(loginUrl);
//
//               Stage newwStage = new Stage();
//               Scene loginScene = new Scene(loginRoot);

//               newwStage.setScene(loginScene);
//
//               newwStage.setTitle("Receptionist Page");
//
//
//               newwStage.show();
//
//           }catch (IOException e) {
//               e.printStackTrace();
//           } catch (Exception e) {
//               System.out.println("Caught an exception: " + e.getMessage());
//               e.printStackTrace();
//           }
//        };

        @FXML
        private void onDoctorClicked() {
            try {
                String absolutePath = "file:/C:/Users/DELL/IdeaProjects/Hopital-Management-System/src/main/java/com/hospital/module/view/DoctorDashboardScene.fxml";
                URL resourceUrl = new URL(absolutePath);
                System.out.println(resourceUrl);

                // Check if the resource URL is not null
                if (resourceUrl != null) {
                    // Load the DoctorDashboardScene.fxml using the absolute path
                    Parent root = FXMLLoader.load(resourceUrl);

                    // Create a new stage
                    Stage newStage = new Stage();

                    // Set the scene on the new stage
                    Scene scene = new Scene(root);
                    scene.getStylesheets().add(getClass().getResource("/css/DoctorDashboardScene.css").toExternalForm());
                    newStage.setScene(scene);

                    // Set any other properties for the new stage if needed
                    newStage.setTitle("Doctor Dashboard");

                    // Show the new stage
                    newStage.show();
                } else {
                    System.err.println("DoctorDashboardScene.fxml not found");
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                System.out.println("Caught an exception: " + e.getMessage());
                e.printStackTrace();
            }
            System.out.println("Doctor button clicked");
        }


    @FXML
        private void onNurseClicked() {
            try {
                // Load the nurse dashboard FXML file
                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/hospital/module/view/NurseDashboardScene.fxml")));

                // Create a new stage
                Stage newStage = new Stage();

                // Set the scene on the new stage
                Scene scene = new Scene(root);
                newStage.setScene(scene);

                // Set any other properties for the new stage if needed
                newStage.setTitle("Nurse Dashboard");

                // Show the new stage
                newStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                System.out.println("Caught an exception: " + e.getMessage());
                e.printStackTrace();
            }
            System.out.println("Nurse button clicked");
        }

    }

