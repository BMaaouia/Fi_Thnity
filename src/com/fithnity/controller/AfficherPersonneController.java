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
//**********************************



/**
 * FXML Controller class
 *
 * @author wiemhjiri
 */
public class AfficherPersonneController implements Initializable {

    /**
     * Initializes the controller class.
     */
     @FXML
    private ListView<Reclamation> listviewP;
    @FXML
    private TableView<Reclamation> personsTable;
    @FXML
    private TableColumn<Reclamation,String> NomColonne;
    @FXML
    private TableColumn<Reclamation,String> PrenomColonne;
      @FXML
    private TextField txt_nom;
       @FXML
    private TextField txt_prenom;
    @FXML
    private Label idLabel;
    @FXML
    private Label nomLabel;
    @FXML
    private Label prenomLabel;

    private ListData listdata = new ListData();
   @FXML
    private Button retour;
     @FXML
    private Button btnn;
     @FXML
    private AnchorPane gestionReclamation;
      @FXML
    private Button modif2;
      
    @FXML
    private Button btn_pie;
    
    @FXML
    private Button btn_delete;
     @FXML
    private Button btn_afficher;
     @FXML
    private Button retourr;
       @FXML
    private Button modif;
//    @FXML
//    private TextField txt_id;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
          ObservableList<Reclamation> data=FXCollections.observableArrayList();
  
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
        Reclamation current = listviewP.getSelectionModel().getSelectedItem();
        // txt_id.setText(Integer.toString(current.getId()));
       
         current.getId();
        txt_nom.setText(current.getNom());
        txt_prenom.setText(current.getPrenom());        
    });
            
    }


     @FXML
    private void ajouter(ActionEvent event) throws IOException { 
	 Reclamation p = new Reclamation(txt_nom.getText(), txt_prenom.getText());
            ReclamationDao pdao = ReclamationDao.getInstance();
            pdao.insert(p);
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Reclamation insérée avec succés!");
        alert.show();
        txt_nom.setText("");
        txt_prenom.setText("");	
		
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
		
	}
    
    @FXML
    private void modifier(ActionEvent event) throws IOException { 
		 Reclamation current = listviewP.getSelectionModel().getSelectedItem();
            Reclamation p = new Reclamation();
//            p.setId(Integer.parseInt(txt_id.getText()));
            p.setId(current.getId());
            p.setNom(txt_nom.getText());
            p.setPrenom(txt_prenom.getText());
            ReclamationDao pdao = ReclamationDao.getInstance();
            pdao.update(p);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Reclamation modifiée avec succés!");
        alert.show();
            txt_nom.setText("");
        txt_prenom.setText("");
		
	}
   
   @FXML
    private void rafraichir(ActionEvent event) throws IOException { 
		Parent root2 = FXMLLoader .load(getClass().getResource("/com/fithnity/view/AfficherPersonne.fxml"));
    Stage window = (Stage) retour.getScene().getWindow();
    window.setScene(new Scene(root2));
		
	}
    
    @FXML
    private void back(ActionEvent event) throws IOException { 
		Parent root3 = FXMLLoader .load(getClass().getResource("/com/fithnity/view/Acceuil.fxml"));
    Stage window = (Stage) retourr.getScene().getWindow();
    window.setScene(new Scene(root3));
		
	}
    
    
 
     
       

}
