/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fithnity.controller;


import com.fithnity.service.ReclamationDao;
import com.fithnity.entity.Reclamation;
import com.fithnity.entity.User;
import com.fithnity.service.UserManager;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
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
import javafx.scene.control.DatePicker;

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
import javafx.scene.control.Pagination;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableCell;
import javafx.scene.control.TextArea;
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

    private ListView<Reclamation> listviewP;
    @FXML
    private TextField txt_nom;
    @FXML
    private TextField txt_prenom;
    @FXML
    private Button btnn;
    private Button modif2;
    @FXML
    private TextField txt_email;
    @FXML
    private TextField txt_tel;
    @FXML
    private TextArea txt_message;
    @FXML
    private Button btn_acceuil;
    @FXML
    private Button btn_user;
    private Button btn_blog;
    
      private ListDataReclamation listdata = new ListDataReclamation();
    @FXML
    private AnchorPane container;

    private final int ITEMS_PER_PAGE = 5;
    private DatePicker dd;
    private DatePicker df;
    @FXML
    private Button manipuler;
    private Set<String> attributes = new HashSet<>();
    @FXML
    private ComboBox<String> txt_type;
    
    User current2 =UserManager.getCurrentUser();
    @FXML
    private Button btn_blog2;
    @FXML
    private Button btn_admin;
    @FXML
    private Button btn_Employe;
    @FXML
    private Button btn_reservation;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        System.out.println(current2.getUser_email());
        // TODO
//        int pageCount = (int) Math.ceil(listdata.getPersons().size() * 1.0 / ITEMS_PER_PAGE);
//        pagination.setPageCount(pageCount);
//        pagination.setPageFactory(this::createPage);
//        //************************************
//                
//search.textProperty().addListener((observable, oldValue, newValue) -> {
//    ReclamationDao pdao = ReclamationDao.getInstance();
//    List<Reclamation> r = pdao.rechercher(newValue);
//    ObservableList<Reclamation> reclamationsList = FXCollections.observableArrayList(r);
//    listviewP.setItems(reclamationsList);
//});
ObservableList<String> liste = FXCollections.observableArrayList("Article perdue", "probléme avec le transporteur","Colis endomagés") ;
        txt_type.setItems(liste);
        //***************
          btn_acceuil.setOnAction(event -> {
            try {//FXMLLoader loader = new FXMLLoader();
                //loader.setLocation(getClass().getResource("/com/esprit/view/Accueil.fxml"));
                Parent page2 = FXMLLoader.load(getClass().getResource("/com/fithnity/view/acceuilfront.fxml"));
                // Give the controller access to the main app.
//                AfficherPersonneController controller =loader.getController();
//                controller.setListData(new ListDataReclamation());
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
          
       



//          btn_admin.setOnAction(event -> {
//            
//               
//             try {
////                Parent page2 = FXMLLoader.load(getClass().getResource("/com/fithnity/view/Ajouterreclamationfront.fxml"));
////                Scene scene = new Scene(page2);
////                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
////                stage.setScene(scene);
////                stage.show();
//              Parent root = FXMLLoader.load(getClass().getResource("/com/fithnity/view/Acceuil.fxml"));
//       
//        Scene scene = btn_acceuil.getScene();
//        root.translateXProperty().set(-scene.getWidth());
//
//        AnchorPane parentContainer = (AnchorPane) btn_acceuil.getScene().getRoot();
//
//        parentContainer.getChildren().add(root);
//
//        Timeline timeline = new Timeline();
//        KeyValue kv = new KeyValue(root.translateXProperty(), 0, Interpolator.EASE_OUT);
//        KeyFrame kf = new KeyFrame(Duration.seconds(1), kv);
//        timeline.getKeyFrames().add(kf);
//        timeline.setOnFinished(t -> {
//            parentContainer.getChildren().remove(container);
//        });
//        timeline.play();
//            } catch (IOException ex) {
//                Logger.getLogger(reclamationbackController.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        });
//          //********************************************************************************
            txt_nom.setText(current2.getUser_lastname());
            txt_prenom.setText(current2.getUser_firstname());
            txt_email.setText(current2.getUser_email());
//  
    }    

     @FXML
    private void ajouter(ActionEvent event) throws IOException { 
        if (Saisi() == true)
        {
            
            java.sql.Date currentDate = new java.sql.Date( System.currentTimeMillis() );
             ReclamationDao pdao = ReclamationDao.getInstance();
	 Reclamation p = new Reclamation(current2.getUser_lastname(), current2.getUser_firstname(), current2.getUser_email(), Integer.parseInt(txt_tel.getText()), pdao.bad_words(txt_message.getText()),currentDate,txt_type.getSelectionModel().getSelectedItem());
	//*************************************************************** Reclamation p = new Reclamation(txt_nom.getText(),txt_prenom.getText(),txt_email.getText(), Integer.parseInt(txt_tel.getText()), pdao.bad_words(txt_message.getText()),currentDate,txt_type.getSelectionModel().getSelectedItem());
            
//Reclamation p2 = new Reclamation( txt_email.getText(), pdao.bad_words(txt_message.getText()));

         //**************************
          if (pdao.reclamationExists(p)) {
            pdao.insert(p);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Réclamation insérée avec succès!");
            alert.show();
            //*****************************************
             String title = "Done";
        String message = "Reclamation created ";
        TrayNotification tray = new TrayNotification();
        AnimationType type=AnimationType.POPUP;
        tray.setTitle(title);
        tray.setMessage(message);
        tray.setNotificationType(NotificationType.SUCCESS);
        tray.showAndDismiss(Duration.millis(1000));

            txt_nom.setText("");
            txt_prenom.setText("");
            txt_email.setText("");
            txt_tel.setText("");
            txt_message.setText("");
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Une réclamation avec les mêmes valeurs existe déjà!");
            alert.show();
        }

       
//reload
Parent root2 = FXMLLoader .load(getClass().getResource("/com/fithnity/view/ajouterreclamationfront.fxml"));
    Stage window = (Stage) btnn.getScene().getWindow();
    window.setScene(new Scene(root2));
    //*************
   
    //************
	}
    }
    
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
    
    private void modifier(ActionEvent event) throws IOException { 
          if (Saisi() == true)
        {
            if (Saisi2(txt_nom.getText()) == true)
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
Parent root2 = FXMLLoader .load(getClass().getResource("/com/fithnity/view/ajouterreclamationfront.fxml"));
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
    }
          
   
   
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

        if (txt_nom.getText().isEmpty() || txt_prenom.getText().isEmpty() || txt_email.getText().isEmpty() || txt_tel.getText().isEmpty() || txt_message.getText().isEmpty()||  txt_type.getValue() == null) {
            Alert(Alert.AlertType.ERROR, "Données invalides", "Verifier !!", "Veuillez bien remplir tous les champs !");
            return false;
        } else {

            if (!Pattern.matches("\\d{8}", txt_tel.getText())) {
                Alert(Alert.AlertType.ERROR, "Données invalides", "Verifier !!", "Votre Num doit etre composé de huit chiffres! ");
                return false;
            }

           if (!Pattern.matches("[A-Za-z]*", txt_nom.getText())) {
                Alert(Alert.AlertType.ERROR, "Données invalides", "Verifier ", "Vérifiez le nom ! ");
                return false;
            }
          if (!Pattern.matches("[A-Za-z]*", txt_prenom.getText())) {
                Alert(Alert.AlertType.ERROR, "Données invalides", "Verifier ", "Vérifiez le prenom ! ");
                return false;
            }
          if (!Pattern.matches("[A-Za-z]*", txt_message.getText())) {
                Alert(Alert.AlertType.ERROR, "Données invalides", "Verifier ", "Vérifiez le mrssage de reclamation ! ");
                return false;
            }
            if (!Pattern.matches("^[a-zA-Z0-9_+&*-]+(?:\\." + "[a-zA-Z0-9_+&*-]+)*@" +"(?:[a-zA-Z0-9-]+\\.)+[a-z" + "A-Z]{2,7}$", txt_email.getText())) {
                Alert(Alert.AlertType.ERROR, "Données invalides", "Verifier ", "Vérifiez votre email ! ");
                return false;
            }
            
       }
        return true;
         
    }
      private Node createPage(int pageIndex) {
        int fromIndex = pageIndex * ITEMS_PER_PAGE;
        int toIndex = Math.min(fromIndex + ITEMS_PER_PAGE, listdata.getPersons().size());
        listviewP.setItems(FXCollections.observableArrayList(listdata.getPersons().subList(fromIndex, toIndex)));
        return listviewP;
      }

private void Filtrer(ActionEvent event) throws IOException {
    //EvenementService es = new EvenementService();
    ObservableList<Reclamation> eventsList = FXCollections.observableArrayList();
   // java.sql.Date currentDate = new java.sql.Date( System.currentTimeMillis() );
    ReclamationDao pdao = ReclamationDao.getInstance();
    java.sql.Date startDate = java.sql.Date.valueOf(dd.getValue());
    java.sql.Date endDate = java.sql.Date.valueOf(df.getValue());
    List<Reclamation> filteredEvents = pdao.filtrerParDate(startDate, endDate);
    eventsList.clear();
    eventsList.addAll(filteredEvents);
    listviewP.setItems(eventsList);
}

private boolean Saisi2(String attribute) {  

    if (attribute.isEmpty()) {
        Alert(Alert.AlertType.ERROR, "Données invalides", "Verifier !!", "Veuillez bien remplir tous les champs !");
        return false;
    } else if (attributes.contains(attribute)) {
        Alert(Alert.AlertType.ERROR, "Données invalides", "Verifier !!", "L'attribut existe déjà !");
        return false;
    } else {
        attributes.add(attribute);
        return true;
    }     
}

    @FXML
    private void gomanipuler(ActionEvent event)   throws IOException {
        Parent root3 = FXMLLoader .load(getClass().getResource("/com/fithnity/view/Mreclamationfront.fxml"));
    Stage window = (Stage) manipuler.getScene().getWindow();
    window.setScene(new Scene(root3));
    }

    @FXML
    private void goo_user(ActionEvent event) throws IOException {
         Parent root3 = FXMLLoader .load(getClass().getResource("/com/fithnity/view/Profile.fxml"));
    Stage window = (Stage) btn_user.getScene().getWindow();
    window.setScene(new Scene(root3));
    }

    @FXML
    private void go_blogfront(ActionEvent event) throws IOException {
         Parent root3 = FXMLLoader .load(getClass().getResource("/com/fithnity/view/ajout_c.fxml"));
    Stage window = (Stage) btn_blog2.getScene().getWindow();
    window.setScene(new Scene(root3));
    }

    @FXML
    private void go_Employefront(ActionEvent event) {
    }

    @FXML
    private void go_Reservationfront(ActionEvent event) {
        try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/com/fithnity/view/ADDreservation.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                    Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    

      
}
