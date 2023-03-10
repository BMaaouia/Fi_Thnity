/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fithnity.controller;

import com.fithnity.service.ReclamationDao;
import com.fithnity.entity.Reclamation;
import com.fithnity.utils.Document_Creation;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.Pagination;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

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
    private Button btn_blog;
    @FXML
    private Button btn_acceuil;

    private ListDataReclamation listdata = new ListDataReclamation();
    @FXML
    private Button btn_reponse;
    @FXML
    private TextField search;
      ReclamationDao pdao = ReclamationDao.getInstance();
    @FXML
    private DatePicker dd;
    @FXML
    private DatePicker df;
    @FXML
    private Button pdf;
    @FXML
    private Pagination pagination;
    private final int ITEMS_PER_PAGE = 5;
    @FXML
    private Button btn_blog2;
    @FXML
    private Button btn_reclamation;
    @FXML
    private Button btn_employe;
    @FXML
    private Button btn_reservation;
    @FXML
    private Button btn_offre;
    
 //  List<Reclamation> r= pdao.rechercher(search.getText());
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        int pageCount = (int) Math.ceil(listdata.getPersons().size() * 1.0 / ITEMS_PER_PAGE);
        pagination.setPageCount(pageCount);
        pagination.setPageFactory(this::createPage);
        
        //  ObservableList<Reclamation> reclamationsList = FXCollections.observableArrayList(r);
        listviewP.setItems(listdata.getPersons());
        
        //**************************************************************************************
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


          btn_reclamation.setOnAction(event -> {
            try {//FXMLLoader loader = new FXMLLoader();
                //loader.setLocation(getClass().getResource("/com/esprit/view/Accueil.fxml"));
                Parent page2 = FXMLLoader.load(getClass().getResource("/com/fithnity/view/reclamationback.fxml"));
                // Give the controller access to the main app.
//                AfficherPersonneController controller =loader.getController();
//                controller.setListData(new ListDataReclamation());
                Scene scene = new Scene(page2);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
                  btn_reclamation.setStyle("-fx-background-color : #1620A1");
            btn_reclamation.toFront();
           //  btn_diplay.setStyle("-fx-background-color :  #05071F");
            
            } catch (IOException ex) {
                Logger.getLogger(reclamationbackController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
          //*****************************search*****************************************************


search.textProperty().addListener((observable, oldValue, newValue) -> {
    ReclamationDao pdao = ReclamationDao.getInstance();
    List<Reclamation> r = pdao.rechercher(newValue);
    ObservableList<Reclamation> reclamationsList = FXCollections.observableArrayList(r);
    listviewP.setItems(reclamationsList);
});


          //********************************************************************************
          
  
    }    



    @FXML
    private void goreponse(ActionEvent event) throws IOException {
    
     Parent root3 = FXMLLoader .load(getClass().getResource("/com/fithnity/view/ajouterreponseback.fxml"));
    Stage window = (Stage) btn_reponse.getScene().getWindow();
    window.setScene(new Scene(root3));
    }
    
 @FXML
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

    @FXML
    private void handle(ActionEvent event) {
        try {
                  
                                  Document_Creation dc = new Document_Creation();
            					dc.generatePDF();
            					File file = new File("my_docs.pdf");
            					if (file.exists()) {
            		                long startTime = System.currentTimeMillis();
            		                Desktop.getDesktop().open(file);
            		                long endTime = System.currentTimeMillis();
            		                System.out.println("Total time taken to open file -> "+ file.getName() +" in "+ (endTime - startTime) +" ms");              
            		            } else {
            		                System.out.println("terereraaaa -> "+ file.getAbsolutePath());
                                         String title = "Done";
        String message = "PDF is SUCCEFYLY generated! ";
        TrayNotification tray = new TrayNotification();
        AnimationType type=AnimationType.POPUP;
        tray.setTitle(title);
        tray.setMessage(message);
        tray.setNotificationType(NotificationType.SUCCESS);
        tray.showAndDismiss(Duration.millis(1000));
            		            }
            				} catch (IOException e) {
            					// TODO Auto-generated catch block
            					e.printStackTrace();
            				} catch (SQLException ex) {
                    System.out.println("erreur pdf");                                }
    }

   private Node createPage(int pageIndex) {
        int fromIndex = pageIndex * ITEMS_PER_PAGE;
        int toIndex = Math.min(fromIndex + ITEMS_PER_PAGE, listdata.getPersons().size());
        listviewP.setItems(FXCollections.observableArrayList(listdata.getPersons().subList(fromIndex, toIndex)));
        return listviewP;
    }

    private void goreponseback(Event event) throws IOException  {
       Parent root3 = FXMLLoader .load(getClass().getResource("/com/fithnity/view/ajouterreponseback.fxml"));
    Stage window = (Stage) btn_reponse.getScene().getWindow();
    window.setScene(new Scene(root3));
        
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
