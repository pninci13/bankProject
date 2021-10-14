package com.visual.bankproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import com.visual.bankproject.BankApplication;
import com.visual.bankproject.bankCode.Manager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class SignupAreaController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private TextField managerEmailText;

    @FXML
    private TextField newEmailText;

    @FXML
    private PasswordField managerPasswordText;

    @FXML
    private PasswordField newPasswordText;

    public void switchToLogin(ActionEvent event) throws IOException {

        if (isManagerValid(managerEmailText.getText(), managerPasswordText.getText())) {
            if (isNewValid(newEmailText.getText(), newPasswordText.getText())) {
                writeFile(newEmailText.getText(), newPasswordText.getText(), managerEmailText.getText(), managerPasswordText.getText());
            }

//            if(emailText.getText().contains("@manager-acc.com")){
//                Manager m = new Manager(emailText.getText(), passwordText.getText());
//                Main.managers.add(m);
//            }

            Alert message = new Alert(Alert.AlertType.INFORMATION);
            message.setContentText("Successfull signup");
            message.setTitle("Signup Information");
            message.show();

            root = FXMLLoader.load(getClass().getResource("LoginArea.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root, 600, 400);
            stage.setScene(scene);
            stage.show();
        } else {
            Alert message = new Alert(Alert.AlertType.INFORMATION);
            message.setContentText("failed to signup your email");
            message.setTitle("Unsuccessfull Signup");
            message.show();
        }

//        passwordText.setText("");

    }

    public boolean isManagerValid(String managerEmail, String managerPassword) throws IOException {
        if (managerEmail.isEmpty() || managerPassword.isEmpty()) {
            return false;
        }

        if (managerEmail.equals("@client-acc.com") || managerEmail.equals("@manager-acc.com")) {
            return false;
        }

        File file = new File("src\\main\\java\\com\\visual\\bankproject\\bankUserInformation.txt");

        Scanner scan = new Scanner(file);

        while (scan.hasNextLine()) {
            if (scan.nextLine().equals(managerEmail)) {
                if (scan.nextLine().equals(managerPassword)) {
                    return true;
                }
            }
        }

        return false;
    }

    public boolean isNewValid(String newEmail, String newPassword) throws IOException {
        if (newEmail.isEmpty() || newPassword.isEmpty()) {
            return false;
        }

        if (newEmail.equals("@client-acc.com") || newEmail.equals("@manager-acc.com")) {
            return false;
        }

        File file = new File("src\\main\\java\\com\\visual\\bankproject\\bankUserInformation.txt");

        Scanner scan = new Scanner(file);
        String aux;
        while (scan.hasNextLine()) {
            aux = scan.nextLine();

            if (aux.equals(newEmail)) {
                return false;
            }
        }
        return true;
    }

    public void writeFile(String newEmail, String newPassword, String managerEmail, String managerPassword) throws FileNotFoundException {
        File file = new File("src\\main\\java\\com\\visual\\bankproject\\bankUserInformation.txt");
        Scanner scan = new Scanner(file);

        String fileContent = "";

        if (newEmail.contains("@manager-acc.com")) {
            try {
                while (scan.hasNextLine()) {
                    fileContent = fileContent.concat(scan.nextLine() + "\n");
                }
                fileContent = fileContent.concat(newEmail + "\n" + newPassword);

                FileWriter writer = new FileWriter("src\\main\\java\\com\\visual\\bankproject\\bankUserInformation.txt");
                writer.write(fileContent);
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            file = new File("src\\main\\java\\com\\visual\\bankproject\\managerClients.txt");
            scan = new Scanner(file);

            try {
                while (scan.hasNextLine()) {
                    fileContent = fileContent.concat(scan.nextLine() + "\n");
                }
                fileContent = fileContent.concat(newEmail + "\n");

                FileWriter writer = new FileWriter("src\\main\\java\\com\\visual\\bankproject\\managerClients.txt");
                writer.write(fileContent);
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;


        } else if (newEmail.contains("@client-acc.com")) {
            try {
                while (scan.hasNextLine()) {
                    fileContent = fileContent.concat(scan.nextLine() + "\n");
                }
                fileContent = fileContent.concat(newEmail + "\n" + newPassword);

                FileWriter writer = new FileWriter("src\\main\\java\\com\\visual\\bankproject\\bankUserInformation.txt");
                writer.write(fileContent);
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            file = new File("src\\main\\java\\com\\visual\\bankproject\\managerClients.txt");
            scan = new Scanner(file);
            String aux;

            try {
                FileWriter writer = new FileWriter("src\\main\\java\\com\\visual\\bankproject\\managerClients.txt");

                while (scan.hasNextLine()) {
                    fileContent = fileContent.concat(scan.nextLine() + "\n");
                }

                fileContent = fileContent.concat("aux" + ";" + newEmail + "\n");

                writer.write(fileContent);
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return;
        }
    }
}