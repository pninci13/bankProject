package com.visual.bankproject;

import com.visual.bankproject.bankCode.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class LoginAreaController {
    public static User userLogged;

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private TextField usernameText;

    @FXML
    private PasswordField passwordText;

    public void switchToRegisterArea(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("signupArea.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root, 600, 400);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToRespectiveArea(ActionEvent event) throws IOException {
        if (loginValidation(usernameText.getText(), passwordText.getText()) == 0) {
            Alert message = new Alert(Alert.AlertType.INFORMATION);
            message.setContentText("Invalid Login");
            message.setTitle("Unsuccessful Login");
            message.show();
            passwordText.setText("");
        } else if (loginValidation(usernameText.getText(), passwordText.getText()) == 1) {
            root = FXMLLoader.load(getClass().getResource("clientArea.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root, 600, 400);
            stage.setScene(scene);
            stage.show();
        } else if (loginValidation(usernameText.getText(), passwordText.getText()) == 2) {
            root = FXMLLoader.load(getClass().getResource("managerArea.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root, 600, 400);
            stage.setScene(scene);
            stage.show();
        }
    }

    public int loginValidation(String username, String password) {
        for (int i = 0; i < SignupAreaController.clients.size(); i++) {
            System.out.println("AAAAAAA");
            userLogged = SignupAreaController.clients.get(i);
            if (username.equals(userLogged.getEmail()) && password.equals(userLogged.getPassword())) {
                return 1;
            }
        }

        for (int i = 0; i < SignupAreaController.managers.size(); i++) {
            System.out.println("BBBBB");
            userLogged = SignupAreaController.managers.get(i);
            if (username.equals(userLogged.getEmail()) && password.equals(userLogged.getPassword())) {
                return 2;
            }
        }

        return 0;
    }
}


