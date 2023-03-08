/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fithnity.controller;

import com.fithnity.entity.Subscription;
import com.fithnity.entity.User;
import com.fithnity.entity.UserSubscription;
import com.fithnity.service.ServiceSubscription;
import com.fithnity.service.ServiceUser;
import com.fithnity.service.ServiceUserSubscription;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Maaouia
 */
public class AdminPanelController implements Initializable {

    @FXML
    private Button btn_acceuil;
    @FXML
    private Button btn_acceuil2;
    @FXML
    private Button btn_blog;
    @FXML
    private AnchorPane subscription;
    @FXML
    private TextField type_subscription;
    @FXML
    private Button add_subscription;
    @FXML
    private ImageView image;
    @FXML
    private Button add_image;
    @FXML
    private TextField price_subscription;
    @FXML
    private AnchorPane list;
    @FXML
    private ScrollPane scrollpane_user;
    @FXML
    private Button logout;
    @FXML
    private TilePane tilepane_subscription;
    @FXML
    private Button show_user;
    
    User U = new User();
    ServiceUser SU = ServiceUser.getInstance();
    Subscription s = new Subscription();
    ServiceSubscription Ss = ServiceSubscription.getInstance();
    UserSubscription Us = new UserSubscription();
    ServiceUserSubscription SUS = ServiceUserSubscription.getInstance();
    @FXML
    private Button show_subscription_vbox;
    @FXML
    private Button subscription_vbox;
    @FXML
    private Button retour;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        subscription_vbox.setVisible(false);
        retour.setVisible(false);
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
            retour.setVisible(false);
            show_subscription(event);
            
              }
        });
        
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

    @FXML
    private void show_user(ActionEvent event) {
        List<User> users = new ArrayList<>();
        users=SU.displayAllList();
        list.setVisible(true);
        subscription.setVisible(false);
        tilepane_subscription.setVisible(false);
        GridPane gridPane = new GridPane();
        gridPane.setHgap(100);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(10));
        scrollpane_user.setContent(gridPane);

        Label nameLabel = new Label("Name");
        nameLabel.setStyle("-fx-font-weight: bold");
        gridPane.add(nameLabel, 1, 0);

        Label emailLabel = new Label("Email");
        emailLabel.setStyle("-fx-font-weight: bold");
        gridPane.add(emailLabel, 2, 0);

        Label avatarLabel = new Label("Avatar");
        avatarLabel.setStyle("-fx-font-weight: bold");
        gridPane.add(avatarLabel, 0, 0);
        
        
        for (int i = 0; i < users.size(); i++) {
            
            User user = users.get(i);
            File imgFile = new File(user.getUser_img());
            Image image= new Image(imgFile.toURI().toString());
            ImageView imageView = new ImageView(image);
            imageView.setFitHeight(150);
            imageView.setFitWidth(150);
 
            gridPane.add(new Label(user.getUser_firstname()), 1, i + 1);
            gridPane.add(new Label(user.getUser_email()), 2, i + 1);
            gridPane.add(imageView, 0, i + 1);

            
        }
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

    @FXML
    private void show_subscription(ActionEvent event) {
        
        subscription.setVisible(false);
        list.setVisible(false);
        tilepane_subscription.setVisible(true);
        subscription_vbox.setVisible(true);
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
    private void subscription_vbox(ActionEvent event) {
        subscription.setVisible(true);
        list.setVisible(false);
        tilepane_subscription.setVisible(false);
        subscription_vbox.setVisible(false);
        retour.setVisible(true);
    }

    @FXML
    private void retour(ActionEvent event) {
        retour.setVisible(false);
        show_subscription(event);
    
    }
    
}
