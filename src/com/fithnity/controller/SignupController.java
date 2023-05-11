/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fithnity.controller;



import com.fithnity.entity.User;
import com.fithnity.service.ServiceUser;
import java.awt.Canvas;
import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Statement;
import java.util.Random;
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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.util.Duration;
import org.mindrot.jbcrypt.BCrypt;

/**
 * FXML Controller class
 *
 * @author Maaouia
 */
public class SignupController implements Initializable {

    @FXML
    private AnchorPane container;
    @FXML
    private Button signup;
    @FXML
    private TextField firstname_text;
    @FXML
    private TextField lastname_text;
    @FXML
    private TextField email_text;
    @FXML
    private PasswordField password_text;
    @FXML
    private Hyperlink login;
    @FXML
    private ImageView logo1;
    @FXML
    private PasswordField confirm_password_text;
    
    ServiceUser Us = ServiceUser.getInstance();
    @FXML
    private Button add_avatar;
    @FXML
    private ImageView avatar;
    
    private String selectedAvatar;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void signup(ActionEvent event) {
        if(validateInputs()==true){
           
        String hashedPassword = hashPassword(password_text.getText());
        User u = new User(firstname_text.getText(), lastname_text.getText(), email_text.getText(),hashedPassword,selectedAvatar);
            
            Us.insert(u);
            System.out.println(u);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Your account has been created successfully!");
        alert.show();
        firstname_text.setText("");
        lastname_text.setText("");
        email_text.setText("");
        password_text.setText("");
        confirm_password_text.setText("");
        avatar.setImage(null);
        
        
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("/com/fithnity/view/Login.fxml"));
       
        Scene scene = login.getScene();
        root.translateXProperty().set(scene.getWidth());

        AnchorPane parentContainer = (AnchorPane) login.getScene().getRoot();

        parentContainer.getChildren().add(root);

        Timeline timeline = new Timeline();
        KeyValue kv = new KeyValue(root.translateXProperty(), 0 , Interpolator.EASE_OUT);
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
        
    }

    @FXML
    private void login(ActionEvent event) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("/com/fithnity/view/Login.fxml"));
       
        Scene scene = login.getScene();
        root.translateXProperty().set(scene.getWidth());

        AnchorPane parentContainer = (AnchorPane) login.getScene().getRoot();

        parentContainer.getChildren().add(root);

        Timeline timeline = new Timeline();
        KeyValue kv = new KeyValue(root.translateXProperty(), 0 , Interpolator.EASE_OUT);
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
   
    
    public boolean validateInputs() {
    
        if (firstname_text.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR!");
            alert.setHeaderText(null);
            alert.setContentText("Missing Firstname!");
            alert.show();
            return false;
        }

        if (!firstname_text.getText().matches("[a-zA-Z\\s]+")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR!");
            alert.setHeaderText(null);
            alert.setContentText("Invalid Firstname! Only alphabetic characters are allowed.");
            alert.show();
            return false;
        }

        if (lastname_text.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR!");
            alert.setHeaderText(null);
            alert.setContentText("Missing Lastname!");
            alert.show();
            return false;
        }

        if (!lastname_text.getText().matches("[a-zA-Z\\s]+")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR!");
            alert.setHeaderText(null);
            alert.setContentText("Invalid Lastname! Only alphabetic characters are allowed.");
            alert.show();
            return false;
        }

        if (email_text.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR!");
            alert.setHeaderText(null);
            alert.setContentText("Missing Email!");
            alert.show();
            return false;
        }

        if (!email_text.getText().matches("[\\w.-]+@[\\w.-]+\\.[A-Za-z]{2,6}")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR!");
            alert.setHeaderText(null);
            alert.setContentText("Invalid Email!");
            alert.show();
            return false;
        }

        if(Us.verif_email(email_text.getText())==false){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR!");
            alert.setHeaderText(null);
            alert.setContentText("Email Already Used!");
            alert.show();
            return false;

        }

        if (password_text.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR!");
            alert.setHeaderText(null);
            alert.setContentText("Missing Password!");
            alert.show();
            return false;
        }

        if (!password_text.getText().matches("^(?=.*[0-9]).{8,}$")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR!");
            alert.setHeaderText(null);
            alert.setContentText("Password should be at least 8 characters and contain at least one digit [0-9] only!");
            alert.show();
            return false;
        }

        if (confirm_password_text.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR!");
            alert.setHeaderText(null);
            alert.setContentText("Missing Confirmed Password!");
            alert.show();
            return false;
        }

        if (!password_text.getText().equals(confirm_password_text.getText())) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR!");
            alert.setHeaderText(null);
            alert.setContentText("Passwords do not match!");
            alert.show();
            return false;
        }
        
        if (avatar.getImage() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR!");
            alert.setHeaderText(null);
            alert.setContentText("Missing Avatar!");
            alert.show();
            return false;
        }

        return true;
       }
    
    
    private String hashPassword(String password) {
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        return hashedPassword;
    }

    @FXML
    private void add_avatar(ActionEvent event) {
        
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose File");
        fileChooser.setInitialDirectory(new File("C:/xampp/htdocs/GestionUser/GestionUser/public/uploads/user_images/"));

        File selectedFile = fileChooser.showOpenDialog(null);

        if (selectedFile != null) {
            String fileName = selectedFile.getName(); // Get the file name
            Image img = new Image(selectedFile.toURI().toString());

            selectedAvatar = fileName; // Save the file name only
            avatar.setImage(img);
        }
    
    }

   
  

    
    
    }
