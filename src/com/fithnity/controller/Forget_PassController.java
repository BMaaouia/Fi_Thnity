/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fithnity.controller;

import com.fithnity.services.ServiceUser;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * FXML Controller class
 *
 * @author Maaouia
 */
public class Forget_PassController implements Initializable {

    @FXML
    private TextField email_text;
    @FXML
    private Button send;
    ServiceUser Us = ServiceUser.getInstance();
    @FXML
    private AnchorPane email_pane;
    @FXML
    private AnchorPane code_pane;
    @FXML
    private Button check;
    @FXML
    private TextField code_text;
    @FXML
    private AnchorPane newpass_pane;
    @FXML
    private Button save_pass;
    @FXML
    private PasswordField newpass_text;
    @FXML
    private PasswordField confirm_newpass_text;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        email_pane.setVisible(true);
        code_pane.setVisible(false);
        newpass_pane.setVisible(false);
         }    

    @FXML
    private void send_mail(ActionEvent event) {
        
        
        if(Validate_email()==true){
        String to = email_text.getText();
        String from = "brmaaouia@gmail.com";
        String host = "smtp.gmail.com";
        String port = "587";
        String username = "brmaaouia@gmail.com";
        String password = "zufsoqnjziwwpwrn";

        // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        // Get the default Session object.
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            // Set Subject: header field
            message.setSubject("Reset your password");

            // Generate a random verification code and save it in a database or cache
            String verificationCode = generateVerificationCode();
            
            // Store the verification code in a variable accessible by the verification code check method
            String[] verificationCodeHolder = new String[1];
            verificationCodeHolder[0] = verificationCode;

            // Set the message content
            message.setText("Here's your verification code: " + verificationCode);

            // Send message
            Transport.send(message);
            System.out.println("Verification email sent successfully.");
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText(null);
            alert.setContentText("A verification code has been sent to your email address. Please check your inbox and follow the instructions to reset your password.");
            alert.showAndWait();
            email_pane.setVisible(false);
            code_pane.setVisible(true);
            newpass_pane.setVisible(false);
            //System.out.println(verificationCodeHolder[0]); 
            
            check.setOnAction(e->{
            if (code_text.getText().equals(verificationCodeHolder[0])) {
                // The verification code is correct, proceed with password reset
                // TODO: Add code for password reset
                Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("The verification code is correct. You may now reset your password.");
                alert.showAndWait();
                email_pane.setVisible(false);
                code_pane.setVisible(false);
                newpass_pane.setVisible(true);
                    
                
                save_pass.setOnAction(e1->{
                    //System.out.println(email_text.getText());
                    if(Validate_pass()==true){
                    
                        Us.update_pass(newpass_text.getText(), email_text.getText());
                        
                        Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Information");
                        alert.setHeaderText(null);
                        alert.setContentText("You have reset your password. Connect with your new Login!");
                        alert.showAndWait();


                        try {  
                            Parent page1 = FXMLLoader.load(getClass().getResource("/com/fithnity/view/Login.fxml"));
                            Scene scene = new Scene(page1);
                            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            stage.setScene(scene);
                            stage.show();
                             } 
                        catch (IOException ex) {
                            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                    
                    }
                
                });
                
                
                
                
            } else {
                // The verification code is incorrect, show error message
                Alert alert1 = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("The verification code is incorrect. Please try again.");
                alert.showAndWait();
            }
            });
            
            
            } catch (MessagingException mex) {
            mex.printStackTrace();
            }
        }
    }
    



public String generateVerificationCode() {
        // Generate a random 6-digit code
        int code = (int) (Math.random() * 900000) + 100000;
        return String.valueOf(code);
    }


public Boolean Validate_email(){
        
        if (email_text.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR!");
            alert.setHeaderText(null);
            alert.setContentText("Missing Email!");
            alert.show();
            return false;
        }


        if(Us.verif_email(email_text.getText())==false){
            
            return true;

        }
        
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("ERROR!");
    alert.setHeaderText(null);
    alert.setContentText("Wrong Email!");
    alert.show();
    return false;  
}


public Boolean Validate_pass(){
        
        if (newpass_text.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR!");
            alert.setHeaderText(null);
            alert.setContentText("Missing Password!");
            alert.show();
            return false;
        }

        if (!newpass_text.getText().matches("^(?=.*[0-9]).{8,}$")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR!");
            alert.setHeaderText(null);
            alert.setContentText("Password should be at least 8 characters and contain at least one digit [0-9] only!");
            alert.show();
            return false;
        }

        if (confirm_newpass_text.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR!");
            alert.setHeaderText(null);
            alert.setContentText("Missing Password!");
            alert.show();
            return false;
        }

        if (!newpass_text.getText().equals(confirm_newpass_text.getText())) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR!");
            alert.setHeaderText(null);
            alert.setContentText("Passwords do not match!");
            alert.show();
            return false;
        }

        return true;
}
   


}