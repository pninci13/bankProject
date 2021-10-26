package com.visual.bankproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import com.visual.bankproject.bankCode.Account;
//import sample.bankCode.Simple;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ClientAreaController{
//    @FXML
//    private ChoiceBox<String> clientOptions;

    private Stage stage;
    private Scene scene;
    private Parent root;

    //lista de contas que o cliente possui
    private String[] accountOptions = {"Simple", "Special", "Saving"};

//    @Override
//    public void initialize(URL url, ResourceBundle resourceBundle) {
//        clientOptions.getItems().addAll(accountOptions);
//        clientOptions.setOnAction(this::changeAccount);
//    }

//    public void changeAccount(ActionEvent event){
//        String myAccount = clientOptions.getValue();
//        System.out.println(myAccount);
//
//
////        if(myAccount.equals("Simple")){
////            Simple account = new Simple();
////        }
//    }

    public void onClientLogoutButtonClick(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("loginArea.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, 600, 400);
        stage.setScene(scene);
        stage.show();
    }

    public void onClientCreatedAnotherAccount(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("clientTabs/my-statement.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, 600, 400);
        stage.setScene(scene);
        stage.show();
    }
}