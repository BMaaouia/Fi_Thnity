/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fithnity.controller;

import com.esprit.dao.BlogDao;
import com.fithnity.controller.ListDatac;
import com.esprit.dao.CommentDao;
import com.esprit.entity.Blog;
import com.esprit.entity.Comment;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author user
 */
public class Affiche_cController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private ListView<Comment> list_c;
    private ListDatac listdatac = new ListDatac();
    ObservableList<Comment> data=FXCollections.observableArrayList();
    
  

    @FXML
    private Button btn_ac;
    @FXML
    private Button btn_dc;
    @FXML
    private Button btn_adc;
    
    
    
    CommentDao bdao = CommentDao.getInstance();
    @FXML
    private TextField text_c;
    @FXML
    private Button btn_up;
            
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
             list_c.setItems(listdatac.getComments());
            list_c.setOnMouseClicked(e->{Comment current = list_c.getSelectionModel().getSelectedItem();
//     current.getId_comment();
     
     text_c.setText(current.gettext_comment());
            });
    }    
    @FXML
    private void add(ActionEvent event) {
        try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/com/esprit/view/ajout_c.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(Affiche_cController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    @FXML
    private void supprime(ActionEvent event) {
         Comment selectedItem = list_c.getSelectionModel().getSelectedItem();

                System.out.println(selectedItem);
                 list_c.getItems().remove(selectedItem);
                 bdao.delete(selectedItem);
    
   
}

    @FXML
    private void modifier1(ActionEvent event) throws IOException {
        Comment current = list_c.getSelectionModel().getSelectedItem();
            Comment p = new Comment();
//            p.setId(Integer.parseInt(txt_id.getText()));
            p.setId_comment(current.getId_comment());
            p.settext_comment(text_c.getText());
            
            
            CommentDao pdao = CommentDao.getInstance();
            pdao.update(p);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("comment modifiée avec succés!");
        alert.show();
            text_c.setText("");
        
	 
//reload
Parent root2 = FXMLLoader.load(getClass().getResource("/com/esprit/view/affiche_c.fxml"));
    Stage window = (Stage) btn_up.getScene().getWindow();
    window.setScene(new Scene(root2));
    }
}