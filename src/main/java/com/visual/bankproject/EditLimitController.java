package com.visual.bankproject;

import com.visual.bankproject.bankCode.Account;
import com.visual.bankproject.bankCode.Client;
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

public class EditLimitController implements Initializable {

    public static String limitAccount;
    public static String rateAccount;

    @FXML
    public ChoiceBox<String> limitBox;

    @FXML
    public ChoiceBox<String> rateBox;

    @FXML
    public TextField limitText;

    @FXML
    public TextField rateText;

    public static ObservableList<String> savingAccounts = FXCollections.observableArrayList();
    public static ObservableList<String> specialAccounts = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        savingAccounts.addListener(new ListChangeListener<String>() {
            @Override
            public void onChanged(Change<? extends String> change) {
                rateBox.getItems().setAll(savingAccounts);
            }
        });
        rateBox.setOnAction(this::changeLimitAccount);

        specialAccounts.addListener(new ListChangeListener<String>() {
            @Override
            public void onChanged(Change<? extends String> change) {
                limitBox.getItems().setAll(specialAccounts);
            }
        });
        limitBox.setOnAction(this::changeRateAccount);
    }

    public void changeLimitAccount(ActionEvent event) {
        limitAccount = limitBox.getValue();
    }

    public void changeRateAccount(ActionEvent event) {
        rateAccount = rateBox.getValue();
    }

    public static void updateLimit() {
        Client loggedClient = (Client) LoginAreaController.userLogged;
        String accountType;
        int accountNumber;

        specialAccounts.clear();
        for (int i = 0; i < loggedClient.getAccounts().size(); i++) {
            accountType = loggedClient.getAccounts().get(i).getAccountType();
            accountNumber = loggedClient.getAccounts().get(i).getAccountNumber();
            specialAccounts.add(accountType + "     " + accountNumber);
//            accounts[i] = (accountType + "\t" + accountNumber);
        }
    }

    public static void updateRate() {
        Client loggedClient = (Client) LoginAreaController.userLogged;
        String accountType;
        int accountNumber;

        savingAccounts.clear();
        for (int i = 0; i < loggedClient.getAccounts().size(); i++) {
            accountType = loggedClient.getAccounts().get(i).getAccountType();
            accountNumber = loggedClient.getAccounts().get(i).getAccountNumber();
            savingAccounts.add(accountType + "     " + accountNumber);
        }
    }

//    public void onWithdrawButtonClick(ActionEvent event) {
//        String[] number = myAccount.split("     ");
//        Float value = Float.parseFloat(amountValue.getText());
//
//        if(!amountValue.getText().isEmpty() && value > 0) {
//            if(value <= 110000){
//                for (int i = 0; i < ((Client) LoginAreaController.userLogged).getAccounts().size(); i++) {
//                    Account account = ((Client) LoginAreaController.userLogged).getAccounts().get(i);
//                    if (String.valueOf(account.getAccountNumber()).equals(number[1])) {
//                        if(account.withdraw(value)){
//                            Image image = new Image(Objects.requireNonNull(getClass().getResource("images/money-withdrawal.png")).toExternalForm());
//                            ImageView imageView = new ImageView(image);
//                            imageView.setFitWidth(64);
//                            imageView.setFitHeight(64);
//
//                            Alert message = new Alert(Alert.AlertType.INFORMATION);
//                            message.setContentText("Withdraw done successfully");
//                            message.setTitle("Successful Withdraw");
//                            message.setGraphic(imageView);
//                            message.showAndWait();
//
//                            amountValue.setText("");
//                            accountOptions.setValue("");
//                            return;
//                        }else{
//                            Image image = new Image(Objects.requireNonNull(getClass().getResource("images/alert.png")).toExternalForm());
//                            ImageView imageView = new ImageView(image);
//                            imageView.setFitWidth(64);
//                            imageView.setFitHeight(64);
//
//                            Alert message = new Alert(Alert.AlertType.INFORMATION);
//                            message.setContentText("It was not possible to withdraw your value");
//                            message.setTitle("Unsuccessful Withdraw");
//                            message.setGraphic(imageView);
//                            message.showAndWait();
//                            amountValue.setText("");
//                            accountOptions.setValue("");
//                            return;
//                        }
//                    }
//                }
//            }else{
//                Image image = new Image(Objects.requireNonNull(getClass().getResource("images/alert.png")).toExternalForm());
//                ImageView imageView = new ImageView(image);
//                imageView.setFitWidth(64);
//                imageView.setFitHeight(64);
//
//                Alert message = new Alert(Alert.AlertType.INFORMATION);
//                message.setContentText("Amount is higher than your range! ");
//                message.setTitle("Unsuccessful Withdraw");
//                message.setGraphic(imageView);
//                message.showAndWait();
//                amountValue.setText("");
//                accountOptions.setValue("");
//            }
//        }else{
//            Image image = new Image(Objects.requireNonNull(getClass().getResource("images/alert.png")).toExternalForm());
//            ImageView imageView = new ImageView(image);
//            imageView.setFitWidth(64);
//            imageView.setFitHeight(64);
//
//            Alert message = new Alert(Alert.AlertType.INFORMATION);
//            message.setContentText("It was not possible to withdraw your value");
//            message.setTitle("Unsuccessful Withdraw");
//            message.setGraphic(imageView);
//            message.showAndWait();
//            amountValue.setText("");
//            accountOptions.setValue("");
//        }
//    }
}
