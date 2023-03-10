/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fithnity.controller;

import com.fithnity.entity.Employée;
import com.fithnity.entity.Vehicule;
import com.fithnity.service.ServiceEmployée;
import com.fithnity.service.ServiceVehicule;
import com.fithnity.utils.Mail;
import java.io.IOException;
import java.net.URL;
import java.util.List;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.StringConverter;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class Ajout_employéeController implements Initializable {

    @FXML
    private TextField firstname_text;
    @FXML
    private TextField lastname_text;
    @FXML
    private TextField email_text;
    @FXML
    private TextField address_text;
    @FXML
    private Button fx_ajout;
    @FXML
    private Button fx_back;
    @FXML
    private Label Lb_err_nom;
    @FXML
    private Label Lb_err_prenom;
    @FXML
    private Label Lb_err_email;
    @FXML
    private Label Lb_err_ville;
    @FXML
    private Label lb_success_add;
    @FXML
    private Label lb_err_add;
    
    ServiceVehicule v_Service = new ServiceVehicule();
    @FXML
    private ComboBox<Vehicule> vehicule_combo;
    
    int selectedModelId =0;
    @FXML
    private AnchorPane container;
    @FXML
    private Label Lb_err_prenom1;
    @FXML
    private Label Lb_err_email1;
    @FXML
    private Label Lb_err_ville1;
    @FXML
    private Button btn_acceuil;
    @FXML
    private Button btn_user;
    @FXML
    private Button btn_reclamation;
    @FXML
    private Button btn_blog;
    @FXML
    private Button btn_reservation;
    @FXML
    private Button btn_offre;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
         fx_ajout.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               ajouter_emp();
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
        
                   List<Vehicule> vehiculeList = v_Service.getAllVehicule();

for(Vehicule vehicule : vehiculeList) {
    vehicule_combo.getItems().add(vehicule);
    vehicule_combo.setConverter(new StringConverter<Vehicule>() {
        @Override
        public String toString(Vehicule vehicule) {
            return vehicule.getModele();
        }
        @Override
        public Vehicule fromString(String string) {
            return vehicule;
        }
    });
}
 vehicule_combo.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
    if(newValue != null) {
        selectedModelId = newValue.getId();
        // Do something with the selected model id
        System.out.println(selectedModelId);

    }
});

 btn_acceuil.setOnAction(event -> {
            try {//FXMLLoader loader = new FXMLLoader();
                //loader.setLocation(getClass().getResource("/com/esprit/view/Accueil.fxml"));
                Parent page2 = FXMLLoader.load(getClass().getResource("/com/fithnity/view/Acceuil.fxml"));
                // Give the controller access to the main app.
//                AfficherPersonneController controller =loader.getController();
//                controller.setListData(new ListDataReclamation());
                Scene scene = new Scene(page2);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
                  btn_acceuil.setStyle("-fx-background-color : #1620A1");
            btn_acceuil.toFront();
             btn_reclamation.setStyle("-fx-background-color :  #05071F");
            } catch (IOException ex) {
                Logger.getLogger(AcceuilController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
                                  
    }
        
       

    @FXML
    private void ajouter_emp( ) {
        
        
       /*  
            if (firstname_text.getText().toString().equals("") || lastname_text.getText().toString().equals("")
                || email_text.getText().toString().equals("") || address_text.getText().toString().equals("")) {

            lb_success_add.setVisible(false);
                 
          if (firstname_text.getText().toString().equals("")) {
                Lb_err_nom.setVisible(true);
            } else {
                Lb_err_nom.setVisible(false);
            }
            if (lastname_text.getText().toString().equals("")) {
                Lb_err_prenom.setVisible(true);
            } else {
                Lb_err_prenom.setVisible(false);
            }
            if (email_text.getText().toString().equals("")) {
                Lb_err_email.setVisible(true);
            } else {
                Lb_err_email.setVisible(false);
            }
            if (address_text.getText().toString().equals("")) {
                Lb_err_ville.setVisible(true);
            } else {
                Lb_err_ville.setVisible(false);
            }
         }
         else{
                 lb_err_add.setVisible(false);
            Lb_err_nom.setVisible(false);
            Lb_err_prenom.setVisible(false);
            Lb_err_email.setVisible(false);
            Lb_err_ville.setVisible(false);
           

            lb_success_add.setVisible(true);
            
                
         ServiceEmployée e_Service = new ServiceEmployée();
        Employée e = new Employée();
        e.setFirstname_employée(String.valueOf(firstname_text.getText()));
        e.setLastname_employée(String.valueOf(lastname_text.getText()));
        e.setEmail_employée(String.valueOf(email_text.getText()));
        e.setAddress_employée(String.valueOf(address_text.getText()));
        
        e_Service.ajout_employée(e);
        
        String mailTO = e.getEmail_employée();
    
        
        Mail mail = new Mail();
        mail.envoyerMailEmploye(mailTO, e.getFirstname_employée(), e.getLastname_employée());
    }}
*/
      
              // Vérifier que les champs obligatoires sont remplis
    if (firstname_text.getText().isEmpty() || lastname_text.getText().isEmpty()
            || email_text.getText().isEmpty() || address_text.getText().isEmpty()) {

        lb_success_add.setVisible(false);

        if (firstname_text.getText().isEmpty()) {
            Lb_err_nom.setVisible(true);
        } else {
            Lb_err_nom.setVisible(false);
        }
        if (lastname_text.getText().isEmpty()) {
            Lb_err_prenom.setVisible(true);
        } else {
            Lb_err_prenom.setVisible(false);
        }
        if (email_text.getText().isEmpty()) {
            Lb_err_email.setVisible(true);
        } else {
            Lb_err_email.setVisible(false);
        }
        if (address_text.getText().isEmpty()) {
            Lb_err_ville.setVisible(true);
        } else {
            Lb_err_ville.setVisible(false);
        }
    } else {
        // Vérifier que les entrées sont valides
        boolean valid = true;

        // Vérifier le format de l'email
        String emailRegex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        Pattern emailPattern = Pattern.compile(emailRegex);
        Matcher emailMatcher = emailPattern.matcher(email_text.getText());
        if (!emailMatcher.matches()) {
            Lb_err_email.setText("Email invalide");
            Lb_err_email.setVisible(true);
            valid = false;
        } else {
            Lb_err_email.setVisible(false);
        }

        // Vérifier le format du nom et du prénom
        String nameRegex = "^[a-zA-Zéèêëîïôöûüàäç-]+$";
        Pattern namePattern = Pattern.compile(nameRegex);
        Matcher firstnameMatcher = namePattern.matcher(firstname_text.getText());
        Matcher lastnameMatcher = namePattern.matcher(lastname_text.getText());
        if (!firstnameMatcher.matches()) {
            Lb_err_nom.setText("Nom invalide");
            Lb_err_nom.setVisible(true);
            valid = false;
        } else {
            Lb_err_nom.setVisible(false);
        }
        if (!lastnameMatcher.matches()) {
            Lb_err_prenom.setText("Prénom invalide");
            Lb_err_prenom.setVisible(true);
            valid = false;
        } else {
            Lb_err_prenom.setVisible(false);
        }
        
         String villeRegex = "^[a-zA-Zéèêëîïôöûüàäç-]+$";
         Pattern villePattern = Pattern.compile(villeRegex);
         Matcher villeMatcher = villePattern.matcher(address_text.getText());    
        if (!villeMatcher.matches()) {
            Lb_err_ville.setText("Ville invalide");
            Lb_err_ville.setVisible(true);
            valid = false;
        } else {
            Lb_err_ville.setVisible(false);
        }

        if (valid) {
            lb_err_add.setVisible(false);
            Lb_err_nom.setVisible(false);
            Lb_err_prenom.setVisible(false);
            Lb_err_email.setVisible(false);
            Lb_err_ville.setVisible(false);

            lb_success_add.setVisible(true);

           
            
            ServiceEmployée e_Service = new ServiceEmployée();
            Employée e = new Employée(firstname_text.getText(),lastname_text.getText(),email_text.getText(),address_text.getText(),selectedModelId);
            
            e_Service.ajout_employée(e);
            
            String mailTO = e.getEmail_employée();

          Mail mail = new Mail();
          mail.envoyerMailEmploye(mailTO, e.getFirstname_employée(), e.getLastname_employée());
            
        }
    }
}
        
        
         /*
            if (firstname_text.getText().toString().equals("") || lastname_text.getText().toString().equals("")
                || email_text.getText().toString().equals("") || address_text.getText().toString().equals("")) {

            lb_success_add.setVisible(false);
                 
          if (firstname_text.getText().toString().equals("")) {
                Lb_err_nom.setVisible(true);
            } else {
                Lb_err_nom.setVisible(false);
            }
            if (lastname_text.getText().toString().equals("")) {
                Lb_err_prenom.setVisible(true);
            } else {
                Lb_err_prenom.setVisible(false);
            }
            if (email_text.getText().toString().equals("")) {
                Lb_err_email.setVisible(true);
            } else {
                Lb_err_email.setVisible(false);
            }
            if (address_text.getText().toString().equals("")) {
                Lb_err_ville.setVisible(true);
            } else {
                Lb_err_ville.setVisible(false);
            }
         }
         else{
                 lb_err_add.setVisible(false);
            Lb_err_nom.setVisible(false);
            Lb_err_prenom.setVisible(false);
            Lb_err_email.setVisible(false);
            Lb_err_ville.setVisible(false);
           

            lb_success_add.setVisible(true);
            */
                
        ServiceEmployée e_Service = new ServiceEmployée();
       Employée e = new Employée();
      
    public void redirectToMyGallerie(ActionEvent event) throws Exception {
        Parent page1 = FXMLLoader.load(getClass().getResource("/com/fithnity/view/DASHBOARD_employée.fxml"));
        Scene scene = new Scene(page1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    
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
        try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/com/fithnity/view/ADDlivraison.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                    Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    @FXML
    private void go_offreback(ActionEvent event) {
        try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/com/fithnity/view/commands.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                    Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
}


    
    
