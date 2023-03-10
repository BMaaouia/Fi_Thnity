/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fithnity.controller;

import com.fithnity.utils.Mail_Reservation;
import com.fithnity.service.livraisonService;
import com.fithnity.service.produitService;
import com.fithnity.service.reservationService;
import com.fithnity.entity.produit;
import com.fithnity.entity.reservation;
import java.io.IOException;
import java.net.URL;
import java.security.SecureRandom;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Worker;
import javafx.event.ActionEvent;
import javafx.event.Event;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebEvent;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import javax.swing.JOptionPane;
//import jdk.nashorn.api.scripting.JSObject;
import netscape.javascript.JSObject;


/**
 * FXML Controller class
 *
 * @author DELL
 */
public class ADDreservationController implements Initializable {

    @FXML
    private TextField catField;
    @FXML
    private TextField poidsField;
    @FXML
    private DatePicker dateField;
    @FXML
    private Button btn2;
    @FXML
    private ComboBox<String> idva;
    @FXML
    private ComboBox<String> idvd;

    /**
     * Initializes the controller class.
     */
    reservationService sr = new reservationService() ;
    produitService ps = new produitService();
   reservation r = new reservation() ;
    @FXML
    private ComboBox<produit> produit_combo;
    int selectedModelId=0;
    @FXML
    private AnchorPane container;
    @FXML
    private Button btn_acceuil;
    @FXML
    private Button btn_user;
    @FXML
    private Button btn_blog;
    @FXML
    private Button btn1;
    @FXML
    private WebView map;
    private WebEngine webengine;
    int kilométrages = 0;
    @FXML
    private TextField ref_field;
    
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final SecureRandom RANDOM = new SecureRandom();
    @FXML
    private Button btn_reclamation;

    public ADDreservationController() {
        this.sr = new reservationService();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btn2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    
                    suitdash(event);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        });
         ObservableList<String> list = FXCollections.observableArrayList("Tunis", "Sousse","Hammamet","Monastir" ) ; 
         idva.setItems(list);
        ObservableList<String> liste = FXCollections.observableArrayList("Tunis", "Sousse","Hammamet","Monastir" ) ; 
        idvd.setItems(liste);
        idva.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
    String idvDepart = idvd.getSelectionModel().getSelectedItem();
    String idvArrivee = idva.getSelectionModel().getSelectedItem();
    
//    int[][] distances = {
//    {0, 120, 70, 160, 80, 140, 70, 80, 250, 140, 210, 280, 140, 250, 200, 230, 320, 340, 450, 450, 460, 550, 350},
//    {120, 0, 120, 60, 30, 50, 90, 70, 280, 40, 110, 180, 50, 160, 110, 140, 230, 250, 360, 360, 370, 460, 260},
//    {70, 120, 0, 130, 80, 140, 10, 50, 220, 110, 180, 250, 110, 220, 170, 200, 290, 310, 420, 420, 430, 520, 320},
//    {160, 60, 130, 0, 90, 30, 120, 100, 310, 80, 40, 110, 30, 140, 90, 120, 210, 230, 340, 340, 350, 440, 240},
//    {80, 30, 80, 90, 0, 60, 40, 20, 190, 70, 140, 210, 70, 180, 130, 160, 250, 270, 380, 380, 390, 480, 280},
//    {140, 50, 140, 30, 60, 0, 130, 110, 280, 50, 20, 90, 50, 60, 40, 70, 160, 180, 290, 290, 300, 390, 190},
//    {70, 90, 10, 120, 40, 130, 0, 40, 210, 100, 170, 240, 100, 210, 160, 190, 280, 300, 410, 410, 420, 510, 310},
//    {80, 70, 50, 100, 20, 110, 40, 0, 170, 60, 130, 200, 60, 170, 120, 150, 240, 260, 370, 370, 380, 470, 270},
//    {250, 280, 220, 310, 190, 280, 210, 170, 0, 220, 290, 360, 220, 310, 260, 290, 380, 400, 470, 470, 480, 570, 370},
//    {140, 40, 110, 80, 70, 50, 100, 60, 220, 0, 70, 140, 40, 150, 100, 130, 220, 240, 350, 350, 360, 450, 250},
//    {210, 110, 180, 40, 140, 20, 170, 130, 290, 70, 0, 70, 90, 20, 30, 60, 150, 170, 280, 280, 290, 380, 180},
//    {280, 180, 250, 110, 210, 90, 240, 200, 360, 140, 70, 0, 130, 40, 90, 120, 210, 230, 340, 340, 350, 440, 240},
//    {140, 50, 110, 30, 70, 50, 100, 60, 220, 40, 90, 130, 0, 110, 60, 90, 180, 200, 310, 310, 320, 410, 210},
//    {250, 160, 220, 140, 180, 60, 210, 170, 310, 150, 20, 40, 110, 0, 50, 80, 170, 190, 300, 300, 310, 400, 200},
//    {200, 110, 170, 90, 130, 40, 160, 120, 260, 100, 30, 90, 60, 50, 0, 30, 120, 140, 250, 250, 260, 350, 150},
//    {230, 140, 200, 120, 160, 70, 190, 150, 290, 130, 60, 120, 90, 80, 30, 0, 90, 110, 220, 220, 230, 320, 120},
//    {320, 230, 290, 210, 250, 160, 280, 240, 380, 220, 150, 210, 180, 170, 120, 90, 0, 20, 130, 130, 140, 230, 130},
//    {340, 250, 310, 230, 270, 180, 300, 260, 400, 240, 170, 230, 200, 190, 140, 110, 20, 0, 110, 110, 120, 210, 110},
//    {450, 360, 420, 340, 380, 290, 410, 370, 470, 350, 280, 340, 310, 300, 250, 220, 130, 110, 0, 10, 20, 110, 210} ,
//    {230, 140, 200, 120, 160, 70, 190, 150, 290, 130, 60, 120, 90, 80, 30, 100, 90, 110, 220, 0, 230, 320, 120},
//    {320, 230, 290, 210, 250, 160, 280, 240, 380, 220, 150, 210, 180, 170, 120, 90, 80, 20, 130, 130, 0, 230, 130},
//    {340, 250, 310, 230, 270, 180, 300, 260, 400, 240, 170, 230, 200, 190, 140, 110, 20, 120, 110, 110, 120, 0, 110},
//    {450, 360, 420, 340, 380, 290, 410, 370, 470, 350, 280, 340, 310, 300, 250, 220, 130, 110, 210, 10, 20, 110, 0} 
//   
//    };
   
    
    if (idvDepart != null && idvDepart.equals("Sousse") && idvArrivee != null && idvArrivee.equals("Tunis")) {
        kilométrages = 160;
    } else if (idvDepart != null && idvDepart.equals("Hammamet") && idvArrivee != null && idvArrivee.equals("Sousse")) {
        kilométrages = 50;
    } else if (idvDepart != null && idvDepart.equals("Monastir") && idvArrivee != null && idvArrivee.equals("Tunis")) {
        kilométrages = 120;
    } else if (idvDepart != null && idvDepart.equals("Tunis") && idvArrivee != null && idvArrivee.equals("Hammamet")) {
        kilométrages = 70;
    } else if (idvDepart != null && idvDepart.equals("Sousse") && idvArrivee != null && idvArrivee.equals("Monastir")) {
        kilométrages = 80;
    } else if (idvDepart != null && idvDepart.equals("Tunis") && idvArrivee != null && idvArrivee.equals("Monastir")) {
        kilométrages = 130;
    } else if (idvDepart != null && idvDepart.equals("Tunis") && idvArrivee != null && idvArrivee.equals("Sousse")) {
        kilométrages = 140;
    } else if (idvDepart != null && idvDepart.equals("Hammamet") && idvArrivee != null && idvArrivee.equals("Monastir")) {
        kilométrages = 90;
    } else if (idvDepart != null && idvDepart.equals("Hammamet") && idvArrivee != null && idvArrivee.equals("Tunis")) {
        kilométrages = 70;
    } else if (idvDepart != null && idvDepart.equals("Monastir") && idvArrivee != null && idvArrivee.equals("Sousse")) {
        kilométrages = 80;
    }
    
//if (idvDepart != null && idvArrivee != null) {
//    int departIndex = getIndexForCity(idvDepart);     // get index of departure city
//    int arriveeIndex = getIndexForCity(idvArrivee);   // get index of arrival city
//    if (departIndex >= 0 && arriveeIndex >= 0) {
//        kilométrages = distances[departIndex][arriveeIndex];   // look up distance in matrix
//    }
//}
    
    
            System.out.println(kilométrages);
    
    
    calcul();
    
    
    
});
        

    
//            if (idva.getValue() != null && idvd.getValue() != null) {
//        if (idva.getValue().equals("Tunis") && idvd.getValue().equals("Sousse")) {
//            catField.setText("100");
//        } else if (idva.getValue().equals("Tunis") && idvd.getValue().equals("Hammemet")) {
//            catField.setText("100");
//        } else if (idva.getValue().equals("Tunis") && idvd.getValue().equals("Monastir")) {
//            catField.setText("100");
//        } else if (idva.getValue().equals("Sousse") && idvd.getValue().equals("Tunis")) {
//            catField.setText("100");
//        } else if (idva.getValue().equals("Sousse") && idvd.getValue().equals("Hammemet")) {
//            catField.setText("100");
//        } else if (idva.getValue().equals("Sousse") && idvd.getValue().equals("Monastir")) {
//            catField.setText("100");
//        } else if (idva.getValue().equals("Hammemet") && idvd.getValue().equals("Tunis")) {
//            catField.setText("100");
//        } else if (idva.getValue().equals("Hammemet") && idvd.getValue().equals("Sousse")) {
//            catField.setText("100");
//        } else if (idva.getValue().equals("Hammemet") && idvd.getValue().equals("Monastir")) {
//            catField.setText("100");
//        }
//    }


//idva.setOnAction(e -> updateCatField());
//idvd.setOnAction(e -> updateCatField());





        String reference = generateReference();
        ref_field.setText(reference);






        // TODO
           LocalDate date = LocalDate.now(); // date par défaut : aujourd'hui
        dateField.setValue(date); 
        
              reservation r=new reservation();
        dateField.setOnAction(new EventHandler() {
            @Override
            public void handle(Event t) {
                LocalDate date = dateField.getValue();
                r.setDateReser(date);
            }
        });
                       dateField.valueProperty().addListener((observable, oldValue, newValue) -> {
            // Mettre à jour l'affichage ou exécuter d'autres actions en réponse aux changements de date
        });
                       
                       
                       
         List<produit> produitList;
        try {
            produitList = ps.getAllProduit();
            for(produit p : produitList) {
    produit_combo.getItems().add(p);
    produit_combo.setConverter(new StringConverter<produit>() {
        @Override
        public String toString(produit p) {
            return p.getNom_produit();
        }
        @Override
        public produit fromString(String string) {
            return p;
        }
    });
}
 produit_combo.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
    if(newValue != null) {
        selectedModelId = newValue.getId_produit();
        // Do something with the selected model id
        System.out.println(selectedModelId);

    }
});  
        } catch (SQLException ex) {
            Logger.getLogger(ADDreservationController.class.getName()).log(Level.SEVERE, null, ex);
        }

            
webengine = map.getEngine();
        webengine.load(getClass().getResource("/com/fithnity/view/Map.html").toExternalForm());

    }


           



    @FXML
    private void ajoutLivraison(ActionEvent event) {
     
    float  poids =Float.valueOf(poidsField.getText());
    float prix =Float.valueOf(catField.getText());
    String vd= idvd.getSelectionModel().getSelectedItem();
    String va= idva.getSelectionModel().getSelectedItem();
    LocalDate date = dateField.getValue();
    
     String regex = "^[a-zA-Z ]+$"; //expression régulière pour accepter uniquement des lettres et espaces
        Pattern pattern = Pattern.compile(regex);
       
        
       
         
//DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//String formattedDate = date.format(formatter); 
    if (poids <= 0) {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setHeaderText("Le poids doit être supérieur à zéro.");
    alert.showAndWait();
    }
  
    
     else
        {
            reservation U1 = new reservation (ref_field.getText(),poids,prix,vd,va,date,selectedModelId);
            reservationService uc= new reservationService() ;
             System.out.println("Connexion réussie!");
                 String recipient = "yassine.benbelgacem@esprit.tn";
                 try {
                 com.fithnity.utils.Mail_Reservation.envoyer(recipient);
                 System.out.println("Le message a été envoyé avec succès.");
                 } catch (Exception e) {
                 System.err.println("Erreur lors de l'envoi du message : " + e.getMessage());
                 e.printStackTrace();}
             uc.addReservation(U1);
            
            JOptionPane.showMessageDialog(null,"Succés De Création ");
        
        }
   
    }

    @FXML
    private void suitdash(ActionEvent event) throws IOException {
        Parent page1 = FXMLLoader.load(getClass().getResource("/com/fithnity/view/DASHBOARDreservation.fxml"));
        Scene scene = new Scene(page1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
        
    }



    @FXML
    private void back(ActionEvent event) throws IOException {
             Parent page1 = FXMLLoader.load(getClass().getResource("/com/fithnity/view/DASHBOARDreservation.fxml"));
        Scene scene = new Scene(page1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
//
//  private void updateCatField() {
//    String idvaValue = idva.getValue();
//    String idvdValue = idvd.getValue();
//
//    if (idvaValue != null && idvdValue != null) {
//        if ((idvaValue.equals("Hammemet") && idvdValue.equals("Monastir")) ||
//            (idvaValue.equals("Monastir") && idvdValue.equals("Hammemet"))) {
//            catField.setText("200");
//        } else if ((idvaValue.equals("Sousse") && idvdValue.equals("Tunis")) ||
//                   (idvaValue.equals("Tunis") && idvdValue.equals("Sousse"))) {
//            catField.setText("100");
//        } else {
//            catField.setText("");
//        }
//    }
//}
//    
    
    
    public void calcul(){
        float prix= (float) (kilométrages*0.2+Float.valueOf(poidsField.getText())*0.2);
        catField.setText(String.valueOf(prix));

    }
    
    public static String generateReference() {
        StringBuilder sb = new StringBuilder(8);
        for (int i = 0; i < 8; i++) {
            sb.append(CHARACTERS.charAt(RANDOM.nextInt(CHARACTERS.length())));
        }
        return sb.toString();
    }

    @FXML
    private void go_user(ActionEvent event) {
        try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/com/fithnity/view/Profile.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                    Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    @FXML
    private void go_reclamation(ActionEvent event) {
        try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/com/fithnity/view/ajouterreclamationfront.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                    Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    @FXML
    private void go_blog(ActionEvent event) {
        try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/com/fithnity/view/ajout_c.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                    Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
}
