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
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author MSI
 */
public class reclamationbackController implements Initializable {
@FXML
    private ListView<Reclamation> listviewP;
    private Button btn_display1;
    private Button btn_display;
    @FXML
    private Button btn_user;
    @FXML
    private Button btn_blog;
    @FXML
    private Button btn_acceuil;

    private ListData listdata = new ListData();
    @FXML
    private Button btn_reponse;
    @FXML
    private TextField search;
      ReclamationDao pdao = ReclamationDao.getInstance();
//   List<Reclamation> r= pdao.rechercher(search.getText());
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        //  ObservableList<Reclamation> reclamationsList = FXCollections.observableArrayList(r);
        listviewP.setItems(listdata.getPersons());
        
        //**************************************************************************************
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
             btn_blog.setStyle("-fx-background-color :  #05071F");
            } catch (IOException ex) {
                Logger.getLogger(AcceuilController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

//        btn_display.setOnAction(event -> {
//            try {//FXMLLoader loader = new FXMLLoader();
//                //loader.setLocation(getClass().getResource("/com/esprit/view/Accueil.fxml"));
//                Parent page2 = FXMLLoader.load(getClass().getResource("/com/fithnity/view/AfficherPersonne.fxml"));
//                // Give the controller access to the main app.
////                AfficherPersonneController controller =loader.getController();
////                controller.setListData(new ListData());
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
//                Logger.getLogger(AcceuilController.class.getName()).log(Level.SEVERE, null, ex);
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
          
          
    }    

//     @FXML
//      public void handleClicks(ActionEvent actionEvent) {
//        if (actionEvent.getSource() == btn_display) {
//            btn_display.setStyle("-fx-background-color : #1620A1");
//            btn_display.toFront();
//        }
//        if (actionEvent.getSource() == btn_display1) {
//            btn_display1.setStyle("-fx-background-color : #53639F");
//           btn_display1.toFront();
//        }
//        if (actionEvent.getSource() == btn_user) {
//            btn_user.setStyle("-fx-background-color : #02030A");
//            blogpane.setVisible(true);
//        }
//        if(actionEvent.getSource()==btn_blog)
//        {
//            btn_blog.setStyle("-fx-background-color : #464F67");
//            btn_blog.toFront();
//        }
//         if(actionEvent.getSource()==btn_employe)
//        {
//            btn_employe.setStyle("-fx-background-color : #464F67");
//            btn_employe.toFront();
//        }
//    }

    @FXML
    private void goreponse(ActionEvent event) throws IOException {
    
     Parent root3 = FXMLLoader .load(getClass().getResource("/com/fithnity/view/ajouterreponseback.fxml"));
    Stage window = (Stage) btn_reponse.getScene().getWindow();
    window.setScene(new Scene(root3));
    }

    @FXML
    private void chercher(ActionEvent event) {
   
//      ReclamationDao pdao = ReclamationDao.getInstance();
//      List<Reclamation> r= pdao.rechercher(search.getText());
//        ObservableList<Reclamation> reclamationsList = FXCollections.observableArrayList(r);
//      
     //reclamationsList.setItems(r);
    }
}
