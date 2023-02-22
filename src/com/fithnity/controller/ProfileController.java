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
import java.util.ResourceBundle;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Maaouia
 */
public class ProfileController implements Initializable {

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
        
        update.setVisible(false);
        save.setVisible(true);
    }

    @FXML
    private void save_update(ActionEvent event) {
       
       System.out.println(current);
       current.setUser_firstname(firstname_text.getText());
       current.setUser_lastname(lastname_text.getText());
       current.setUser_email(email_text.getText());
       current.setUser_password(password_text.getText());
       
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
       
       save.setVisible(false);
       update.setVisible(true);
                
    }

    @FXML
    private void show_profile(ActionEvent event) {
        firstname_text.setText(current.getUser_firstname());
        lastname_text.setText(current.getUser_lastname());
        email_text.setText(current.getUser_email());
        password_text.setText(current.getUser_password());
        
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

                    

                    Label price = new Label(Integer.toString(item.getSubscription_price()));
                    Label type = new Label(item.getSubscription_type());

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
                        Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex);
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
        Date end = a.getSubscription_end();
        
        long tl = end.getTime() - start.getTime(); // time difference in milliseconds
        long daysLeft = TimeUnit.DAYS.convert(tl, TimeUnit.MILLISECONDS); // convert to days
        
        time.setText("You have " +daysLeft+" days in your "+a.getSubscription().getSubscription_type()+" Subscription");
   
    }
    

    
}
