package com.hospital.module;


import javafx.application.Application;
import javafx.stage.Stage;
import com.hospital.module.controller.SceneManager;


public class Main extends Application {

    Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        SceneManager sceneManager = new SceneManager(primaryStage);
        sceneManager.showMainScene();
    }


    public static void main(String[] args) {
        launch(args);
    }
}

