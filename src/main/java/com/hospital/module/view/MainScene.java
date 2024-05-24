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

import static com.hospital.module.controller.LoginController.checkLogin;

public class MainScene {

    @FXML
    private Button receptionistBtn;

    @FXML
    private Button doctorBtn;

    @FXML
    private Button adminBtn;

    MainController mainController = new MainController();

    public static CountDownLatch latch;

    @FXML
    private void onReceptionistClicked() {
        try {
            String absolutePath = "file:/C:/Users/DELL/IdeaProjects/Hopital-Management-System/src/main/java/com/hospital/module/view/receptionist.fxml";
            URL resourceUrl = new URL(absolutePath);
            System.out.println(resourceUrl);

            if (resourceUrl != null) {
                Parent root = FXMLLoader.load(resourceUrl);

                System.out.println("I have fotten this far!!");

                // Create a new stage
                Stage newStage = new Stage();

                // Set the scene on the new stage
                Scene scene = new Scene(root);
                scene.getStylesheets().add(getClass().getResource("/css/receptionist.css").toExternalForm());


                newStage.setScene(scene);

                // Set any other properties for the new stage if needed
                newStage.setTitle("Receptionist Page");

                // Show the new stage
                newStage.show();
            } else {
                System.err.println("MainScene.fxml not found");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Caught an exception: " + e.getMessage());
            e.printStackTrace();
        }
//            loginFunction();
    }

    private void loginFunction() {
        latch = new CountDownLatch(1);

        Platform.runLater(() -> {
            try {
                String loginPath = "file:/C:/Users/DELL/IdeaProjects/Hopital-Management-System/src/main/resources/fxml/login_view.fxml";
                URL loginUrl = new URL(loginPath);
                Parent loginRoot = FXMLLoader.load(loginUrl);



                Stage newStage = new Stage();

                Scene loginScene = new Scene(loginRoot);

                newStage.setScene(loginScene);
                newStage.setTitle("Receptionist Page");

                newStage.setOnHiding(event -> {
                    latch.countDown(); // Release the latch when the login window is closed
                });

                newStage.show();
            } catch (IOException e) {
                e.printStackTrace();
                latch.countDown(); // Ensure the latch is released in case of an exception
            }
        });

        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                try {
                    latch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return null;
            }
        };

        task.setOnSucceeded(event -> {
            Platform.runLater(() -> {
                boolean check = checkLogin();
                System.out.println(check);

                if (check) {
                    try {
                        String absolutePath = "file:/C:/Users/DELL/IdeaProjects/Hopital-Management-System/src/main/java/com/hospital/module/view/receptionist.fxml";
                        URL resourceUrl = new URL(absolutePath);
                        System.out.println(resourceUrl);

                        if (resourceUrl != null) {
                            Parent root = FXMLLoader.load(resourceUrl);

                            System.out.println("I have fotten this far!!");

                            // Create a new stage
                            Stage newStage = new Stage();

                            // Set the scene on the new stage
                            Scene scene = new Scene(root);
                            scene.getStylesheets().add(getClass().getResource("/css/receptionist.css").toExternalForm());


                            newStage.setScene(scene);

                            // Set any other properties for the new stage if needed
                            newStage.setTitle("Receptionist Page");

                            // Show the new stage
                            newStage.show();
                        } else {
                            System.err.println("MainScene.fxml not found");
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (Exception e) {
                        System.out.println("Caught an exception: " + e.getMessage());
                        e.printStackTrace();
                    }
                }
            });
        });

        new Thread(task).start();
    }


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
    private void onAdminClicked() {
        try {
            String absolutePath = "file:/C:/Users/DELL/IdeaProjects/Hopital-Management-System/src/main/java/com/hospital/module/view/admin_dashboard.fxml";
            URL resourceUrl = new URL(absolutePath);
            System.out.println("URL IS" + resourceUrl);

            // Check if the resource URL is not null
            if (resourceUrl != null) {
                // Load the DoctorDashboardScene.fxml using the absolute path
                Parent root = FXMLLoader.load(resourceUrl);

                // Create a new stage
                Stage newStage = new Stage();

                // Set the scene on the new stage
                Scene scene = new Scene(root);
                scene.getStylesheets().add(getClass().getResource("/css/admin_dashboard.css").toExternalForm());
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

    }

