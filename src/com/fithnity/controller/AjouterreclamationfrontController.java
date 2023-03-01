/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fithnity.controller;


import com.fithnity.service.ReclamationDao;
import com.fithnity.entity.Reclamation;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableCell;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import javafx.util.Duration;
import javax.management.Notification;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;


//**********************************




/**
 * FXML Controller class
 *
 * @author MSI
 */
public class AjouterreclamationfrontController implements Initializable {

    @FXML
    private ListView<Reclamation> listviewP;
    @FXML
    private Button btn_delete;
    @FXML
    private TextField txt_nom;
    @FXML
    private TextField txt_prenom;
    @FXML
    private Button btnn;
    @FXML
    private Button modif2;
    @FXML
    private TextField txt_email;
    @FXML
    private TextField txt_tel;
    @FXML
    private TextField txt_message;
    @FXML
    private Button btn_acceuil;
    @FXML
    private Button btn_user;
    @FXML
    private Button btn_blog;
    
      private ListData listdata = new ListData();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
          btn_acceuil.setOnAction(event -> {
            try {//FXMLLoader loader = new FXMLLoader();
                //loader.setLocation(getClass().getResource("/com/esprit/view/Accueil.fxml"));
                Parent page2 = FXMLLoader.load(getClass().getResource("/com/fithnity/view/Acceuil.fxml"));
                // Give the controller access to the main app.
//                AfficherPersonneController controller =loader.getController();
//                controller.setListData(new ListData());
                Scene scene = new Scene(page2);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
                  btn_acceuil.setStyle("-fx-background-color : #1620A1");
            btn_acceuil.toFront();
           //  btn_blog.setStyle("-fx-background-color :  #05071F");
            } catch (IOException ex) {
                Logger.getLogger(AcceuilController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

//        btn_display.setOnAction(event -> {
//            try {//FXMLLoader loader = new FXMLLoader();
//                //loader.setLocation(getClass().getResource("/com/esprit/view/Accueil.fxml"));
//                Parent page3 = FXMLLoader.load(getClass().getResource("/com/fithnity/view/AfficherPersonne.fxml"));
//                // Give the controller access to the main app.
////                AfficherPersonneController controller =loader.getController();
////                controller.setListData(new ListData());
//                Scene scene = new Scene(page3);
//                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//                stage.setScene(scene);
//                stage.show();
//                  btn_display.setStyle("-fx-background-color : #1620A1");
//            btn_display.toFront();
//           //  btn_blog.setStyle("-fx-background-color :  #05071F");
//            } catch (IOException ex) {
//                Logger.getLogger(AfficherPersonneController.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        });
//        
//         btn_display1.setOnAction(event -> {
//            try {//FXMLLoader loader = new FXMLLoader();
//                //loader.setLocation(getClass().getResource("/com/esprit/view/Accueil.fxml"));
//                Parent page2 = FXMLLoader.load(getClass().getResource("/com/fithnity/view/AfficherReponse.fxml"));
//                // Give the controller access to the main app.
////                AfficherPersonneController controller =loader.getController();
////                controller.setListData(new ListData());
//                Scene scene = new Scene(page2);
//                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//                stage.setScene(scene);
//                stage.show();
//                  btn_display1.setStyle("-fx-background-color : #1620A1");
//            btn_display1.toFront();
//             btn_blog.setStyle("-fx-background-color :  #05071F");
//             
//            } catch (IOException ex) {
//                Logger.getLogger(AfficherReponseController.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        });
          btn_blog.setOnAction(event -> {
            try {//FXMLLoader loader = new FXMLLoader();
                //loader.setLocation(getClass().getResource("/com/esprit/view/Accueil.fxml"));
                Parent page2 = FXMLLoader.load(getClass().getResource("/com/fithnity/view/reclamationback.fxml"));
                // Give the controller access to the main app.
//                AfficherPersonneController controller =loader.getController();
//                controller.setListData(new ListData());
                Scene scene = new Scene(page2);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
                  btn_blog.setStyle("-fx-background-color : #1620A1");
            btn_blog.toFront();
           //  btn_diplay.setStyle("-fx-background-color :  #05071F");
            
            } catch (IOException ex) {
                Logger.getLogger(reclamationbackController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
          //********************************************************************************
        
        listviewP.setItems(listdata.getPersons()); 
         
 
        listviewP.setOnMouseClicked(event->{
            
//        idLabel.setText(String.valueOf(listdata.getPersons()
//                .get(listviewP.getSelectionModel().getSelectedIndex())
//                .getId()));
//        nomLabel.setText(listdata.getPersons()
//                .get(listviewP.getSelectionModel().getSelectedIndex())
//                .getNom());
//        prenomLabel.setText(listdata.getPersons()
//                .get(listviewP.getSelectionModel().getSelectedIndex())
//                .getPrenom());
 java.sql.Date currentDate = new java.sql.Date( System.currentTimeMillis() );
        Reclamation current = listviewP.getSelectionModel().getSelectedItem();
        // txt_id.setText(Integer.toString(current.getId()));
       
         current.getId();
        txt_nom.setText(current.getNom());
        txt_prenom.setText(current.getPrenom());
        txt_email.setText(current.getEmail());
        txt_tel.setText(Integer.toString(current.getNumTel()));   
txt_message.setText(current.getMessage());        

    });
          
        
    }    

     @FXML
    private void ajouter(ActionEvent event) throws IOException { 
        if (Saisi() == true)
        {
            java.sql.Date currentDate = new java.sql.Date( System.currentTimeMillis() );
	 Reclamation p = new Reclamation(txt_nom.getText(), txt_prenom.getText(), txt_email.getText(), Integer.parseInt(txt_tel.getText()), txt_message.getText(),currentDate);
            ReclamationDao pdao = ReclamationDao.getInstance();
            pdao.insert(p);
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Reclamation insérée avec succés!");
        alert.show();
        
        txt_nom.setText("");
        txt_prenom.setText("");	
	 txt_email.setText("");
        txt_tel.setText("");   
txt_message.setText("");  
//reload
Parent root2 = FXMLLoader .load(getClass().getResource("/com/fithnity/view/Ajouterreclamationfront.fxml"));
    Stage window = (Stage) btnn.getScene().getWindow();
    window.setScene(new Scene(root2));
    //*************
    String title = "Done";
        String message = "Reclamation created ";
        TrayNotification tray = new TrayNotification();
        AnimationType type=AnimationType.POPUP;
        tray.setTitle(title);
        tray.setMessage(message);
        tray.setNotificationType(NotificationType.SUCCESS);
        tray.showAndDismiss(Duration.millis(1000));
    
	}
    }
    
    @FXML
    private void delete(ActionEvent event) throws IOException { 
		 ReclamationDao pdao = ReclamationDao.getInstance();
     Reclamation selectedItem2 = listviewP.getSelectionModel().getSelectedItem();
  
    listviewP.getItems().remove(selectedItem2);
      pdao.delete(selectedItem2);  
      Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Reclamation suprimée avec succés!");
        alert.show();
         String title = "Done";
        String message = "Reclamation created ";
        TrayNotification tray = new TrayNotification();
        AnimationType type=AnimationType.POPUP;
        tray.setTitle(title);
        tray.setMessage(message);
        tray.setNotificationType(NotificationType.SUCCESS);
        tray.showAndDismiss(Duration.millis(1000));
    
		
	}
    
    @FXML
    private void modifier(ActionEvent event) throws IOException { 
          if (Saisi() == true)
        {
		 Reclamation current = listviewP.getSelectionModel().getSelectedItem();
            Reclamation p = new Reclamation();
//            p.setId(Integer.parseInt(txt_id.getText()));
            p.setId(current.getId());
            p.setNom(txt_nom.getText());
            p.setPrenom(txt_prenom.getText());
            p.setEmail(txt_email.getText());
            p.setNumTel(Integer.parseInt(txt_tel.getText()));
             p.setMessage(txt_message.getText());
            ReclamationDao pdao = ReclamationDao.getInstance();
            pdao.update(p);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Reclamation modifiée avec succés!");
        alert.show();
            txt_nom.setText("");
        txt_prenom.setText("");	
	 txt_email.setText("");
        txt_tel.setText("");   
txt_message.setText("");  
//reload
Parent root2 = FXMLLoader .load(getClass().getResource("/com/fithnity/view/Ajouterreclamationfront.fxml"));
    Stage window = (Stage) modif2.getScene().getWindow();
    window.setScene(new Scene(root2));
     String title = "Done";
        String message = "Reclamation modified! ";
        TrayNotification tray = new TrayNotification();
        AnimationType type=AnimationType.POPUP;
        tray.setTitle(title);
        tray.setMessage(message);
        tray.setNotificationType(NotificationType.SUCCESS);
        tray.showAndDismiss(Duration.millis(1000));
    
        }	
	}
          
   
   
    @FXML
    private void back(ActionEvent event) throws IOException { 
		Parent root3 = FXMLLoader .load(getClass().getResource("/com/fithnity/view/Acceuil.fxml"));
    Stage window = (Stage) btnn.getScene().getWindow();
    window.setScene(new Scene(root3));
		
	}
    
     public static void Alert(Alert.AlertType type, String title, String header, String text) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(text);
        alert.showAndWait();

    }
    
 private boolean Saisi() {  

//        if (txt_nom.getText().isEmpty() || txt_prenom.getText().isEmpty() || txt_email.getText().isEmpty() || txt_tel.getText().isEmpty() || txt_message.getText().isEmpty()) {
//            Alert(Alert.AlertType.ERROR, "Données invalides", "Verifier !!", "Veuillez bien remplir tous les champs !");
//            return false;
//        } else {
//
//            if (!Pattern.matches("\\d{8}", txt_tel.getText())) {
//                Alert(Alert.AlertType.ERROR, "Données invalides", "Verifier !!", "Votre Num doit etre composé de huit chiffres! ");
//                return false;
//            }
//
//           if (!Pattern.matches("[A-Za-z]*", txt_nom.getText())) {
//                Alert(Alert.AlertType.ERROR, "Données invalides", "Verifier ", "Vérifiez le nom ! ");
//                return false;
//            }
//          if (!Pattern.matches("[A-Za-z]*", txt_prenom.getText())) {
//                Alert(Alert.AlertType.ERROR, "Données invalides", "Verifier ", "Vérifiez le prenom ! ");
//                return false;
//            }
//          if (!Pattern.matches("[A-Za-z]*", txt_message.getText())) {
//                Alert(Alert.AlertType.ERROR, "Données invalides", "Verifier ", "Vérifiez le mrssage de reclamation ! ");
//                return false;
//            }
//            if (!Pattern.matches("^[a-zA-Z0-9_+&*-]+(?:\\." + "[a-zA-Z0-9_+&*-]+)*@" +"(?:[a-zA-Z0-9-]+\\.)+[a-z" + "A-Z]{2,7}$", txt_email.getText())) {
//                Alert(Alert.AlertType.ERROR, "Données invalides", "Verifier ", "Vérifiez votre email ! ");
//                return false;
//            }
//           
//        }
        return true;
         
    }
     
      
}
