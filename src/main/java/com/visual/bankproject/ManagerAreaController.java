package com.visual.bankproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.stage.Stage;

import java.io.IOException;

public class ManagerAreaController {
    public Tab clientsTab;
    public Tab accountInfoTab;
    public Tab depositToClientTab;
    public Tab transferTab;
    public Tab limitTab;
    public Tab newAccountTab;
    public Tab changePasswordTab;
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void switchToLogin(ActionEvent event) throws IOException {

        root = FXMLLoader.load(getClass().getResource("loginArea.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, 600, 400);
        stage.setScene(scene);
        stage.show();
    }

    public void onManagerLogoutButtonClick(ActionEvent event){
        try{
            root = FXMLLoader.load(getClass().getResource("loginArea.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root, 600, 400);
            stage.setScene(scene);
            stage.show();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

}