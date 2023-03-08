/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fithnity.controller;

import com.fithnity.service.ReclamationDao;
import com.fithnity.utils.ConnexionSingleton;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author MSI
 */
public class AcceuilController implements Initializable {
 private AnchorPane rootLayout;
    private Stage primaryStage;
    @FXML
    private Button btn_user;
    @FXML
    private Button btn_blog;
    @FXML
    private Button btn_acceuil;
    @FXML
    private Button btn_front;
    @FXML
    private AnchorPane translate;
    @FXML
    private PieChart piechart;
    
    
     private static ReclamationDao instance;
    private Statement st;
    private ResultSet rs;
   // ConnexionSingleton cs;
      ConnexionSingleton cs=ConnexionSingleton.getInstance();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
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
           //  btn_blog.setStyle("-fx-background-color :  #05071F");
            } catch (IOException ex) {
                Logger.getLogger(AcceuilController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
            btn_front.setOnAction(event -> {
            try {//FXMLLoader loader = new FXMLLoader();
                //loader.setLocation(getClass().getResource("/com/esprit/view/Accueil.fxml"));
                Parent page2 = FXMLLoader.load(getClass().getResource("/com/fithnity/view/ajouterreclamationfront.fxml"));

                  Scene scene = btn_front.getScene();
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
               page2.translateXProperty().set(-scene.getWidth());

        AnchorPane parentContainer = (AnchorPane) btn_front.getScene().getRoot();

        parentContainer.getChildren().add(page2);

        Timeline timeline = new Timeline();
        KeyValue kv = new KeyValue(page2.translateXProperty(), 0, Interpolator.EASE_OUT);
        KeyFrame kf = new KeyFrame(Duration.seconds(1), kv);
        timeline.getKeyFrames().add(kf);
        timeline.setOnFinished(t -> {
            parentContainer.getChildren().remove(translate);
        });
        timeline.play(); 
            } catch (IOException ex) {
                Logger.getLogger(AcceuilController.class.getName()).log(Level.SEVERE, null, ex);
            }

        });



          btn_blog.setOnAction(event -> {
            try {//FXMLLoader loader = new FXMLLoader();
                //loader.setLocation(getClass().getResource("/com/esprit/view/Accueil.fxml"));
                Parent page2 = FXMLLoader.load(getClass().getResource("/com/fithnity/view/reclamationback.fxml"));
 
                Scene scene = new Scene(page2);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
                  btn_blog.setStyle("-fx-background-color : #1620A1");
            btn_blog.toFront();
          
            
            } catch (IOException ex) {
                Logger.getLogger(reclamationbackController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
          
//             ObservableList<PieChart.Data> data = FXCollections.observableArrayList(
//            new PieChart.Data("Apple", 50),
//            new PieChart.Data("Banana", 50),
//            new PieChart.Data("Orange", 1)
//        );
//             
//         //    stat();
//        piechart.setData(data);
  ReclamationDao pdao = ReclamationDao.getInstance();
 HashMap<String, Integer> counts = new HashMap<>();
        pdao. displayAllList().stream()
                .forEach(p -> counts.put(p.getTypeR(), counts.getOrDefault(p.getTypeR(), 0) + 1));
        piechart.getData().clear();
          
piechart.setLegendVisible(false); // hide the legend
piechart.setLabelsVisible(true); // show labels for each slice
piechart.setLabelLineLength(10); // adjust the length of the label lines
piechart.setStartAngle(180); // adjust the starting angle of the pie chart
piechart.setAnimated(true);
  counts.forEach((role, count) -> piechart.getData().add(new PieChart.Data(role+" : "+count, count)));
//*************************************************
Timeline timeline = new Timeline();
DoubleProperty startAngleProperty = new SimpleDoubleProperty(180);
piechart.startAngleProperty().bind(startAngleProperty);
timeline.getKeyFrames().addAll(
    new KeyFrame(Duration.ZERO, new KeyValue(startAngleProperty, 180)),
    new KeyFrame(Duration.seconds(3.0), new KeyValue(startAngleProperty, 0))
);
timeline.play(); 

for (PieChart.Data slice : piechart.getData()) {
    Node node = slice.getNode();
    node.setOnMouseEntered(event -> {
        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(100), node);
        scaleTransition.setToX(1.1);
        scaleTransition.setToY(1.1);
        scaleTransition.play();
    });
    node.setOnMouseExited(event -> {
        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(100), node);
        scaleTransition.setToX(1.0);
        scaleTransition.setToY(1.0);
        scaleTransition.play();
    });
}
    }
    
//     public void populateChart() {
//        HashMap<String, Integer> counts = new HashMap<>();
//        utilisateurService.afficherUtilisateurs().stream()
//                .filter(user -> !user.getRole().equals("Admin"))
//                .forEach(user -> counts.put(user.getRole(), counts.getOrDefault(user.getRole(), 0) + 1));
//        pieChart.getData().clear();
//        pieChart.setLegendVisible(false);
//        pieChart.setStartAngle(90);
//        pieChart.setLabelsVisible(true);
//        pieChart.setClockwise(false);
//        pieChart.setAnimated(true);
//
//
//        counts.forEach((role, count) -> pieChart.getData().add(new PieChart.Data(role+" : "+count, count)));
//       
//    }
//     private void stat()
//    {
//         // Connection con = DBConnection.getInstance().getCon();
//            ObservableList<PieChart.Data>data=FXCollections.observableArrayList();
//      try {
//           
//       //   String query = "select quantite As Qtn,nomProduit As nom from produit group by nomProduit" ;
//       
//    
//          rs=st.executeQuery(query);    
//                     
//            while (rs.next()){  
//               
//               data.add(new PieChart.Data(rs.getString("nom"),rs.getInt("Qtn")));
//            }     
//        } catch (SQLException ex) {
//            Logger.getLogger(AcceuilController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//      
////        piechart.setTitle("*Statistique des produits selon quantite*");
////        piechart.setLegendSide(Side.LEFT);
////        piechart.setData(data);
//    
//    }
    
//    
//    

    }    
    

