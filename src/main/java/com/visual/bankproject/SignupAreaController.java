package com.visual.bankproject;

import com.visual.bankproject.bankCode.Client;
import com.visual.bankproject.bankCode.Manager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import com.visual.bankproject.BankApplication;
import com.visual.bankproject.bankCode.Client;
import com.visual.bankproject.bankCode.Manager;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;

public class SignupAreaController implements Initializable{
    private Stage stage;
    private Scene scene;
    private Parent root;
    String myAccount;

    public static ArrayList<Client> clients = new ArrayList<>();
    public static ArrayList<Manager> managers = new ArrayList<>();

    @FXML
    private TextField usernameText;

    @FXML
    private PasswordField passwordText;

    @FXML
    private ChoiceBox<String> clientOptions;

    private String[] accountOptions = {"Client", "Manager"};

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        clientOptions.getItems().addAll(accountOptions);
        clientOptions.setOnAction(this::changeAccount);
    }

    public void changeAccount(ActionEvent event){
        myAccount = clientOptions.getValue();
    }

    public void switchToLogin(ActionEvent event) throws IOException {

        if(isSignupValid(usernameText.getText(), passwordText.getText(), myAccount)){
            System.out.println(myAccount);
            root = FXMLLoader.load(getClass().getResource("managerArea.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root, 600, 400);
            stage.setScene(scene);
            stage.show();

            Alert message = new Alert (Alert.AlertType.INFORMATION);
            message.setContentText("Successful signup");
            message.setTitle("Successful Signup");
            message.show();


        }else{
            Alert message = new Alert (Alert.AlertType.INFORMATION);
            message.setContentText("failed to signup your email");
            message.setTitle("Unsuccessful Signup");
            message.show();

        }
    }

    public boolean isSignupValid(String newUsername, String newPassword, String myAccount) throws IOException{
        File file = new File("src\\main\\java\\com\\visual\\bankproject\\bankUserInformation.txt");
        Scanner scan = new Scanner(file);

        if(newUsername.isEmpty() || newPassword.isEmpty()){
            return false;
        }

        while (scan.hasNextLine()) {
            scan.nextLine();
            if (scan.nextLine().equals(newUsername)) {
                return false;
            }
            scan.nextLine();
        }

        if(myAccount.equals("Client")){
            Client client = new Client(newUsername, newPassword);
            SignupAreaController.clients.add(client);
            ((Manager)LoginAreaController.userLogged).getClients().add(client);

        }else if(myAccount.equals("Manager")){
            Manager manager = new Manager(newUsername, newPassword);
            SignupAreaController.managers.add(manager);
        }

        file = new File("src\\main\\java\\com\\visual\\bankproject\\bankUserInformation.txt");
        scan = new Scanner(file);

        String fileContent = "";

        try {
            while(scan.hasNextLine()){
                fileContent = fileContent.concat(scan.nextLine() + "\n");
            }
            fileContent = fileContent.concat(myAccount + "\n" + newUsername + "\n" + newPassword);

            FileWriter writer = new FileWriter("src\\main\\java\\com\\visual\\bankproject\\bankUserInformation.txt");
            writer.write(fileContent);
            writer.close();
        } catch(IOException e){
            e.printStackTrace();
        }

        return true;
    }
}