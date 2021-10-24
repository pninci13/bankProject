package com.visual.bankproject;

import com.visual.bankproject.bankCode.Account;
import com.visual.bankproject.bankCode.Client;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ClientMyStatementController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private ChoiceBox<String> clientAccount;

    @FXML
    private ListView<Float> listView;

    public static ObservableList<String> information = FXCollections.observableArrayList();

    String myAccount;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        information.addListener(new ListChangeListener<String>() {
            @Override
            public void onChanged(Change<? extends String> change) {
                clientAccount.getItems().setAll(information);
            }
        });

        clientAccount.setOnAction(this::changeAccount);
    }

    public static void update() {
        Client loggedClient = (Client) LoginAreaController.userLogged;
        String accountType;
        int accountNumber;

        information.clear();
        System.out.println(loggedClient.getAccounts().size());
        for (int i = 0; i < loggedClient.getAccounts().size(); i++) {
            accountType = loggedClient.getAccounts().get(i).getAccountType();
            accountNumber = loggedClient.getAccounts().get(i).getAccountNumber();
            information.add(accountType + "     " + accountNumber);
//            accounts[i] = (accountType + "\t" + accountNumber);
        }
    }

    public void changeAccount(ActionEvent event){
        myAccount = clientAccount.getValue();
        listView.getItems().clear();

        if(myAccount != null) {
            for (int i = 0; i < ((Client) LoginAreaController.userLogged).getAccounts().size() ; i++) {
                Account account = ((Client) LoginAreaController.userLogged).getAccounts().get(i);
                String[] number = myAccount.split("     ");
                String finalNumber = String.valueOf(account.getAccountNumber());

                if(number.length == 1){
                    return;
                }

                if(number[1].equals(finalNumber)) {
                    for (int j = 0; j < account.getAccountStatement().size(); j++) {
                        listView.getItems().add(account.getAccountStatement().get(j));
                    }
                }

            }
        }
    }

    public void switchToLogin(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("loginArea.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, 600, 400);
        stage.setScene(scene);
        stage.show();
    }
}
