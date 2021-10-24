package com.visual.bankproject;

import com.visual.bankproject.bankCode.*;
import javafx.beans.Observable;
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
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

public class ClientWithdrawController implements Initializable {

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

    public void changeAccount(ActionEvent event) {
        System.out.println(accountOptions.getItems());
        myAccount = accountOptions.getValue();
        System.out.println(myAccount);
    }

    public static void update() {
        Client loggedClient = (Client) LoginAreaController.userLogged;
        String accountType;
        int accountNumber;

        accounts.clear();
        for (int i = 0; i < loggedClient.getAccounts().size(); i++) {
            accountType = loggedClient.getAccounts().get(i).getAccountType();
            accountNumber = loggedClient.getAccounts().get(i).getAccountNumber();
            accounts.add(accountType + "     " + accountNumber);
//            accounts[i] = (accountType + "\t" + accountNumber);
        }
    }

    public void onWithdrawButtonClick(ActionEvent event) {
        String[] number = myAccount.split("     ");
        Float value = Float.parseFloat(amountValue.getText());

        if(!amountValue.getText().isEmpty() && value > 0) {
            if(value <= 110000){
                for (int i = 0; i < ((Client) LoginAreaController.userLogged).getAccounts().size(); i++) {
                    Account account = ((Client) LoginAreaController.userLogged).getAccounts().get(i);
                    if (String.valueOf(account.getAccountNumber()).equals(number[1])) {
                        account.withdraw(value);

                        Image image = new Image(Objects.requireNonNull(getClass().getResource("images/money-withdrawal.png")).toExternalForm());
                        ImageView imageView = new ImageView(image);
                        imageView.setFitWidth(64);
                        imageView.setFitHeight(64);

                        Alert message = new Alert(Alert.AlertType.INFORMATION);
                        message.setContentText("Withdraw done successfully");
                        message.setTitle("Successful Withdraw");
                        message.setGraphic(imageView);
                        message.showAndWait();

                        amountValue.setText("");
                        accountOptions.setValue("");
                        break;
                    }
                }
            }else{
                Image image = new Image(Objects.requireNonNull(getClass().getResource("images/alert.png")).toExternalForm());
                ImageView imageView = new ImageView(image);
                imageView.setFitWidth(64);
                imageView.setFitHeight(64);

                Alert message = new Alert(Alert.AlertType.INFORMATION);
                message.setContentText("Amount is higher than your range! ");
                message.setTitle("Unsuccessful Withdraw");
                message.setGraphic(imageView);
                message.showAndWait();
                amountValue.setText("");
                accountOptions.setValue("");
            }
        }else{
            Image image = new Image(Objects.requireNonNull(getClass().getResource("images/alert.png")).toExternalForm());
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(64);
            imageView.setFitHeight(64);

            Alert message = new Alert(Alert.AlertType.INFORMATION);
            message.setContentText("It was not possible to withdraw your value");
            message.setTitle("Unsuccessful Withdraw");
            message.setGraphic(imageView);
            message.showAndWait();
            amountValue.setText("");
            accountOptions.setValue("");
        }
    }
}
