/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fithnity.controller;


import com.fithnity.entity.User;
import com.fithnity.services.ServiceUser;
import com.fithnity.services.UserManager;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;    
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Maaouia
 */
public class LoginController implements Initializable {

    @FXML
    private TextField email_text;
    @FXML
    private PasswordField password_text;
    @FXML
    private AnchorPane container;
    @FXML
    private Button login;
    @FXML
    private ImageView logo;
    @FXML
    private Hyperlink signup;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }    

    @FXML
    private void login(ActionEvent event) {
            String mail = email_text.getText();
            String pass = password_text.getText();
            ServiceUser Su = ServiceUser.getInstance();
            User user = new User();
            
            
            if (mail.isEmpty() || pass.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Please enter a username and password.");
                alert.show();
                return;
            }

            else if (Su.verif_user(mail,pass) == true) {
                // Successful login
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Login Successful!");
                alert.show();
                
                
                User current = Su.getCurrentUser(mail, pass);
                 
                UserManager.setCurrentUser(current);
                 
                            
                    if(current.getAdmin()==1){
                        try {
                              Parent page1 = FXMLLoader.load(getClass().getResource("/com/fithnity/view/AdminPanel.fxml"));
                              Scene scene = new Scene(page1);
                              Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                              stage.setScene(scene);
                              stage.show();
                          } 
                        catch (IOException ex) {
                              Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                          }     
                    }
                    else {
                        try {  
                              Parent page1 = FXMLLoader.load(getClass().getResource("/com/fithnity/view/Profile.fxml"));
                              Scene scene = new Scene(page1);
                              Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                              stage.setScene(scene);
                              stage.show();
                          } 
                       catch (IOException ex) {
                              Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                          }
                    }
                
            } else {
                // Incorrect username or password
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Incorrect mail or password!");
                alert.show();
            }
    }


    @FXML
    private void signup(ActionEvent event) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("/com/fithnity/view/Signup.fxml"));
       
        Scene scene = signup.getScene();
        root.translateXProperty().set(-scene.getWidth());

        AnchorPane parentContainer = (AnchorPane) signup.getScene().getRoot();

        parentContainer.getChildren().add(root);

        Timeline timeline = new Timeline();
        KeyValue kv = new KeyValue(root.translateXProperty(), 0, Interpolator.EASE_OUT);
        KeyFrame kf = new KeyFrame(Duration.seconds(1), kv);
        timeline.getKeyFrames().add(kf);
        timeline.setOnFinished(t -> {
            parentContainer.getChildren().remove(container);
        });
        timeline.play(); 
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void forget_pass(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("You can't solve that yet!");
        alert.show();
    }
    
    


}
