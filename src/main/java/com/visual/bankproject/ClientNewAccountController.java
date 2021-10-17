package com.visual.bankproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import com.visual.bankproject.bankCode.Saving;
import com.visual.bankproject.bankCode.Special;
import com.visual.bankproject.bankCode.Simple;
import com.visual.bankproject.LoginAreaController;
import com.visual.bankproject.BankApplication;
import com.visual.bankproject.bankCode.Client;
import com.visual.bankproject.bankCode.Manager;

import java.io.IOException;

public class ClientNewAccountController {
    private Stage stage;
    private Scene scene;
    private Parent root;


    public void createSimpleAccount(ActionEvent event) throws IOException {
        createAccount("Simple");

        Alert message = new Alert(Alert.AlertType.INFORMATION);
        message.setContentText("" +
                "Successful signup");
        message.setTitle("Successful Signup");
        message.show();

    }

    public void createSavingAccount(ActionEvent event) throws IOException {
        createAccount("Saving");

        Alert message = new Alert(Alert.AlertType.INFORMATION);
        message.setContentText("Successfull signup");
        message.setTitle("Successfull Signup");
        message.show();

    }

    public void createSpecialAccount(ActionEvent event) throws IOException {
        createAccount("Special");

        Alert message = new Alert(Alert.AlertType.INFORMATION);
        message.setContentText("Successfull signup");
        message.setTitle("Successfull Signup");
        message.show();

    }

    public void createAccount(String type) {
        if (type.equals("Simple")) {
            ((Client) LoginAreaController.userLogged).getAccounts().add(new Simple());
        } else if (type.equals("Saving")) {
            ((Client) LoginAreaController.userLogged).getAccounts().add(new Saving());
        } else if (type.equals("Special")) {
            ((Client) LoginAreaController.userLogged).getAccounts().add(new Special());
        }
    }

}
