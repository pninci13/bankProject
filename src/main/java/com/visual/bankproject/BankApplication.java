package com.visual.bankproject;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import com.visual.bankproject.bankCode.Client;
import com.visual.bankproject.bankCode.Manager;

import java.io.IOException;
import java.util.ArrayList;

public class BankApplication extends Application {
    public static ArrayList<Client> clients = new ArrayList<>();
    public static ArrayList<Manager> managers = new ArrayList<>();
//    public static //colocar o logged user

    @Override
    public void start(Stage primaryStage) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("loginArea.fxml"));
        primaryStage.setTitle("Menu");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

//        for (int i = 0; i < users.size(); i++) {
//            Manager m = users.get(i);
//
//            if (m.abc.equals("email que eu digitei"))
//                userLogged = m;
//        }
//
//        Simple contaASerCriada = new Simple();
//
//        ((Client) userLogged).getBankAccounts().add(contaASerCriada);


//    @Override
//    public void clientScene(Stage clientScene) throws Exception{
//        Parent root = FXMLLoader.load(getClass().getResource("clientLoginScreen.fxml"));
//        clientScene.setTitle("Hello World");
//        clientScene.setScene(new Scene(root, 331, 573));
//        clientScene.setResizable(false);
//        clientScene.show();
//    }

//    public void switchToClientArea(ActionEvent event) throws IOException {
//
//        if(loginValidation(emailText.getText(), passwordText.getText())){
//            root = FXMLLoader.load(getClass().getResource("../client/clientArea.fxml"));
//            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
//            scene = new Scene(root, 600, 400);
//            stage.setScene(scene);
//            stage.show();
//        }else{
//            Alert message = new Alert (Alert.AlertType.INFORMATION);
//            message.setContentText("Invalid Login");
//            message.setTitle("Unsuccesful Login");
//            message.show();
//        }
//
//        passwordText.setText("");
//    }
//
//    public void switchToSignupArea(ActionEvent event) throws IOException {
//        root = FXMLLoader.load(getClass().getResource("../allSignups/clientSignup.fxml"));
//        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
//        scene = new Scene(root, 600, 400);
//        stage.setScene(scene);
//        stage.show();
//
//    }