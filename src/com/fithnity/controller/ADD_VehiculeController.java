package com.fithnity.controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import com.fithnity.entity.Vehicule;
import com.fithnity.service.ServiceVehicule;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class ADD_VehiculeController implements Initializable {

    @FXML
    private TextField fx_model;
    @FXML
    private TextField fx_mat;
    @FXML
    private TextField fx_cat;
    @FXML
    private CheckBox fx_etat;
    @FXML
    private Button btn_ajout;
    @FXML
    private Button fx_back;
    @FXML
    private CheckBox fx_etat2;
    @FXML
    private Label fx_etatLabel;
    @FXML
    private Label modelelabel;
    @FXML
    private Label etatlabel;
    @FXML
    private Label categorielabel;
    @FXML
    private Label immatriculationlabel;
    @FXML
    private Button btn_acceuil;
    @FXML
    private Button btn_user;
    @FXML
    private Button btn_blog;
    @FXML
    private AnchorPane container;
    @FXML
    private Button choose_image;
    @FXML
    private ImageView image_vehicule;
    
     private String selectedImage;
    @FXML
    private Button btn_user1;
    @FXML
    private Button btn_reclamation;
    @FXML
    private Button btn_reservation;
    @FXML
    private Label label_modele_err;
    @FXML
    private Label label_immatricul_err;
    @FXML
    private Label label_categor_err;
    @FXML
    private Label label_erreur_ajout;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btn_ajout.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               ajouter_v();
            }
        });
           
        fx_back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    redirectToMyGallerie(event);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        });
    }
    

    @FXML
    private void ajouter_v() {
        
        boolean valid = true;
        
      
        ServiceVehicule V_Service = new ServiceVehicule();
        Vehicule v = new Vehicule();
        if(fx_model.getText().isEmpty() || fx_mat.getText().isEmpty() || fx_cat.getText().isEmpty()) {
            valid = false;
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Remplissez tous les champs");
            alert.showAndWait();
        } 
             
        // Vérifier que les entrées sont valides
        

         String nameRegex = "^[a-zA-Zéèêëîïôöûüàäç-]+$";
        Pattern namePattern = Pattern.compile(nameRegex);
        Matcher categorieMatcher = namePattern.matcher(fx_cat.getText());
       
        if (!categorieMatcher.matches()) {
            categorielabel.setText("Catégorie invalide");
            categorielabel.setVisible(true);
            valid = false;
             Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("la forme de catégorie invalide");
            alert.showAndWait();
            
        } else {
            categorielabel.setVisible(false);
        
            
            v.setModele(fx_model.getText());
            v.setImmatriculation(fx_mat.getText());
            v.setCategorie(fx_cat.getText());
            v.setEtat(fx_etat.isSelected());
            v.setEtat(fx_etat2.isSelected());
            v.setImage(selectedImage);
            
          if(fx_etat.isSelected()) {
    fx_etatLabel.setText("Occasion");
} else {
    fx_etatLabel.setText("Neuf");
}
            
            try {
                V_Service.addVehicule(v);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("Le véhicule a été ajouté avec succès !");
                alert.show();
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText(null);
                alert.setContentText("Une erreur est survenue lors de l'ajout du véhicule : " + e.getMessage());
                alert.show();
            }
        }
    }


    public void redirectToMyGallerie(ActionEvent event) throws Exception {
        Parent page1 = FXMLLoader.load(getClass().getResource("/com/fithnity/view/DASHBOARD_vehicule.fxml"));
        Scene scene = new Scene(page1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    
}

    @FXML
    private void choose_image(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Choose File");
            fileChooser.setInitialDirectory(new File(System.getProperty("user.home")+ "/Desktop"));
            FileChooser.ExtensionFilter pngFilter = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.png");
            fileChooser.getExtensionFilters().add(pngFilter);
            File selectedFile = fileChooser.showOpenDialog(null);

            if (selectedFile != null) {
            Image img = new Image(selectedFile.toURI().toString());

                    selectedImage =selectedFile.getAbsolutePath();
                    selectedImage = selectedImage.replace(File.separator, "/");
                    image_vehicule.setImage(img);
                    

           }
    }

    @FXML
    private void go_userback(ActionEvent event) {
        try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/com/fithnity/view/AdminPanel.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                    Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    @FXML
    private void go_userback1(ActionEvent event) {
        try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/com/fithnity/view/AdminPanel.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                    Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    @FXML
    private void go_reclamationback(ActionEvent event) {
        try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/com/fithnity/view/reclamationback.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                    Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    @FXML
    private void go_blogback(ActionEvent event) {
        try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/com/fithnity/view/Ajout_Blog.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                    Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    @FXML
    private void go_reservationback(ActionEvent event) {
    }
}

