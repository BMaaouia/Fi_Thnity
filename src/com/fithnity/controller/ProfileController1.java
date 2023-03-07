/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fithnity.controller;

import com.fithnity.entity.Subscription;
import com.fithnity.services.ServiceUser;
import com.fithnity.entity.User;
import com.fithnity.entity.UserSubscription;
import com.fithnity.services.ServiceSubscription;
import com.fithnity.services.ServiceUserSubscription;
import com.fithnity.services.UserManager;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Maaouia
 */
public class ProfileController1 implements Initializable {

    @FXML
    private Text user;
    @FXML
    private AnchorPane slider;
    @FXML
    private AnchorPane profile;
    @FXML
    private TextField firstname_text;
    @FXML
    private TextField lastname_text;
    @FXML
    private TextField email_text;
    @FXML
    private Button update;
    @FXML
    private PasswordField password_text;
    @FXML     
    private Button save;
    @FXML
    private Button show_profile;
    
    
    
    TranslateTransition slide = new TranslateTransition();
    ServiceUser Su = ServiceUser.getInstance();
    User current =UserManager.getCurrentUser();
    Subscription s = new Subscription();
    ServiceSubscription Ss = ServiceSubscription.getInstance();
    UserSubscription US = new UserSubscription();
    ServiceUserSubscription SUS = ServiceUserSubscription.getInstance();
    
    @FXML
    private Button Subscription;
    @FXML
    private TilePane Subscription_tilepane;
    private boolean subscriptionsDisplayed = false;
    @FXML
    private Button CheckSubscription;
    @FXML
    private Text time;
    @FXML
    private AnchorPane check_Subscription_pane;
    @FXML
    private Button cancel_subscription;
    @FXML
    private Button delete;
    @FXML
    private Button logout;
    @FXML
    private ImageView avatar;
    
    private String selectedAvatar;
    @FXML
    private Button choose_avatar;
   

    

    
       
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       user.setText("Welcome "+UserManager.getCurrentUser().getUser_firstname()+"!");
       slider.setTranslateX(-230);
       
       profile.setVisible(false);
       Subscription_tilepane.setVisible(false);
       check_Subscription_pane.setVisible(false);
       Subscription_tilepane.setPrefColumns(4);
       if(current.getIsSubscribed()==1){
       CheckSubscription.setVisible(true);
       Subscription.setVisible(false);
       }
       else{
       CheckSubscription.setVisible(false);
       Subscription.setVisible(true);
       }
       
        
    }    

    

    @FXML
    private void exit(MouseEvent event) {
        slide.setDuration(Duration.seconds(0.4));
            slide.setNode(slider);

            slide.setToX(-230);
            slide.play();

            slider.setTranslateX(0);
    }

    @FXML
    private void enter(MouseEvent event) {
        slide.setDuration(Duration.seconds(0.4));
            slide.setNode(slider);

            slide.setToX(0);
            slide.play();

            slider.setTranslateX(-230);
    }

    @FXML
    private void update(ActionEvent event) {
        firstname_text.setDisable(false);
        lastname_text.setDisable(false);
        email_text.setDisable(false);
        password_text.setDisable(false);
        avatar.setDisable(false);
        choose_avatar.setDisable(false);
        
        update.setVisible(false);
        save.setVisible(true);
    }

    @FXML
    private void save_update(ActionEvent event) {
       if(validateInputs()==true){
            System.out.println(current);
            current.setUser_firstname(firstname_text.getText());
            current.setUser_lastname(lastname_text.getText());
            current.setUser_email(email_text.getText());
            current.setUser_password(password_text.getText());
            current.setUser_img(selectedAvatar);

            Su.update(current);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Your Account has been updated!");
            alert.show();

            user.setText("Welcome "+UserManager.getCurrentUser().getUser_firstname()+"!");
            firstname_text.setDisable(true);
            lastname_text.setDisable(true);
            email_text.setDisable(true);
            password_text.setDisable(true);
            avatar.setDisable(true);
            choose_avatar.setDisable(true);

            save.setVisible(false);
            update.setVisible(true);
       }        
    }

    @FXML
    private void show_profile(ActionEvent event) {
        firstname_text.setText(current.getUser_firstname());
        lastname_text.setText(current.getUser_lastname());
        email_text.setText(current.getUser_email());
        password_text.setText(current.getUser_password());
        File imgFile = new File(current.getUser_img());
        Image img = new Image(imgFile.toURI().toString());
        avatar.setImage(img);
        
       profile.setVisible(true);
       Subscription_tilepane.setVisible(false);
       check_Subscription_pane.setVisible(false);
       
       update.setVisible(true);
       save.setVisible(false);
        
    }


    @FXML
    private void Subscription_vbox(ActionEvent event) {
       profile.setVisible(false);
       Subscription_tilepane.setVisible(true);
       check_Subscription_pane.setVisible(false);
       if (!subscriptionsDisplayed) {
            
            List<Subscription> items = new ArrayList<>();
            items=Ss.displayAllList();

             for (Subscription item : items) {
                
                Image image;
                try {
                    image = new Image(new FileInputStream(item.getSubscription_img()));
                    ImageView imageView = new ImageView(image);
                    imageView.setFitHeight(150);
                    imageView.setFitWidth(150);
   
                    Button btn = new Button("Subscribe");
                    btn.setId(Integer.toString(item.getSubscription_id()));
                    btn.setStyle("-fx-background-color: black; -fx-background-radius: 20; -fx-font-family: \"Franklin Gothic Medium\";-fx-text-fill: #F9F7DD;");

                    Label price = new Label(Integer.toString(item.getSubscription_price())+" DT");
                    Label type = new Label(item.getSubscription_type());
                    price.setStyle("-fx-font-family: \"Franklin Gothic Medium\";");
                    type.setStyle("-fx-font-family: \"Franklin Gothic Medium\";");

                    VBox vBox = new VBox();
                    vBox.getChildren().addAll(imageView,type,price,btn);
                    vBox.setAlignment(Pos.CENTER);
                    Subscription_tilepane.getChildren().add(vBox);
                    
                    btn.setOnAction(e -> {

                        
                        int subscriptionId = Integer.parseInt(((Button) e.getSource()).getId());

                        long currentTimeMillis = System.currentTimeMillis();
                        long oneMonthInMillis = 30L * 24 * 60 * 60 * 1000;
                        long oneMonthLaterInMillis = currentTimeMillis + oneMonthInMillis;

                          
                        java.sql.Date currentDate = new java.sql.Date(currentTimeMillis );
                        java.sql.Date oneMonthLaterDate = new java.sql.Date(oneMonthLaterInMillis);


                        US.setSubscription_start(currentDate);
                        US.setSubscription_end(oneMonthLaterDate);
                        SUS.insert(US, current.getUser_id(), subscriptionId);
                        
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Information Dialog");
                        alert.setHeaderText(null);
                        alert.setContentText("You can't pay yet!");
                        alert.show();
                        CheckSubscription.setVisible(true);
                        Subscription.setVisible(false);
                        Subscription_tilepane.setVisible(false);
                        
                    });
                 
                } catch (FileNotFoundException ex) {
                        Logger.getLogger(ProfileController1.class.getName()).log(Level.SEVERE, null, ex);
                    }


                     }
            subscriptionsDisplayed = true;
        }
        
    }  

    @FXML
    private void Check_Subscription_vbox(ActionEvent event) {
        profile.setVisible(false);
        Subscription_tilepane.setVisible(false);
        check_Subscription_pane.setVisible(true);
        
        UserSubscription a = SUS.displayById(current.getUser_id());
        Date start = a.getSubscription_start();
        long currentTime = System.currentTimeMillis();
        Date end = a.getSubscription_end();
        
        long tl = end.getTime() - currentTime; // time difference in milliseconds
        long daysLeft = TimeUnit.DAYS.convert(tl, TimeUnit.MILLISECONDS); // convert to days
        
        time.setText("You have " +daysLeft+" days left in your "+a.getSubscription().getSubscription_type()+" Subscription");
        
        
        
        cancel_subscription.setOnAction(e -> {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("You can't cancel yet!");
            alert.show();
            
        });
   
    }

    @FXML
    private void delete(ActionEvent event) {
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Are you sure you want to Delete this item?");
        Optional<ButtonType> result = alert.showAndWait();
                            
        if (result.get() == ButtonType.OK){
            Su.delete(current);
            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/com/fithnity/view/Login.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                    Logger.getLogger(ProfileController1.class.getName()).log(Level.SEVERE, null, ex);
            }  
         }
    }

    @FXML
    private void logout(ActionEvent event) {
        
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Are you sure you want to Log Out?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            Parent page1;
            try {
            page1 = FXMLLoader.load(getClass().getResource("/com/fithnity/view/Login.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
            
            } catch (IOException ex) {
            Logger.getLogger(ProfileController1.class.getName()).log(Level.SEVERE, null, ex);
            }
    
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

        if (!firstname_text.getText().matches("[a-zA-Z]+")) {
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

        if (!lastname_text.getText().matches("[a-zA-Z]+")) {
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

        if(Su.verif_email(email_text.getText())==false && !email_text.getText().equals(current.getUser_email())){
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

        return true;
    }

    @FXML
    private void change_avatar(ActionEvent event) {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Choose File");
            fileChooser.setInitialDirectory(new File(System.getProperty("user.home")+ "/Desktop"));
            FileChooser.ExtensionFilter pngFilter = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.png");
            fileChooser.getExtensionFilters().add(pngFilter);
            File selectedFile = fileChooser.showOpenDialog(null);

            if (selectedFile != null) {
            Image img = new Image(selectedFile.toURI().toString());

                    selectedAvatar =selectedFile.getAbsolutePath();
                    selectedAvatar = selectedAvatar.replace(File.separator, "/");
                    avatar.setImage(img);
                    

           } 
    }

    
}
