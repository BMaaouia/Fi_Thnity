/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fithnity.controller;
import javafx.scene.control.ListView;
import com.fithnity.dao.BlogDao;
import com.fithnity.dao.CommentDao;
import com.fithnity.entity.Blog;
import com.fithnity.entity.Comment;
import com.fithnity.entity.Pdf;
import com.itextpdf.text.DocumentException;
import java.awt.Button;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author user
 */
public class Ajout_cController implements Initializable {

    
        private ListData listdata = new ListData();
    ObservableList<Blog> data=FXCollections.observableArrayList();
    private ListDatac listdatac = new ListDatac();
    ObservableList<Comment> datac=FXCollections.observableArrayList();
    BlogDao bdao = BlogDao.getInstance();
    CommentDao bdaoC = CommentDao.getInstance();

    @FXML
    private TextField text_n;
    @FXML
    private TextField text_c;
    @FXML
    private javafx.scene.control.Button btn_c;
    @FXML
    private javafx.scene.control.Button btn_c1;
    @FXML
    private ListView<Blog> list_b;
    @FXML
    private ListView<Comment> list_c;
    @FXML
    private Label idblogselected;
      /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
          list_b.setItems(listdata.getBlogs());

    }  
    
      public void getEvents() {  

        
            // TODO
    
            list_b.setItems(listdata.getBlogs());
            ObservableList<Blog> data=FXCollections.observableArrayList();
            

  }

     @FXML
    private void add(ActionEvent event)  throws SQLException {
       String text,np;
        text=text_c.getText();
        np=text_n.getText();
        Comment c = new Comment(text, np,Integer.valueOf(idblogselected.getText()));
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
             list_c.setItems(bdaoC.displayById_Blog(Integer.valueOf(idblogselected.getText())));
            datac=FXCollections.observableArrayList();

    
 
    }
    @FXML
    private void show(ActionEvent event) {
         try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/com/fithnity/view/affiche_c.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(Ajout_cController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    @FXML
    private void Load_Comment(MouseEvent event) {
        
            Blog selectedItem = list_b.getSelectionModel().getSelectedItem();
                     
            idblogselected.setText(String.valueOf(selectedItem.getId_blog()));
            list_c.setItems(bdaoC.displayById_Blog(selectedItem.getId_blog()));
            datac=FXCollections.observableArrayList();

    }

    @FXML
    private void PDFbtn(ActionEvent event) throws FileNotFoundException, SQLException, DocumentException {
        Pdf p = new Pdf();
        p.add("Blogs");
        
        
    }
}
