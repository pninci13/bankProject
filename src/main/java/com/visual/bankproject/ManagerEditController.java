package com.visual.bankproject;

import com.visual.bankproject.bankCode.*;
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

public class ManagerEditController implements Initializable {

    @FXML
    public ChoiceBox<String> limitBox;

    @FXML
    public ChoiceBox<String> rateBox;

    @FXML
    public ChoiceBox<String> rateAccountBox;

    @FXML
    public ChoiceBox<String> limitAccountBox;

    @FXML
    public TextField limitText;

    @FXML
    public TextField rateText;

    public static ObservableList<String> savingAccounts = FXCollections.observableArrayList();
    public static ObservableList<String> specialAccounts = FXCollections.observableArrayList();
    public static ObservableList<String> clientNames = FXCollections.observableArrayList();

    public String clientRateName;
    public String clientLimitName;

    public String limitAccount;
    public String rateAccount;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        clientNames.addListener(new ListChangeListener<String>() {
            @Override
            public void onChanged(Change<? extends String> change) {
                limitBox.getItems().setAll(clientNames);
            }
        });
        limitBox.setOnAction(this::changeLimitAccountName);

        clientNames.addListener(new ListChangeListener<String>() {
            @Override
            public void onChanged(Change<? extends String> change) {
                rateBox.getItems().setAll(clientNames);
            }
        });
        rateBox.setOnAction(this::changeRateAccountName);

        savingAccounts.addListener(new ListChangeListener<String>() {
            @Override
            public void onChanged(Change<? extends String> change) {
                rateAccountBox.getItems().setAll(savingAccounts);
            }
        });
        rateAccountBox.setOnAction(this::changeRateAccount);

        specialAccounts.addListener(new ListChangeListener<String>() {
            @Override
            public void onChanged(Change<? extends String> change) {
                limitAccountBox.getItems().setAll(specialAccounts);
            }
        });
        limitAccountBox.setOnAction(this::changeLimitAccount);
    }

    private void changeRateAccountName(ActionEvent actionEvent) {
        clientRateName = rateBox.getValue();
        updateRate(clientRateName);
    }

    private void changeLimitAccountName(ActionEvent actionEvent) {
        clientLimitName = limitBox.getValue();
        updateLimit(clientLimitName);
    }

    public void changeLimitAccount(ActionEvent event) {
        limitAccount = limitAccountBox.getValue();
    }

    public void changeRateAccount(ActionEvent event) {
        rateAccount = rateAccountBox.getValue();
    }

    public static void updateLimit(String name) {
        Manager loggedManager = (Manager) LoginAreaController.userLogged;
        String accountType;
        int accountNumber;

        specialAccounts.clear();
        for (int i = 0; i < loggedManager.getClients().size(); i++) {
            Client client = loggedManager.getClients().get(i);
            if (client.getEmail().equals(name)) {
                for (int j = 0; j < client.getAccounts().size(); j++) {
                    accountType = client.getAccounts().get(j).getAccountType();
                    accountNumber = client.getAccounts().get(j).getAccountNumber();
                    specialAccounts.add(accountType + "     " + accountNumber);
                }
            }
        }
    }

    public static void updateRate(String name) {
        Manager loggedManager = (Manager) LoginAreaController.userLogged;
        String accountType;
        int accountNumber;

        savingAccounts.clear();
        for (int i = 0; i < loggedManager.getClients().size(); i++) {
            Client client = loggedManager.getClients().get(i);
            if (client.getEmail().equals(name)) {
                for (int j = 0; j < client.getAccounts().size(); j++) {
                    accountType = client.getAccounts().get(j).getAccountType();
                    accountNumber = client.getAccounts().get(j).getAccountNumber();
                    savingAccounts.add(accountType + "     " + accountNumber);
                }
            }
        }
    }

    public static void updateNames() {
        Manager loggedManager = (Manager) LoginAreaController.userLogged;
        String accountName;

        clientNames.clear();
        for (int i = 0; i < loggedManager.getClients().size(); i++) {
            Client client = loggedManager.getClients().get(i);
            accountName = client.getEmail();
            clientNames.add(accountName);
        }
    }

    public void onLimitButtonClick(ActionEvent event) {
        Account account1;
        Float value = Float.parseFloat(limitText.getText());
        if (!limitText.getText().isEmpty()) {
            if (Float.parseFloat(limitText.getText()) > 0) {
                account1 = isAccountSpecial(limitAccount);
                if (account1 != null) {
                    ((Special) account1).setLimit(value);
                    Image image = new Image(Objects.requireNonNull(getClass().getResource("images/check.png")).toExternalForm());
                    ImageView imageView = new ImageView(image);
                    imageView.setFitWidth(64);
                    imageView.setFitHeight(64);
                    Alert message = new Alert(Alert.AlertType.INFORMATION);
                    message.setContentText("Limit Changed :)");
                    message.setTitle("Limit Change Alert");
                    message.show();

                    return;

                } else {
                    Image image = new Image(Objects.requireNonNull(getClass().getResource("images/alert.png")).toExternalForm());
                    ImageView imageView = new ImageView(image);
                    imageView.setFitWidth(64);
                    imageView.setFitHeight(64);
                    Alert message = new Alert(Alert.AlertType.INFORMATION);
                    message.setContentText("Selected account is not valid");
                    message.setTitle("Your limit could not be applied");
                    message.show();
                }
            } else {
                Image image = new Image(Objects.requireNonNull(getClass().getResource("images/alert.png")).toExternalForm());
                ImageView imageView = new ImageView(image);
                imageView.setFitWidth(64);
                imageView.setFitHeight(64);
                Alert message = new Alert(Alert.AlertType.INFORMATION);
                message.setContentText("Your value must be higher than 0");
                message.setTitle("Invalid Value");
                message.show();
            }
        } else {
            Image image = new Image(Objects.requireNonNull(getClass().getResource("images/alert.png")).toExternalForm());
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(64);
            imageView.setFitHeight(64);
            Alert message = new Alert(Alert.AlertType.INFORMATION);
            message.setContentText("Text box is empty");
            message.setTitle("Invalid Value");
            message.show();
        }
    }

    public void onRateButtonClick(ActionEvent event) {
        Account account1;
        Float value = Float.parseFloat(rateText.getText());
        if (!rateText.getText().isEmpty()) {
            if (Float.parseFloat(rateText.getText()) > 0) {
                account1 = isAccountSaving(rateAccount);
                if (account1 != null) {
                    ((Saving) account1).setAccountInterestRate(value);
                    Image image = new Image(Objects.requireNonNull(getClass().getResource("images/check.png")).toExternalForm());
                    ImageView imageView = new ImageView(image);
                    imageView.setFitWidth(64);
                    imageView.setFitHeight(64);
                    Alert message = new Alert(Alert.AlertType.INFORMATION);
                    message.setContentText("Interest Rate Changed :)");
                    message.setTitle("Interest Rate Alert");
                    message.show();
                } else {
                    Image image = new Image(Objects.requireNonNull(getClass().getResource("images/alert.png")).toExternalForm());
                    ImageView imageView = new ImageView(image);
                    imageView.setFitWidth(64);
                    imageView.setFitHeight(64);
                    Alert message = new Alert(Alert.AlertType.INFORMATION);
                    message.setContentText("Selected account is not valid");
                    message.setTitle("Your interest rate could not be applied");
                    message.show();
                }
            } else {
                Image image = new Image(Objects.requireNonNull(getClass().getResource("images/alert.png")).toExternalForm());
                ImageView imageView = new ImageView(image);
                imageView.setFitWidth(64);
                imageView.setFitHeight(64);
                Alert message = new Alert(Alert.AlertType.INFORMATION);
                message.setContentText("Your value must be higher than 0");
                message.setTitle("Invalid Value");
                message.show();
            }
        } else {
            Image image = new Image(Objects.requireNonNull(getClass().getResource("images/alert.png")).toExternalForm());
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(64);
            imageView.setFitHeight(64);
            Alert message = new Alert(Alert.AlertType.INFORMATION);
            message.setContentText("Text box is empty");
            message.setTitle("Invalid Value");
            message.show();
        }

    }

    public Account isAccountSpecial(String selectedAccount) {

        String[] fromInfo = selectedAccount.split("     ");
        Account account;
        Client client;

        for (int i = 0; i < ((Manager) LoginAreaController.userLogged).getClients().size(); i++) {
            client = ((Manager) LoginAreaController.userLogged).getClients().get(i);
            for (int j = 0; j < client.getAccounts().size(); j++) {
                account = client.getAccounts().get(j);
                if (String.valueOf(account.getAccountNumber()).equals(fromInfo[1])) {
                    if (String.valueOf(account.getAccountType()).equals(fromInfo[0]) && String.valueOf(account.getAccountType()).equals("Special")) {
                        return account;
                    } else {
                        return null;
                    }
                }
            }
        }

        return null;
    }

    public Account isAccountSaving(String selectedAccount) {
        String[] fromInfo = selectedAccount.split("     ");
        Account account;
        Client client;

        for (int i = 0; i < ((Manager) LoginAreaController.userLogged).getClients().size(); i++) {
            client = ((Manager) LoginAreaController.userLogged).getClients().get(i);
            for (int j = 0; j < client.getAccounts().size(); j++) {
                account = client.getAccounts().get(j);
                if (String.valueOf(account.getAccountType()).equals(fromInfo[0]) && String.valueOf(account.getAccountType()).equals("Saving")) {
                    return account;
                }
            }
        }

        return null;
    }

}