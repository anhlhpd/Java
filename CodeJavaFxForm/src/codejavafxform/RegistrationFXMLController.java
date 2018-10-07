/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codejavafxform;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javax.swing.AbstractButton;

/**
 *
 * @author Phuong Anh
 */
public class RegistrationFXMLController implements Initializable {
    
    @FXML
    private Label label;
    @FXML
    private Label username;
    @FXML
    private Label password;
    @FXML
    private Label email;
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField usernameField;
    @FXML
    private TextField emailField;
    @FXML
    private Button btnSubmit;
    @FXML
    private Button btnReset;
    
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
    private void open_registration() throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("RegistrationFXML.fxml"));
        RegistrationFXMLController.this;
        Scene scene = new Scene(root);
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
