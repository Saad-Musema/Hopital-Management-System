package com.hospital.module;

import com.hospital.module.db.DatabaseConnection;
import com.hospital.module.model.Receptionist;
import com.hospital.module.controller.ReceptionistController;
import javafx.application.Application;
import javafx.stage.Stage;
import com.hospital.module.controller.SceneManager;

import java.sql.Connection;
import java.sql.SQLException;

public class Main extends Application {




    @Override
    public void start(Stage primaryStage) throws Exception {
        SceneManager sceneManager = new SceneManager(primaryStage);
        sceneManager.showMainScene();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
