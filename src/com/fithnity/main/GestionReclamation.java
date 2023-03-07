/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fithnity.main;

import com.fithnity.controller.ListData;

import com.fithnity.controller.ListDataReponse;
import com.fithnity.controller.AjouterreclamationfrontController;
import com.fithnity.controller.AcceuilfrontController;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
/**
 *
 * @author MSI
 */
public class GestionReclamation extends Application  {

   
    private Stage primaryStage;
    private Parent parentPage;
   
    @Override
    public void start(Stage primaryStage) throws IOException {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Fi_Thnity");
        
        parentPage = FXMLLoader.load(getClass().getResource("/com/fithnity/view/acceuilfront.fxml"));
        Scene scene = new Scene(parentPage, 1200, 800);
        scene.setFill(Color.TRANSPARENT);
        primaryStage.setScene(scene);
        this.primaryStage.setScene(scene);
        this.primaryStage.show();
scene.getStylesheets().add("/com/fithnity/view/style.css");
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
