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
import javafx.scene.control.TableCell;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author MSI
 */
public class AjouterreponsebackController implements Initializable {

    @FXML
    private Button btnn;
    @FXML
    private TextField txt_emailU;
    @FXML
    private TextField txt_messageR;
    @FXML
    private ListView<Reclamation> listviewRec;
     @FXML
    private ListView<Reponse> listviewR;
    @FXML
    private Button btn_reponse;
    @FXML
    private Button btn_acceuil;
    @FXML
    private Button btn_user;
    @FXML
    private Button btn_blog;

     private ListData listdata2 = new ListData();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
               ObservableList<Reponse> data=FXCollections.observableArrayList();
  
//listviewR.setItems(listdata.getPersons()); 
listviewRec.setItems(listdata2.getPersons()); 
         
 
        listviewRec.setOnMouseClicked(event->{

 java.sql.Date currentDate = new java.sql.Date( System.currentTimeMillis() );
//      Reponse current = listviewR.getSelectionModel().getSelectedItem();
        Reclamation current2 = listviewRec.getSelectionModel().getSelectedItem(); //***********
        
;
       
        txt_emailU.setText(current2.getEmail());
         

    });
        
        
        
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

   
     @FXML
    private void ajouter(ActionEvent event) throws IOException { 
        if (Saisi() == true)
        {
//              Reponse current = listviewR.getSelectionModel().getSelectedItem();
              Reclamation current2 = listviewRec.getSelectionModel().getSelectedItem(); //***********
            java.sql.Date currentDate = new java.sql.Date( System.currentTimeMillis() );
	 Reponse p = new Reponse(currentDate, txt_emailU.getText(), txt_messageR.getText(),current2);
           ReponseDao pdao = ReponseDao.getInstance();
            pdao.insert(p);
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Reponse insérée avec succés!");
        alert.show();
	 txt_emailU.setText("");
        txt_messageR.setText("");  	
        //reload
        Parent root2 = FXMLLoader .load(getClass().getResource("/com/fithnity/view/Ajouterreponseback.fxml"));
    Stage window = (Stage) btnn.getScene().getWindow();
    window.setScene(new Scene(root2));
	}
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
		
	}
    
//    @FXML
//    private void modifier(ActionEvent event) throws IOException { 
//		 Reponse current = listviewR.getSelectionModel().getSelectedItem();
//            Reponse p = new Reponse();
////            p.setId(Integer.parseInt(txt_id.getText()));
//            p.setIdReponse(current.getIdReponse());
//           
//            p.setEmailUser(txt_emailU.getText());
//          
//             p.setMessageR(txt_messageR.getText());
//            ReponseDao pdao = ReponseDao.getInstance();
//            pdao.update(p);
//            Alert alert = new Alert(Alert.AlertType.INFORMATION);
//        alert.setTitle("Information Dialog");
//        alert.setHeaderText(null);
//        alert.setContentText("Reponse modifiée avec succés!");
//        alert.show();
//        
//	 txt_emailU.setText("");
//          
//txt_messageR.setText("");  
////reload
//Parent root2 = FXMLLoader .load(getClass().getResource("/com/fithnity/view/AfficherReponse.fxml"));
//    Stage window = (Stage) btnn.getScene().getWindow();
//    window.setScene(new Scene(root2));
//		
//	}
    @FXML
    private void goreponse(ActionEvent event) throws IOException {
    
     Parent root3 = FXMLLoader .load(getClass().getResource("/com/fithnity/view/Mreponse.fxml"));
    Stage window = (Stage) btn_reponse.getScene().getWindow();
    window.setScene(new Scene(root3));
    }
       
//    @FXML
//    private void back(ActionEvent event) throws IOException { 
//		Parent root3 = FXMLLoader .load(getClass().getResource("/com/fithnity/view/Acceuil.fxml"));
//    Stage window = (Stage) retourr.getScene().getWindow();
//    window.setScene(new Scene(root3));
//		
//	}
    
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

           
        
          if (!Pattern.matches("[A-Za-z]*", txt_messageR.getText())) {
                Alert(Alert.AlertType.ERROR, "Données invalides", "Verifier ", "Vérifiez le message de Reponse ! ");
                return false;
            }
            if (!Pattern.matches("^[a-zA-Z0-9_+&*-]+(?:\\." + "[a-zA-Z0-9_+&*-]+)*@" +"(?:[a-zA-Z0-9-]+\\.)+[a-z" + "A-Z]{2,7}$", txt_emailU.getText())) {
                Alert(Alert.AlertType.ERROR, "Données invalides", "Verifier ", "Vérifiez votre email ! ");
                return false;
            }
           
        }
        return true;
         
    }
    
}
