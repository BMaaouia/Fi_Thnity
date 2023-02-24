/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fithnity.controller;

import com.esprit.dao.BlogDao;
import com.esprit.entity.Blog;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author wiemhjiri
 */
public class Ajout_BlogController implements Initializable {

    @FXML
    private Button btn_add;
    @FXML
    private Button affiche;
    @FXML
    private TextField text_blog;
    @FXML
    private TextField image_blog;
    

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
    private void add(ActionEvent event) throws SQLException {
        
        String text,image;
        text=text_blog.getText();
        image=image_blog.getText();
        Blog b = new Blog(text, image);
        //Volet 1: Validation de champs de texte vides
 if (text.isEmpty() || image.isEmpty()) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText("Champ(s) vide(s)");
        alert.setContentText("Veuillez remplir tous les champs obligatoires");
        alert.showAndWait();
    } else {
        
            BlogDao pdao = BlogDao.getInstance();
            pdao.insert(b);
        
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Blog insérée avec succés!");
        alert.show();
        text_blog.setText("");
        image_blog.setText("");
     } 
 
 /*PreparedStatement stmt = cnx.prepareStatement("SELECT COUNT(*) FROM blog WHERE text_blog = ?");
 stmt.setString(1, text_blog);
 ResultSet rs = stmt.executeQuery();
if (rs.next() && rs.getInt(1) > 0) {
    Alert alert = new Alert(AlertType.ERROR);
    alert.setTitle("Erreur");
    alert.setHeaderText("Produit déjà existant");
    alert.setContentText("Le produit " + text_blog + " existe déjà dans la base de données");
    alert.showAndWait();
}
    /*  String path = b.getimage_blog();
               File file=new File(path);
              Image img = new Image(file.toURI().toString());
               imageview.setImage(img);*/
         }   
    @FXML
    private void show(ActionEvent event) {
         try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/com/esprit/view/affiche.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(Ajout_BlogController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

   
}
