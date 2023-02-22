/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fithnity.controller;


import com.fithnity.entity.Subscription;
import com.fithnity.entity.User;
import com.fithnity.services.ServiceSubscription;
import com.fithnity.services.ServiceUser;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.TranslateTransition;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.util.Duration;
import javax.imageio.ImageIO;

/**
 * FXML Controller class
 *
 * @author Maaouia
 */
public class AdminPanelController implements Initializable {

    @FXML
    private AnchorPane slider;
    
    TranslateTransition slide = new TranslateTransition();
    @FXML
    private Button show_user;
    @FXML
    private ListView<User> list_user;
    
    private ListData listdata = new ListData();
    @FXML
    private AnchorPane subscription;
    @FXML
    private TextField type_subscription;
    @FXML
    private TextField price_subscription;
    @FXML
    private Button add_subscription;
    @FXML
    private Button add_image;
    @FXML
    private Button subscription_vbox;
    @FXML
    private ImageView image;
    
   
    Subscription s = new Subscription();
    ServiceSubscription Ss = ServiceSubscription.getInstance();
    @FXML
    private AnchorPane list;
    @FXML
    private Button b;
    
    
    
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        slider.setTranslateX(0);
        subscription.setVisible(false);
        list.setVisible(false);
        



//b.setContextMenu(contextMenu);
       
        
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
    private void show_user(ActionEvent event) {
         ContextMenu contextMenu = new ContextMenu();
        MenuItem menuItem1 = new MenuItem("Option 1");
MenuItem menuItem2 = new MenuItem("Option 2");       
        list.setVisible(true);
        subscription.setVisible(false);
        ObservableList<User> userList = listdata.getUsers();
        list_user.setItems(userList); 
        list_user.setCellFactory(cell -> new ListCell<User>() {
      @Override
        protected void updateItem(User user, boolean empty) {
            super.updateItem(user, empty);

            if (empty || user == null) {
                setText(null);
            } else {
                setText("First Name: " + user.getUser_firstname() + "  |  Last Name: " + user.getUser_lastname() + "  |  email: " + user.getUser_email());
                setContextMenu(contextMenu);
            contextMenu.getItems().clear();
            contextMenu.getItems().addAll(menuItem1, menuItem2);
            
            
            list_user.setOnMouseClicked(e -> {
    if (e.getButton() == MouseButton.SECONDARY) { // right-click
        User selectedUser = list_user.getSelectionModel().getSelectedItem();
        if (selectedUser != null) {
            contextMenu.show(list_user, e.getScreenX(), e.getScreenY());
            menuItem1.setOnAction(event -> {
                // Handle option 1 action
                System.out.println("Option 1 selected for user " + selectedUser.getUser_firstname());
            });
            menuItem2.setOnAction(event -> {
                // Handle option 2 action
                System.out.println("Option 2 selected for user " + selectedUser.getUser_firstname());
            });
        }
    }
});
            
            
            }
        }
        });
        


        
        
          
        
       
        System.out.println(userList);
       
    }


    @FXML
    private void add_image(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose File");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")+ "/Desktop"));
        FileChooser.ExtensionFilter pngFilter = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.png");
        fileChooser.getExtensionFilters().add(pngFilter);
        File selectedFile = fileChooser.showOpenDialog(null);
        
        if (selectedFile != null) {
        Image img = new Image(selectedFile.toURI().toString());
  
                String selected =selectedFile.getAbsolutePath();
                selected = selected.replace(File.separator, "/");
                image.setImage(img);
                s.setSubscription_img(selected);
           
       }
        
        add_subscription.setOnAction(e->{
            s.setSubscription_type(type_subscription.getText());
            s.setSubscription_price(Integer.parseInt(price_subscription.getText()));
            Ss.insert(s);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Your account has been created successfully!");
            alert.show();
           
            type_subscription.setText("");
            price_subscription.setText("");
            
        });
      }   

    @FXML
    private void subscription_vbox(ActionEvent event) {
        subscription.setVisible(true);
        list.setVisible(false);
    }

   
    
    
}
