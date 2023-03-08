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
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author msi
 */
public class MenuController implements Initializable {

    @FXML
    private BorderPane bp;
    @FXML
    private AnchorPane AP;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        // loadPage("CategorieInterface");
        loadPage("/com/fithnity/view/ajout_c");
    }    
    
    void loadPage(String page){          
        Parent root = null;
        try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(page+".fxml"));
        root = loader.load();
        Object controller = loader.getController();
        if (controller instanceof Ajout_cController) {
            Ajout_cController ajout_cController = (Ajout_cController) controller;
            ajout_cController.setMenuController(this);
        } else if (controller instanceof AfficheController) {
            AfficheController afficheController = (AfficheController) controller;
            afficheController.setMenuController(this);
        }
        
        

        } catch (Exception ex) {
            Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE,null,ex);
        }
        bp.setCenter(root);
    }

    @FXML
    private void InterAjoutBlog(ActionEvent event) {
        loadPage("/com/fithnity/view/Ajout_Blog");
    }

    @FXML
    private void InterBlog(ActionEvent event) {
        loadPage("/com/fithnity/view/affiche");
    }

    @FXML
    private void InterCommentaire(ActionEvent event) {
        loadPage("/com/fithnity/view/ajout_c");

    }

}
