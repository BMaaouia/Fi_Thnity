/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fithnity.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


import com.fithnity.entity.Reclamation;
import com.fithnity.service.ReponseDao;
import com.fithnity.entity.Reponse;
import com.fithnity.service.ReclamationDao;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
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
import javafx.scene.control.TableCell;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import javafx.util.Duration;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author MSI
 */
public class MreponseController implements Initializable {

    @FXML
    private TextField txt_emailU;
    @FXML
    private TextArea txt_messageR;
    @FXML
    private Button btn_acceuil;
    @FXML
    private Button btn_user;
    @FXML
    private Button btn_blog;
    @FXML
    private ListView<Reponse> listviewR;
    @FXML
    private Button retourr;
    @FXML
    private Button btn_delete;
    @FXML
    private Button modif2;
    
     private ListDataReponse listdata = new ListDataReponse();
  
    private ListDataReclamation listdata2 = new ListDataReclamation();
    @FXML
    private Pagination pagination;
    private final int ITEMS_PER_PAGE = 5;
    @FXML
    private TextField search;
    @FXML
    private DatePicker dd;
    @FXML
    private DatePicker df;
    @FXML
    private Button btn_blog2;
    @FXML
    private Button btn_employe;
    @FXML
    private Button btn_reservation;
    @FXML
    private Button btn_offre;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
               //*****************************search*****************************************************


search.textProperty().addListener((observable, oldValue, newValue) -> {
    ReponseDao pdao = ReponseDao.getInstance();
    List<Reponse> r = pdao.rechercher(newValue);
    ObservableList<Reponse> reclamationsList = FXCollections.observableArrayList(r);
    
    listviewR.setItems(reclamationsList);
});


          //********************************************************************************
          
        int pageCount = (int) Math.ceil(listdata.getPersons().size() * 1.0 / ITEMS_PER_PAGE);
        pagination.setPageCount(pageCount);
        pagination.setPageFactory(this::createPage);
        
        //**************************************************************************
              ObservableList<Reponse> data=FXCollections.observableArrayList();
  
listviewR.setItems(listdata.getPersons()); 
//listviewRec.setItems(listdata2.getPersons()); 
         
 
        listviewR.setOnMouseClicked(event->{
 java.sql.Date currentDate = new java.sql.Date( System.currentTimeMillis() );
        Reponse current = listviewR.getSelectionModel().getSelectedItem();
      
         current.getIdReponse();
       
        txt_emailU.setText(current.getEmailUser());
         
txt_messageR.setText(current.getMessageR());        
//current.getDate();
    });
        
        
        //**************************************************************************
//         btn_acceuil.setOnAction(event -> {
//            try {//FXMLLoader loader = new FXMLLoader();
//                //loader.setLocation(getClass().getResource("/com/esprit/view/Accueil.fxml"));
//                Parent page2 = FXMLLoader.load(getClass().getResource("/com/fithnity/view/Acceuil.fxml"));
//                // Give the controller access to the main app.
////                AfficherPersonneController controller =loader.getController();
////                controller.setListData(new ListDataReclamation());
//                Scene scene = new Scene(page2);
//                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//                stage.setScene(scene);
//                stage.show();
//                  btn_acceuil.setStyle("-fx-background-color : #1620A1");
//            btn_acceuil.toFront();
//             btn_blog.setStyle("-fx-background-color :  #05071F");
//            } catch (IOException ex) {
//                Logger.getLogger(AcceuilController.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        });

//        btn_display.setOnAction(event -> {
//            try {//FXMLLoader loader = new FXMLLoader();
//                //loader.setLocation(getClass().getResource("/com/esprit/view/Accueil.fxml"));
//                Parent page2 = FXMLLoader.load(getClass().getResource("/com/fithnity/view/AfficherPersonne.fxml"));
//                // Give the controller access to the main app.
////                AfficherPersonneController controller =loader.getController();
////                controller.setListData(new ListDataReclamation());
//                Scene scene = new Scene(page2);
//                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//                stage.setScene(scene);
//                stage.show();
//                  btn_display.setStyle("-fx-background-color : #1620A1");
//            btn_display.toFront();
//             btn_blog.setStyle("-fx-background-color :  #05071F");
//            } catch (IOException ex) {
//                Logger.getLogger(AcceuilController.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        });
//        
//         btn_display1.setOnAction(event -> {
//            try {//FXMLLoader loader = new FXMLLoader();
//                //loader.setLocation(getClass().getResource("/com/esprit/view/Accueil.fxml"));
//                Parent page2 = FXMLLoader.load(getClass().getResource("/com/fithnity/view/AfficherReponse.fxml"));
//                // Give the controller access to the main app.
////                AfficherPersonneController controller =loader.getController();
////                controller.setListData(new ListDataReclamation());
//                Scene scene = new Scene(page2);
//                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//                stage.setScene(scene);
//                stage.show();
//                  btn_display1.setStyle("-fx-background-color : #1620A1");
//            btn_display1.toFront();
//             btn_blog.setStyle("-fx-background-color :  #05071F");
//             
//            } catch (IOException ex) {
//                Logger.getLogger(AcceuilController.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        });
          btn_blog.setOnAction(event -> {
            try {//FXMLLoader loader = new FXMLLoader();
                //loader.setLocation(getClass().getResource("/com/esprit/view/Accueil.fxml"));
                Parent page2 = FXMLLoader.load(getClass().getResource("/com/fithnity/view/blog.fxml"));
                // Give the controller access to the main app.
//                AfficherPersonneController controller =loader.getController();
//                controller.setListData(new ListDataReclamation());
                Scene scene = new Scene(page2);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
                  btn_blog.setStyle("-fx-background-color : #1620A1");
            btn_blog.toFront();
           //  btn_diplay.setStyle("-fx-background-color :  #05071F");
            
            } catch (IOException ex) {
                Logger.getLogger(AcceuilController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }    

    @FXML
    private void delete(ActionEvent event) throws IOException { 
		 ReponseDao pdao = ReponseDao.getInstance();
     Reponse selectedItem2 = listviewR.getSelectionModel().getSelectedItem();
  
    listviewR.getItems().remove(selectedItem2);
      pdao.delete(selectedItem2);  
      Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Reponse suprimée avec succés!");
        alert.show();
         String title = "Done";
        String message = "Reponse Suprimée! ";
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
		 Reponse current = listviewR.getSelectionModel().getSelectedItem();
            Reponse p = new Reponse();
//            p.setId(Integer.parseInt(txt_id.getText()));
            p.setIdReponse(current.getIdReponse());
           
            p.setEmailUser(txt_emailU.getText());
          
             p.setMessageR(txt_messageR.getText());
            ReponseDao pdao = ReponseDao.getInstance();
            pdao.update(p);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Reponse modifiée avec succés!");
        alert.show();
         String title = "Done";
        String message = "Reponse modified! ";
        TrayNotification tray = new TrayNotification();
        AnimationType type=AnimationType.POPUP;
        tray.setTitle(title);
        tray.setMessage(message);
        tray.setNotificationType(NotificationType.SUCCESS);
        tray.showAndDismiss(Duration.millis(1000));
        
	 txt_emailU.setText("");
          
txt_messageR.setText("");  
//reload
Parent root2 = FXMLLoader .load(getClass().getResource("/com/fithnity/view/Mreponse.fxml"));
    Stage window = (Stage) retourr.getScene().getWindow();
    window.setScene(new Scene(root2));
        }
	}
   
   
    @FXML
    private void back(ActionEvent event) throws IOException { 
		Parent root3 = FXMLLoader .load(getClass().getResource("/com/fithnity/view/ajouterreponseback.fxml"));
    Stage window = (Stage) retourr.getScene().getWindow();
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

        if ( txt_emailU.getText().isEmpty() || txt_messageR.getText().isEmpty()) {
            Alert(Alert.AlertType.ERROR, "Données invalides", "Verifier !!", "Veuillez bien remplir tous les champs !");
            return false;
        } else {
 if (!Pattern.matches("^[a-zA-Z0-9_+&*-]+(?:\\." + "[a-zA-Z0-9_+&*-]+)*@" +"(?:[a-zA-Z0-9-]+\\.)+[a-z" + "A-Z]{2,7}$", txt_emailU.getText())) {
              Alert(Alert.AlertType.ERROR, "Données invalides", "Verifier ", "Vérifiez votre email ! ");
                return false;
            }


           
          if (!Pattern.matches("[A-Za-z]*", txt_messageR.getText())) {
                Alert(Alert.AlertType.ERROR, "Données invalides", "Verifier ", "Vérifiez le prenom ! ");
                return false;
            }
          
          
           
        }
        return true;
         
    }
 
 private Node createPage(int pageIndex) {
        int fromIndex = pageIndex * ITEMS_PER_PAGE;
        int toIndex = Math.min(fromIndex + ITEMS_PER_PAGE, listdata.getPersons().size());
        listviewR.setItems(FXCollections.observableArrayList(listdata.getPersons().subList(fromIndex, toIndex)));
        return listviewR;
    }

    @FXML
private void Filtrer(ActionEvent event) throws IOException {
    //EvenementService es = new EvenementService();
    ObservableList<Reponse> eventsList = FXCollections.observableArrayList();
   // java.sql.Date currentDate = new java.sql.Date( System.currentTimeMillis() );
    ReponseDao pdao = ReponseDao.getInstance();
    java.sql.Date startDate = java.sql.Date.valueOf(dd.getValue());
    java.sql.Date endDate = java.sql.Date.valueOf(df.getValue());
    List<Reponse> filteredEvents = pdao.filtrerParDate(startDate, endDate);
    eventsList.clear();
    eventsList.addAll(filteredEvents);
    listviewR.setItems(eventsList);
}

       @FXML
    private void go_userback(ActionEvent event) throws IOException {
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
    private void go_blogback(ActionEvent event) throws IOException {
         Parent root3 = FXMLLoader .load(getClass().getResource("/com/fithnity/view/Ajout_Blog.fxml"));
    Stage window = (Stage) btn_blog2.getScene().getWindow();
    window.setScene(new Scene(root3));
    }

   

    @FXML
    private void go_employeback(ActionEvent event) {
        try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/com/fithnity/view/Ajout_employée.fxml"));
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
