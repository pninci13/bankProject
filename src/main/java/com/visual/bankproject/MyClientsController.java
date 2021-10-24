package com.visual.bankproject;

import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Callback;
import com.visual.bankproject.SignupAreaController;
import com.visual.bankproject.bankCode.Account;
import com.visual.bankproject.bankCode.Client;;
import com.visual.bankproject.bankCode.Manager;;
import com.visual.bankproject.bankCode.Simple;;
import com.visual.bankproject.LoginAreaController;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class MyClientsController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    public TreeView<String> treeView;

    @FXML
    private ListView<Account> clientList;

    @FXML
    private ListView<Float> listView;

    int accountIndex;
    int selectedClient;

//    public List<Account> accountList = new ArrayList<>(Arrays.asList(new Simple(), new Simple()));

    public void initialize() {

        Manager currentManager = (Manager) LoginAreaController.userLogged;

        for (Client client : currentManager.getClients()) {
            TreeItem<String> clientTreeItem = new TreeItem<>(client.getEmail());

            for (Account account : client.getAccounts()) {
                TreeItem<String> accountTreeItem = new TreeItem<>(account.getAccountType() + "     " + account.getAccountNumber() + "     " + account.getBalance());
                clientTreeItem.getChildren().add(accountTreeItem);
            }

            treeView.getRoot().getChildren().add(clientTreeItem);
        }
    }

    public void onManagerLogoutButtonClick(ActionEvent event) {
        try {
            root = FXMLLoader.load(getClass().getResource("loginArea.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root, 600, 400);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void selectedItem() {
        TreeItem<String> item = treeView.getSelectionModel().getSelectedItem();
        listView.getItems().clear();

        if (item != null) {
            for (int i = 0; i < ((Manager) LoginAreaController.userLogged).getClients().size(); i++) {
                Client client = ((Manager) LoginAreaController.userLogged).getClients().get(i);
                for (int j = 0; j < client.getAccounts().size(); j++) {
                    String[] number = item.getValue().split("     ");
                    String finalNumber = String.valueOf(client.getAccounts().get(j).getAccountNumber());

                    if (number.length == 1) {
                        return;
                    }

                    if (number[1].equals(finalNumber)) {
                        accountIndex = j;
                        selectedClient = i;
                        for (int k = 0; k < client.getAccounts().get(j).getAccountStatement().size(); k++) {
                            listView.getItems().add(client.getAccounts().get(accountIndex).getAccountStatement().get(k));
                        }
                    }
                }
            }
        }
    }
}