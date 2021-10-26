package com.visual.bankproject;

import com.visual.bankproject.bankCode.Account;
import com.visual.bankproject.bankCode.Client;
import com.visual.bankproject.bankCode.Manager;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class ManagerInvestmentController implements Initializable {


    public static String myAccount;
    public static String myNames;

    @FXML
    public ChoiceBox<String> accountOptions;

    @FXML
    public ChoiceBox<String> nameOptions;

    @FXML
    public TextField amountValue;

    public static ObservableList<String> clientNames = FXCollections.observableArrayList();
    public static ObservableList<String> accounts = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        clientNames.addListener(new ListChangeListener<String>() {
            @Override
            public void onChanged(Change<? extends String> change) {
                nameOptions.getItems().setAll(clientNames);
            }
        });
        nameOptions.setOnAction(this::changeName);

        accounts.addListener(new ListChangeListener<String>() {
            @Override
            public void onChanged(Change<? extends String> change) {
                accountOptions.getItems().setAll(accounts);
            }
        });
        accountOptions.setOnAction(this::changeAccount);
    }

    public void changeAccount(ActionEvent event) {
        myAccount = accountOptions.getValue();
    }

    public void changeName(ActionEvent event) {
        myNames = nameOptions.getValue();
        updateAccounts(myNames);
    }

    public static void updateAccounts(String name) {
        Manager loggedManager = (Manager) LoginAreaController.userLogged;
        String accountType;
        int accountNumber;
        accounts.clear();
        for (int i = 0; i < loggedManager.getClients().size(); i++) {
            Client client = loggedManager.getClients().get(i);
            if (client.getEmail().equals(name)) {
                for (int j = 0; j < client.getAccounts().size(); j++) {
                    accountType = client.getAccounts().get(j).getAccountType();
                    accountNumber = client.getAccounts().get(j).getAccountNumber();
                    accounts.add(accountType + "     " + accountNumber);
                }
            }
        }
    }

    public static void updateNames() {
        Manager loggedManager = (Manager) LoginAreaController.userLogged;
        String accountName = null;

        clientNames.clear();
        for (int i = 0; i < loggedManager.getClients().size(); i++) {
            Client client = loggedManager.getClients().get(i);
            accountName = client.getEmail();
            clientNames.add(accountName);
        }
    }

    public void onManagerDepositButtonClick(ActionEvent event) {
        String[] number = myAccount.split("     ");
        if (amountValue.getText().isEmpty()) {
            Image image = new Image(Objects.requireNonNull(getClass().getResource("images/alert.png")).toExternalForm());
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(64);
            imageView.setFitHeight(64);

            Alert message = new Alert(Alert.AlertType.INFORMATION);
            message.setContentText("Inputs are empty! Try Again! ");
            message.setTitle("Unsuccessful Withdraw");
            message.setGraphic(imageView);
            message.showAndWait();
            amountValue.setText("");
            accountOptions.setValue("");
            nameOptions.setValue("");
            return;
        }

        Manager currentManager = (Manager) LoginAreaController.userLogged;
        Float value = Float.parseFloat(amountValue.getText());
        if (value > 0) {
            for (int i = 0; i < currentManager.getClients().size(); i++) {
                Client client = currentManager.getClients().get(i);
                for (int j = 0; j < client.getAccounts().size(); j++) {
                    Account account = client.getAccounts().get(j);
                    if (String.valueOf(account.getAccountNumber()).equals(number[1])) {
                        account.deposit(value);
                        Image image = new Image(Objects.requireNonNull(getClass().getResource("images/money-withdrawal.png")).toExternalForm());
                        ImageView imageView = new ImageView(image);
                        imageView.setFitWidth(64);
                        imageView.setFitHeight(64);

                        Alert message = new Alert(Alert.AlertType.INFORMATION);
                        message.setContentText("Deposit done successfully");
                        message.setTitle("Successful Deposit");
                        message.setGraphic(imageView);
                        message.showAndWait();

                        amountValue.setText("");
                        accountOptions.setValue("");
                        nameOptions.setValue("");
                        return;
                    } else {
                        amountValue.setText("");
                        accountOptions.setValue("");
                        nameOptions.setValue("");
                        return;
                    }
                }
            }
        } else {
            Image image = new Image(Objects.requireNonNull(getClass().getResource("images/alert.png")).toExternalForm());
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(64);
            imageView.setFitHeight(64);

            Alert message = new Alert(Alert.AlertType.INFORMATION);
            message.setContentText("Amount Invalid! ");
            message.setTitle("Unsuccessful Deposit");
            message.setGraphic(imageView);
            message.showAndWait();
            amountValue.setText("");
            accountOptions.setValue("");
            nameOptions.setValue("");
        }
    }
}