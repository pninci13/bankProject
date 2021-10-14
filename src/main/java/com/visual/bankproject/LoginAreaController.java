package com.visual.bankproject;

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
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private TextField emailText;

    @FXML
    private PasswordField passwordText;

    public void switchToRegisterArea(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("signupArea.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, 600, 400);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToRespectiveArea(ActionEvent event) throws IOException {
        if (emailText.getText().contains("@manager-acc.com")) {
            if (loginValidation(emailText.getText(), passwordText.getText())) {
                root = FXMLLoader.load(getClass().getResource("managerArea.fxml"));
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root, 600, 400);
                stage.setScene(scene);
                stage.show();
            } else {
                Alert message = new Alert(Alert.AlertType.INFORMATION);
                message.setContentText("Invalid Login");
                message.setTitle("Unsuccessfull Login");
                message.show();
                passwordText.setText("");
            }
        }else if (emailText.getText().contains("@client-acc.com")) {
            if (loginValidation(emailText.getText(), passwordText.getText())) {
                root = FXMLLoader.load(getClass().getResource("clientArea.fxml"));
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root, 600, 400);
                stage.setScene(scene);
                stage.show();
            }else{
                Alert message = new Alert(Alert.AlertType.INFORMATION);
                message.setContentText("Invalid Login");
                message.setTitle("Unsuccessfull Login");
                message.show();
                passwordText.setText("");
            }
        }else{
            Alert message = new Alert(Alert.AlertType.INFORMATION);
            message.setContentText("Invalid Login");
            message.setTitle("Unsuccessfull Login");
            message.show();
            passwordText.setText("");
        }
    }

    public boolean loginValidation(String email, String password) throws IOException {
        File file = new File("com/visual/bankproject/bankUserInformation.txt");

        if((email == null) && (password == null)){
            return false;
        }

        Scanner scan = new Scanner(file);
        String aux;

        if(email.isEmpty() || password.isEmpty()){
            return false;
        }

        while(scan.hasNextLine()){
            aux = scan.nextLine();
            if (aux.equals(email)) {
                if (password.equals(scan.nextLine())) {
                    return true;
                } else {
                    break;
                }
            }
        }

        return false;
    }

}