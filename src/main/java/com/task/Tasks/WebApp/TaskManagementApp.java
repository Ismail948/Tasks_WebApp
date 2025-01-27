//package com.task.Tasks.WebApp;
//
//
//import javafx.application.Application;
//import javafx.scene.Scene;
//import javafx.scene.control.Alert;
//import javafx.scene.control.Alert.AlertType;
//import javafx.scene.layout.StackPane;
//import javafx.scene.web.WebEngine;
//import javafx.scene.web.WebView;
//import javafx.stage.Stage;
//
//public class TaskManagementApp extends Application {
//
//    @Override
//    public void start(Stage primaryStage) {
//        // Create a WebView to display web content
//        WebView webView = new WebView();
//        WebEngine webEngine = webView.getEngine();
//
//        // Load the URL of your Spring Boot application
//        String url = "http://localhost:8081/tasks";
//        webEngine.load(url);
//
//        // Check if the page is successfully loaded
//        webEngine.setOnAlert(event -> {
//            System.out.println("Alert from web page: " + event.getData());
//        });
//
//        // Add the WebView to a layout pane
//        StackPane root = new StackPane();
//        root.getChildren().add(webView);
//
//        // Set up the scene
//        Scene scene = new Scene(root, 800, 600);
//        primaryStage.setTitle("Task Management");
//        primaryStage.setScene(scene);
//
//        // Show the window
//        primaryStage.show();
//    }
//
//    public static void main(String[] args) {
//        // Start the JavaFX application
//        launch(args);
//    }
//}
