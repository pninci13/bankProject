package com.visual.bankproject;

import com.visual.bankproject.bankCode.Account;
import com.visual.bankproject.bankCode.Client;
import com.visual.bankproject.bankCode.Manager;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;


public class TransferController implements Initializable {

    @FXML
    public ChoiceBox<String> fromBox;

    @FXML
    public ChoiceBox<String> toBox;

    @FXML
    public ChoiceBox<String> fromAccount;

    @FXML
    public ChoiceBox<String> toAccount;

    @FXML
    public TextField amountText;

    @FXML
    public Button transferButton;

    public static ObservableList<String> selectedFromAccounts = FXCollections.observableArrayList();
    public static ObservableList<String> selectedToAccounts = FXCollections.observableArrayList();
    public static ObservableList<String> clientNames = FXCollections.observableArrayList();

    public String fromName;
    public String toName;
    public String fromNumber;
    public String toNumber;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        clientNames.addListener(new ListChangeListener<String>() {
            @Override
            public void onChanged(Change<? extends String> change) {
                fromBox.getItems().setAll(clientNames);
            }
        });
        fromBox.setOnAction(this::changeFromAccountName);

        selectedFromAccounts.addListener(new ListChangeListener<String>() {
            @Override
            public void onChanged(Change<? extends String> change) {
                fromAccount.getItems().setAll(selectedFromAccounts);
            }
        });
        fromAccount.setOnAction(this::changeFromAccount);

        clientNames.addListener(new ListChangeListener<String>() {
            @Override
            public void onChanged(Change<? extends String> change) {
                toBox.getItems().setAll(clientNames);
            }
        });
        toBox.setOnAction(this::changeToAccountName);

        selectedToAccounts.addListener(new ListChangeListener<String>() {
            @Override
            public void onChanged(Change<? extends String> change) {
                toAccount.getItems().setAll(selectedToAccounts);
            }
        });
        toAccount.setOnAction(this::changeToAccount);
    }

    public void changeFromAccountName(ActionEvent event) {
        fromName = fromBox.getValue();
        updateFrom(fromName);
    }

    public void changeToAccountName(ActionEvent event) {
        toName = toBox.getValue();
        updateTo(toName);
    }

    public void changeFromAccount(ActionEvent event) {
        fromNumber = fromAccount.getValue();

    }

    public void changeToAccount(ActionEvent event) {
        toNumber = toAccount.getValue();
    }

    public static void updateTo(String name) {
        Manager loggedManager = (Manager) LoginAreaController.userLogged;
        String accountType;
        int accountNumber;
        selectedToAccounts.clear();
        for (int i = 0; i < loggedManager.getClients().size(); i++) {
            Client client = loggedManager.getClients().get(i);
            if (client.getEmail().equals(name)) {
                for (int j = 0; j < client.getAccounts().size(); j++) {
                    accountType = client.getAccounts().get(j).getAccountType();
                    accountNumber = client.getAccounts().get(j).getAccountNumber();
                    selectedToAccounts.add(accountType + "\t" + accountNumber);
                }
            }
        }
    }

    public static void updateFrom(String name) {
        Manager loggedManager = (Manager) LoginAreaController.userLogged;
        String accountType;
        int accountNumber;
        selectedFromAccounts.clear();
        for (int i = 0; i < loggedManager.getClients().size(); i++) {
            Client client = loggedManager.getClients().get(i);
            if (client.getEmail().equals(name)) {
                for (int j = 0; j < client.getAccounts().size(); j++) {
                    accountType = client.getAccounts().get(j).getAccountType();
                    accountNumber = client.getAccounts().get(j).getAccountNumber();
                    selectedFromAccounts.add(accountType + "\t" + accountNumber);
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


    public void onTransferButtonClick(ActionEvent event) {
        Account account1, account2;
        Float value = Float.parseFloat(amountText.getText());
        if (!amountText.getText().isEmpty()) {
            if (Float.parseFloat(amountText.getText()) > 0) {
                account1 = isClientValid(fromNumber);
                account2 = isClientValid(toNumber);
                if (account1 != null && account2 != null) {
                    account1.withdraw(value);
                    account2.deposit(value);

                    Image image = new Image(Objects.requireNonNull(getClass().getResource("images/money-transfer.png")).toExternalForm());
                    ImageView imageView = new ImageView(image);
                    imageView.setFitWidth(64);
                    imageView.setFitHeight(64);
                    Alert message = new Alert(Alert.AlertType.INFORMATION);
                    message.setContentText("Transfer Successful");
                    message.setTitle("Successful Transfer");
                    message.show();

                    fromAccount.setValue("");
                    toAccount.setValue("");
                    fromBox.setValue("");
                    toBox.setValue("");
                    amountText.setText("");

                    return;
                }
            }
        }
        Image image = new Image(Objects.requireNonNull(getClass().getResource("images/alert.png")).toExternalForm());
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(64);
        imageView.setFitHeight(64);

        Alert message = new Alert(Alert.AlertType.INFORMATION);
        message.setContentText("You cannot deposit this value");
        message.setTitle("Unsuccessful Deposit");
        message.show();

        fromAccount.setValue("");
        toAccount.setValue("");
        fromBox.setValue("");
        toBox.setValue("");
        amountText.setText("");
    }

    public Account isClientValid(String selectedAccount) {
        String[] fromInfo = selectedAccount.split("\t");
//        String[] toInfo = toOption.split("\t");
        Account account;
        Client client;

        for (int i = 0; i < ((Manager) LoginAreaController.userLogged).getClients().size(); i++) {
            client = ((Manager) LoginAreaController.userLogged).getClients().get(i);
            for (int j = 0; j < client.getAccounts().size(); j++) {
                account = client.getAccounts().get(j);
                if (String.valueOf(account.getAccountNumber()).equals(fromInfo[1])) {
                    return account;
                }
            }
        }
        return null;
    }
}

