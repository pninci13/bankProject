package com.visual.bankproject;

import com.visual.bankproject.bankCode.Account;
import com.visual.bankproject.bankCode.Client;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ClientDepositController implements Initializable {

    public static String myAccount;

    @FXML
    public ChoiceBox<String> accountOptions;

    @FXML
    public TextField amountValue;

//    public static String[] accounts = new String[10];

    public static ObservableList<String> accounts = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        accounts.addListener(new ListChangeListener<String>() {
            @Override
            public void onChanged(Change<? extends String> change) {
                accountOptions.getItems().setAll(accounts);
            }
        });
        accountOptions.setOnAction(this::changeAccount);
    }

    public static void update() {
        Client loggedClient = (Client) LoginAreaController.userLogged;
        String accountType;
        int accountNumber;

        accounts.clear();
        for (int i = 0; i < loggedClient.getAccounts().size(); i++) {
            accountType = loggedClient.getAccounts().get(i).getAccountType();
            accountNumber = loggedClient.getAccounts().get(i).getAccountNumber();
            accounts.add(accountType + "\t" + accountNumber);
//            accounts[i] = (accountType + "\t" + accountNumber);
        }
    }

    public void changeAccount(ActionEvent event) {
        myAccount = accountOptions.getValue();
        System.out.println(myAccount);
    }

    public void onDepositButtonClick(ActionEvent event) {
        String[] number = myAccount.split("\t");

        for (int i = 0; i < ((Client) LoginAreaController.userLogged).getAccounts().size(); i++) {
            if (String.valueOf(((Client) LoginAreaController.userLogged).getAccounts().get(i).getAccountNumber()).equals(number[1])) {
                Float amount = Float.parseFloat(amountValue.getText());
//                System.out.println(amount);
                ((Client)LoginAreaController.userLogged).getAccounts().get(i).deposit(amount);
                System.out.println(((Client) LoginAreaController.userLogged).getAccounts().get(i).getBalance());

                break;
            }
        }
        System.out.println(number[1]);
    }
}
