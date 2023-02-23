/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fithnity.controller;


import com.fithnity.entity.Subscription;
import com.fithnity.entity.User;
import com.fithnity.entity.UserSubscription;
import com.fithnity.services.ServiceSubscription;
import com.fithnity.services.ServiceUser;
import com.fithnity.services.ServiceUserSubscription;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.TranslateTransition;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
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
import javafx.scene.layout.TilePane;
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
    UserSubscription Us = new UserSubscription();
    ServiceUserSubscription SUS = ServiceUserSubscription.getInstance();
    @FXML
    private AnchorPane list;
    @FXML
    private Button show_subscription_vbox;
    @FXML
    private TilePane tilepane_subscription;
    
    private boolean subscriptionsDisplayed = false;
    
    
    
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        slider.setTranslateX(0);
        subscription.setVisible(false);
        list.setVisible(false);
        tilepane_subscription.setVisible(false);

       
        
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
        tilepane_subscription.setVisible(false);
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
                add_image.setVisible(false);
                s.setSubscription_img(selected);
           
       }
        
        add_subscription.setOnAction(e->{
            if(validateInputs()==true){
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
            image.setImage(null);
            add_image.setVisible(true);
              }
        });
       
      }   

    @FXML
    private void subscription_vbox(ActionEvent event) {
        subscription.setVisible(true);
        list.setVisible(false);
        tilepane_subscription.setVisible(false);
    }

   
    public boolean validateInputs() {
    
    if (type_subscription.getText().isEmpty()) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("ERROR!");
        alert.setHeaderText(null);
        alert.setContentText("Missing Type Subscription!");
        alert.show();
        return false;
    }
    
    if (!type_subscription.getText().matches("[a-zA-Z]+")) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("ERROR!");
        alert.setHeaderText(null);
        alert.setContentText("Invalid Firstname! Only alphabetic characters are allowed.");
        alert.show();
        return false;
    }
    
    if (image == null) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("ERROR!");
        alert.setHeaderText(null);
        alert.setContentText("Missing Image!");
        alert.show();
        return false;
    }
    if (price_subscription.getText().isEmpty()) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("ERROR!");
        alert.setHeaderText(null);
        alert.setContentText("Missing Price Subscription!");
        alert.show();
        return false;
    }
   
    if (!price_subscription.getText().matches("^[0-9]+$")) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("ERROR!");
        alert.setHeaderText(null);
        alert.setContentText("Invalid subscription price! Only numeric characters are allowed.");
        alert.show();
        return false;
    }
    
    int price = Integer.parseInt(price_subscription.getText());
    
    if (price < 10 || price > 1000) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("ERROR!");
        alert.setHeaderText(null);
        alert.setContentText("Invalid subscription price! Price must be between 10 and 1000.");
        alert.show();
        return false;
    }
    
    
   
    
    
    return true;
   }

    @FXML
    private void show_subscription(ActionEvent event) {
        subscription.setVisible(false);
        list.setVisible(false);
        tilepane_subscription.setVisible(true);
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
   
                    Button btn = new Button("Delete");
                    btn.setId(Integer.toString(item.getSubscription_id()));
                    btn.setStyle("-fx-background-color: black; -fx-background-radius: 20; -fx-font-family: \"Franklin Gothic Medium\";-fx-text-fill: #F9F7DD;");

                    Label nbUsers = new Label(Integer.toString(SUS.GetNbUsers(item.getSubscription_id()))+" Current Users");
                    Label type = new Label(item.getSubscription_type());
                    nbUsers.setStyle("-fx-font-family: \"Franklin Gothic Medium\";");
                    type.setStyle("-fx-font-family: \"Franklin Gothic Medium\";");
                    

                    VBox vBox = new VBox();
                    vBox.getChildren().addAll(imageView,type,nbUsers,btn);
                    vBox.setAlignment(Pos.CENTER);
                    tilepane_subscription.getChildren().add(vBox);
                    
                    btn.setOnAction(e -> {
                          Ss.delete(item);
                        
                        
                    });
                 
                } catch (FileNotFoundException ex) {
                        Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex);
                    }


                     }
            subscriptionsDisplayed = true;
        }
    }
    
}
