/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fithnity.controller;

import com.fithnity.entity.Employée;
import com.fithnity.entity.Vehicule;
import com.fithnity.service.ServiceEmployée;
import com.fithnity.service.ServiceVehicule;
import com.fithnity.utils.Pdf;
import javafx.geometry.Insets;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class DASHBOARD_vehiculeController implements Initializable {

    
    
    private ListView table;
    private ObservableList data;
    @FXML
    private TextField T;
    @FXML
    private TextField modèle_text;
    @FXML
    private TextField catégorie_text;
    @FXML
    private TextField immatriculation_text;
    @FXML
    private TextField etat_text;
    @FXML
    private Button fx_addv;
    @FXML
    private Button fx_updatev;
    @FXML
    private Button fx_deletev;
    @FXML
    private Button fx_backv;
    @FXML
    private AnchorPane container;
    @FXML
    private Button dashboard_employé;
    @FXML
    private Button btn_acceuil;
    @FXML
    private Button btn_user;
    @FXML
    private Button btn_blog;
    @FXML
    private AnchorPane vehicule_list;
    private ScrollPane véhicule_list_scroll;
    @FXML
    private TilePane véhicule_list_tile;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
            
        ServiceVehicule vs = new ServiceVehicule();
        System.out.println(vs.getAllVehicule().toString());
        
//       table = new ListView();
//        
//         data = getInitialTableData();
//        table.setItems(data);
//
//      
//        
//        TableColumn nameCol = new TableColumn("Modèle");
//        nameCol.setCellValueFactory(new PropertyValueFactory("modele"));
//
//        TableColumn lastanameCol = new TableColumn("Immatriculation");
//        lastanameCol.setCellValueFactory(new PropertyValueFactory("immatriculation"));
//     
//
//        TableColumn emailCol = new TableColumn("Catégorie");
//        emailCol.setCellValueFactory(new PropertyValueFactory("categorie"));
//
//        TableColumn addressCol = new TableColumn("Etat");
//        addressCol.setCellValueFactory(new PropertyValueFactory("etat"));
//
//       // table.getColumns().setAll( nameCol, lastanameCol, emailCol,addressCol);
//        table.setPrefWidth(700);
//        table.setPrefHeight(376);
//      //  table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
//
//        véhicule_list.getChildren().add(table);




            List<Vehicule> items = new ArrayList<>();
            items=vs.getAllVehicule();

             for (Vehicule item : items) {
                
                Image image;
                try {
                    image = new Image(new FileInputStream(item.getImage()));
                    ImageView imageView = new ImageView(image);
                    imageView.setFitHeight(150);
                    imageView.setFitWidth(150);
   
                    Button btn = new Button("Delete");
                    btn.setId(Integer.toString(item.getId()));
                    btn.setStyle("-fx-background-color: black; -fx-background-radius: 20; -fx-font-family: \"Franklin Gothic Medium\";-fx-text-fill: #F9F7DD;");

                    
                    Label Categorie = new Label(item.getCategorie());
                    Label Immatriculation = new Label(item.getImmatriculation());
                    Label Etat = new Label(String.valueOf(item.getEtat()));
                    Label Modele = new Label(item.getModele());

                    
                    
                    
                    Categorie.setStyle("-fx-font-family: \"Franklin Gothic Medium\";");
                    Immatriculation.setStyle("-fx-font-family: \"Franklin Gothic Medium\";");
                    Modele.setStyle("-fx-font-family: \"Franklin Gothic Medium\";");
                    

                    VBox vBox = new VBox();
                    vBox.getChildren().addAll(imageView,Categorie,Immatriculation,Modele,btn);
                    vBox.setAlignment(Pos.CENTER);
                    véhicule_list_tile.getChildren().add(vBox);
                    
                    
                    
                    vBox.setOnMouseClicked(e -> {
                        // Create a new stage to display the subscription details
                        Stage detailsStage = new Stage();
                        detailsStage.setTitle("Subscription Details");

                        // Create text fields to display the subscription details
                        TextField categorieField = new TextField(item.getCategorie());
                        categorieField.setStyle("-fx-background-radius: 20; -fx-effect: dropshadow( gaussian , rgba(0,0,0,0.5) , 10,0,0,1 );");
                        categorieField.setEditable(true);
                        
                        TextField modeleField = new TextField((item.getModele()));
                        modeleField.setStyle("-fx-background-radius: 20; -fx-effect: dropshadow( gaussian , rgba(0,0,0,0.5) , 10,0,0,1 );");
                        modeleField.setEditable(true);
                        
                        TextField ImmatriculationField = new TextField((item.getImmatriculation()));
                        ImmatriculationField.setStyle("-fx-background-radius: 20; -fx-effect: dropshadow( gaussian , rgba(0,0,0,0.5) , 10,0,0,1 );");
                        ImmatriculationField.setEditable(true);
                        
                        TextField EtatField = new TextField(String.valueOf(item.getEtat()));
                        EtatField.setStyle("-fx-background-radius: 20; -fx-effect: dropshadow( gaussian , rgba(0,0,0,0.5) , 10,0,0,1 );");
                        EtatField.setEditable(true);


                        // Add the text fields to a grid pane
                        GridPane detailsPane = new GridPane();
                        detailsPane.setAlignment(Pos.CENTER);
                        detailsPane.setStyle("-fx-background-color: WHITE;");
                        detailsPane.setVgap(40);
                        detailsPane.setHgap(20);
                        
                        Label categorieLabel = new Label("Categorie: ");
                        categorieLabel.setFont(Font.font("Franklin Gothic Medium", FontWeight.NORMAL, 18));
                        detailsPane.add(categorieLabel, 0, 0);
                        detailsPane.add(categorieField, 1, 0);
                        
                        Label modeleLabel = new Label("Modele: ");
                        modeleLabel.setFont(Font.font("Franklin Gothic Medium", FontWeight.NORMAL, 18));
                        detailsPane.add(modeleLabel, 0, 1);
                        detailsPane.add(modeleField, 1, 1);
                        
                        Label ImmatriculationLabel = new Label("Immatriculation: ");
                        ImmatriculationLabel.setFont(Font.font("Franklin Gothic Medium", FontWeight.NORMAL, 18));
                        detailsPane.add(ImmatriculationLabel, 0, 2);
                        detailsPane.add(ImmatriculationField, 1, 2);
                        
                        Label EtatLabel = new Label("Etat: ");
                        EtatLabel.setFont(Font.font("Franklin Gothic Medium", FontWeight.NORMAL, 18));
                        detailsPane.add(EtatLabel, 0, 3);
                        detailsPane.add(EtatField, 1, 3);
                        
                        Button updateButton = new Button("Update");
                        updateButton.setStyle("-fx-background-color: black; -fx-background-radius: 20; -fx-font-family: \"Franklin Gothic Medium\"; -fx-text-fill: #F9F7DD;");
                        detailsPane.add(updateButton, 0, 4, 2, 1);
                        GridPane.setHalignment(updateButton, HPos.CENTER);
                        
                        updateButton.setOnAction(e1 -> {
                            
                            // Update the subscription with the new details
                            item.setCategorie(categorieField.getText());
                            Categorie.setText(item.getCategorie());
                            
                            item.setModele(modeleField.getText());
                            Modele.setText(item.getModele());
                            
                            item.setImmatriculation(ImmatriculationField.getText());
                            Immatriculation.setText(item.getImmatriculation());
                            
                            item.setEtat(Boolean.parseBoolean(EtatField.getText()));
                            Etat.setText(String.valueOf(item.getEtat()));
                            
                            vs.updateVehicule(item, item.getId());
                            // Close the window
                            detailsStage.close();
                            
                        });

                        // Add the grid pane to the scene
                        Scene detailsScene = new Scene(detailsPane, 800, 400);
                        detailsStage.setScene(detailsScene);

                        // Show the stage
                        detailsStage.show();
                    });
                    
                    btn.setOnAction(e -> {
                            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                            alert.setTitle("Confirmation Dialog");
                            alert.setHeaderText("Are you sure you want to Delete this item?");
                            Optional<ButtonType> result = alert.showAndWait();
                            
                            if (result.get() == ButtonType.OK){
                                vs.deleteVehicule(item.getId());
                                véhicule_list_tile.getChildren().remove(vBox);
                            }
                    });

                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(DASHBOARD_vehiculeController.class.getName()).log(Level.SEVERE, null, ex);
                    }}


        
         
         
         
             fx_addv.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    redirectToNewVehicule(event);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        });
            
               
           
                         
                     fx_backv.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
              redirectToMyGallerie(event);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        });
                     
                     
                     
//                     véhicule_list_scroll.setOnMouseClicked(event ->{
//                          Object clickedObject = event.getTarget();
//                            if (clickedObject instanceof Node) {
//                                Node clickedNode = (Node) clickedObject;
//                                // do something with the clicked node
//                                System.out.println(clickedNode);
//                            }
//                            
//                           Vehicule v1 = new Vehicule();
//                           v1.getId();
//                            modèle_text.setText(v1.getModele());
//                            catégorie_text.setText(v1.getCategorie());
//                            immatriculation_text.setText(v1.getImmatriculation());
//                            etat_text.setText(String.valueOf(v1.getEtat()));
//                           
//                            fx_updatev.setOnAction(e->{
//                             
//                            if(modèle_text.getText().isEmpty()||catégorie_text.getText().isEmpty()||immatriculation_text.getText().isEmpty()||etat_text.getText().isEmpty()){
//                                Alert alert = new Alert(Alert.AlertType.ERROR);
//                                alert.setTitle("Erreur");
//                                    alert.setHeaderText("ajouter des champs");
//
//                                    alert.showAndWait();
//                            }else{
//
//                            
//                                try {
//                                    v1.setModele(String.valueOf(modèle_text.getText()));
//                                    v1.setCategorie(String.valueOf(catégorie_text.getText()));
//                                    v1.setImmatriculation(String.valueOf(immatriculation_text.getText()));
//                                   v1.setEtat(Boolean.parseBoolean(etat_text.getText()));
//                                                                                          ;
//                                    
//                                    vs.updateVehicule(v1,Integer.valueOf(v1.getId()));
//                                    
//                                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
//                                    alert.setTitle("Information Dialog");
//                                    alert.setHeaderText(null);
//                                    alert.setContentText("Your Vehicule has been UPDATED successfully!");
//                                    alert.show();
//
//                                    Parent root2 = FXMLLoader .load(getClass().getResource("/com/fithnity/view/DASHBOARD_vehicule.fxml"));
//                                    Stage window = (Stage) fx_updatev.getScene().getWindow();
//                                    window.setScene(new Scene(root2));
//                                } catch (IOException ex) {
//                                    Logger.getLogger(DASHBOARD_vehiculeController.class.getName()).log(Level.SEVERE, null, ex);
//                                }
//                            }
//                             
//                            });
//                     });
        // TODO
    }
//        public void redirectToModifPersonne(ActionEvent event) throws Exception {
//        Parent page1 = FXMLLoader.load(getClass().getResource("/com/fithnity/view/update_employée.fxml"));
//        Scene scene = new Scene(page1);
//        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//        stage.setScene(scene);
//        stage.show();
//    }
        
        public void redirectToNewVehicule(ActionEvent event) throws Exception {
        Parent page1 = FXMLLoader.load(getClass().getResource("/com/fithnity/view/ADD_Vehicule.fxml"));
        Scene scene = new Scene(page1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
       
         
         public void redirectToMyGallerie(ActionEvent event) throws Exception {
        Parent page1 = FXMLLoader.load(getClass().getResource("/com/fithnity/view/DASHBOARD_vehicule.fxml"));
        Scene scene = new Scene(page1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    
}
       private ObservableList getInitialTableData() {
        List list = new ArrayList();
        ServiceVehicule vs = new ServiceVehicule();
        list = vs.getAllVehicule();
        ObservableList data = FXCollections.observableList(list);
        return data;
    }

        // TODO
    

    @FXML
    private void DeleteEmploye(ActionEvent event) {
//        Vehicule v1 = new Vehicule();
//        v1= (Vehicule) table.getSelectionModel().getSelectedItem();
//        
//            ServiceVehicule v = new ServiceVehicule();
//            v.deleteVehicule(v1.getId());
//
//            table.getItems().removeAll(table.getSelectionModel().getSelectedItem());
    }

   

    @FXML
    private void recherche(KeyEvent event) {
//          String searchTerm = T.getText();
//    ServiceVehicule su = new ServiceVehicule();
//    ObservableList<Vehicule> list = su.search1(searchTerm);
//    List<Vehicule> filteredList = list.stream()
//        .filter(entretient -> entretient.getModele().toLowerCase().contains(searchTerm.toLowerCase()))
//        .collect(Collectors.toList());
//
//       table.setItems(FXCollections.observableArrayList(filteredList));
    }

    @FXML
    private void dashboard_employé(ActionEvent event) {
        Parent page1;
        try {
            page1 = FXMLLoader.load(getClass().getResource("/com/fithnity/view/DASHBOARD_employée.fxml"));
            Scene scene = new Scene(page1);
                              Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                              stage.setScene(scene);
                              stage.show();
        } catch (IOException ex) {
            Logger.getLogger(DASHBOARD_employéeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
