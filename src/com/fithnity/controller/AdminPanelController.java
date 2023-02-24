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
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.TranslateTransition;
import javafx.beans.binding.Bindings;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.util.converter.NumberStringConverter;
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
    
    User U = new User();
    ServiceUser SU = ServiceUser.getInstance();
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
    @FXML
    private Button logout;
    
    
    
   
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
        MenuItem menuItem1 = new MenuItem("Delete");
        MenuItem menuItem2 = new MenuItem("Ban");       
        list.setVisible(true);
        subscription.setVisible(false);
        tilepane_subscription.setVisible(false);
        ObservableList<User> userList = listdata.getUsers();
        list_user.setItems(userList); 

        list_user.setContextMenu(contextMenu);
        contextMenu.getItems().clear();
        contextMenu.getItems().addAll(menuItem1, menuItem2);
            
            
            list_user.setOnMouseClicked(e -> {
        if (e.getButton() == MouseButton.SECONDARY) { // right-click
        User selectedUser = list_user.getSelectionModel().getSelectedItem();
            if (selectedUser != null) {
                contextMenu.show(list_user, e.getScreenX(), e.getScreenY());
                menuItem1.setOnAction(e1 -> {
                    //SU.delete(selectedUser);
                    list_user.getItems().remove(selectedUser);
                
                    //System.out.println(selectedUser.getUser_id());
               
                System.out.println("User " + selectedUser.getUser_firstname() +" is Deleted!");
            });
                menuItem2.setOnAction(e1 -> {
                // Handle option 2 action
                System.out.println("User " + selectedUser.getUser_firstname()+" is Banned!");
            });
             }
        }
        });
            
  
   
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
        tilepane_subscription.getChildren().clear();
            
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
                    Label price = new Label(Integer.toString(item.getSubscription_price())+" DT");
                    
                    
                    nbUsers.setStyle("-fx-font-family: \"Franklin Gothic Medium\";");
                    type.setStyle("-fx-font-family: \"Franklin Gothic Medium\";");
                    price.setStyle("-fx-font-family: \"Franklin Gothic Medium\";");
                    

                    VBox vBox = new VBox();
                    vBox.getChildren().addAll(imageView,type,price,nbUsers,btn);
                    vBox.setAlignment(Pos.CENTER);
                    tilepane_subscription.getChildren().add(vBox);
                    
                    
                    vBox.setOnMouseClicked(e -> {
                        // Create a new stage to display the subscription details
                        Stage detailsStage = new Stage();
                        detailsStage.setTitle("Subscription Details");

                        // Create text fields to display the subscription details
                        TextField typeField = new TextField(item.getSubscription_type());
                        typeField.setStyle("-fx-background-radius: 20; -fx-effect: dropshadow( gaussian , rgba(0,0,0,0.5) , 10,0,0,1 );");
                        typeField.setEditable(true);
                        TextField priceField = new TextField(Integer.toString(item.getSubscription_price()));
                        priceField.setStyle("-fx-background-radius: 20; -fx-effect: dropshadow( gaussian , rgba(0,0,0,0.5) , 10,0,0,1 );");
                        priceField.setEditable(true);


                        // Add the text fields to a grid pane
                        GridPane detailsPane = new GridPane();
                        detailsPane.setAlignment(Pos.CENTER);
                        detailsPane.setStyle("-fx-background-color: #F9F7DD;");
                        detailsPane.setVgap(40);
                        detailsPane.setHgap(20);
                        Label typeLabel = new Label("Type: ");
                        typeLabel.setFont(Font.font("Franklin Gothic Medium", FontWeight.NORMAL, 18));
                        detailsPane.add(typeLabel, 0, 0);
                        detailsPane.add(typeField, 1, 0);
                        Label priceLabel = new Label("Price: ");
                        priceLabel.setFont(Font.font("Franklin Gothic Medium", FontWeight.NORMAL, 18));
                        detailsPane.add(priceLabel, 0, 1);
                        detailsPane.add(priceField, 1, 1);
                        Button updateButton = new Button("Update");
                        updateButton.setStyle("-fx-background-color: black; -fx-background-radius: 20; -fx-font-family: \"Franklin Gothic Medium\"; -fx-text-fill: #F9F7DD;");
                        detailsPane.add(updateButton, 0, 2, 2, 1);
                        GridPane.setHalignment(updateButton, HPos.CENTER);
                        updateButton.setOnAction(e1 -> {
                            if(validateInputs2(typeField.getText(),priceField.getText())==true){
                            // Update the subscription with the new details
                            item.setSubscription_type(typeField.getText());
                            item.setSubscription_price(Integer.parseInt(priceField.getText()));
                            type.setText(item.getSubscription_type());
                            price.setText(Integer.toString(item.getSubscription_price())+" DT");
                            Ss.update(item);
                            // Close the window
                            detailsStage.close();
                            }
                        });

                        // Add the grid pane to the scene
                        Scene detailsScene = new Scene(detailsPane, 400, 300);
                        detailsStage.setScene(detailsScene);

                        // Show the stage
                        detailsStage.show();
                    });

                    
                    
                    
                    btn.setOnAction(e -> {
                            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                            alert.setTitle("Confirmation Dialog");
                            alert.setHeaderText("Are you sure you want to Delete this item?");
                            Optional<ButtonType> result = alert.showAndWait();
                            
                            if (result.get() == ButtonType.OK){
                                Ss.delete(item);
                                tilepane_subscription.getChildren().remove(vBox);
                            }
                    });
                 
                } catch (FileNotFoundException ex) {
                        Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                
                
                    



                     }
            
      
    }

    @FXML
    private void logout(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
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
            Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex);
            }
    
        } 
                              
    }
    
    public boolean validateInputs2(String type,String price) {
    
    if (type.isEmpty()) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("ERROR!");
        alert.setHeaderText(null);
        alert.setContentText("Missing Type Subscription!");
        alert.show();
        return false;
    }
    
    if (!type.matches("[a-zA-Z]+")) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("ERROR!");
        alert.setHeaderText(null);
        alert.setContentText("Invalid Firstname! Only alphabetic characters are allowed.");
        alert.show();
        return false;
    }
    
    if (price.isEmpty()) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("ERROR!");
        alert.setHeaderText(null);
        alert.setContentText("Missing Price Subscription!");
        alert.show();
        return false;
    }
   
    if (!price.matches("^[0-9]+$")) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("ERROR!");
        alert.setHeaderText(null);
        alert.setContentText("Invalid subscription price! Only numeric characters are allowed.");
        alert.show();
        return false;
    }
    
    int int_price = Integer.parseInt(price);
    
    if (int_price < 10 || int_price > 1000) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("ERROR!");
        alert.setHeaderText(null);
        alert.setContentText("Invalid subscription price! Price must be between 10 and 1000.");
        alert.show();
        return false;
    }
    return true;
    }
}
