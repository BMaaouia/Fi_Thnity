/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fithnity.controller;
import javafx.scene.control.ListView;
import com.esprit.dao.BlogDao;
import com.esprit.dao.CommentDao;
import com.esprit.entity.Blog;
import com.esprit.entity.Comment;
import java.awt.Button;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author user
 */
public class Ajout_cController implements Initializable {

    @FXML
    private TextField text_n;
    @FXML
    private TextField text_c;
    @FXML
    private javafx.scene.control.Button btn_c;
    @FXML
    private javafx.scene.control.Button btn_c1;
      /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  
     @FXML
    private void add(ActionEvent event)  throws SQLException {
       String text,np;
        text=text_c.getText();
        np=text_n.getText();
        Comment c = new Comment(text, np);
        //Volet 1: Validation de champs de texte vides
 if (text.isEmpty() || np.isEmpty()) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText("Champ(s) vide(s)");
        alert.setContentText("Veuillez remplir tous les champs obligatoires");
        alert.showAndWait();
    } else {
        
            CommentDao pdao = CommentDao.getInstance();
            pdao.insert(c);
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Blog insérée avec succés!");
        alert.show();
        text_c.setText("");
        text_n.setText("");
    }
    
 
    }
    @FXML
    private void show(ActionEvent event) {
         try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/com/esprit/view/affiche_c.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(Ajout_cController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
}
