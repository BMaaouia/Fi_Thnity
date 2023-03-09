/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fithnity.controller;

import com.fithnity.service.BlogDao;
import com.fithnity.service.CommentDao;
import com.fithnity.entity.Blog;
import com.fithnity.entity.Comment;
import com.fithnity.utils.Pdf;
import com.itextpdf.text.DocumentException;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;
import java.util.Random;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author ameni touihri
 */
public class AfficheController implements Initializable {  

   /**
     * Initializes the controller class.
     */
    @FXML
    private ListView<Blog> list_b;
    private ListData listdata = new ListData();
    ObservableList<Blog> data=FXCollections.observableArrayList();
    private ListDatac listdatac = new ListDatac();
    ObservableList<Comment> datac=FXCollections.observableArrayList();
    
  

    @FXML
    private Button btn_supprimer;
    @FXML
    private Button ajout;
    
    
    BlogDao bdao = BlogDao.getInstance();
    CommentDao bdaoC = CommentDao.getInstance();
    @FXML
    private Button btn_modif;
    @FXML
    private TextField text_blog;
     @FXML
    private TextField titre_blog;
    @FXML
    private TextField image_blog;
    private ImageView imageview;
    @FXML
    private ListView<Comment> list_c;
    
    private MenuController menuController;
    @FXML
    private Button retour;
    @FXML
    private AnchorPane container;
    @FXML
    private Button btn_acceuil;
    @FXML
    private Button btn_user;
    @FXML
    private Button btn_reclamation;
    @FXML
    private Button btn_employe;
    @FXML
    private Button btn_reservation;
   
    
    


    @Override
    public void initialize(URL url, ResourceBundle rb) {
         // TODO
        
             list_b.setItems(listdata.getBlogs());
             

    }

    


    @FXML
    private void add(ActionEvent event) {
        try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/com/fithnity/view/Ajout_Blog.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(AfficheController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    private void click(MouseEvent event) {
         Blog selectedItem = list_b.getSelectionModel().getSelectedItem();

                System.out.println(selectedItem);
                 list_b.getItems().remove(selectedItem);
                 bdao.delete(selectedItem);
    }

    /* private void update_text(TableColumn.CellEditEvent<Blog, String> e) {
        TablePosition<Blog, String> pos = e.getTablePosition();
             String newBlog = e.getNewValue();
             int row = pos.getRow();
             Blog blog = e.getTableView().getItems().get(row);
             blog.settext_blog(newBlog);
             bdao.update(blog);
    }

    private void update_image(TableColumn.CellEditEvent<Blog, String> e) {
        TablePosition<Blog, String> pos = e.getTablePosition();
             String newBlog = e.getNewValue();
             int row = pos.getRow();
             Blog blog = e.getTableView().getItems().get(row);
             blog.setimage_blog(newBlog);
             bdao.update(blog); 
    }
*/
   @FXML 
  private void click(ActionEvent event) {
        Blog selectedItem = list_b.getSelectionModel().getSelectedItem();

              
        if (list_b.getSelectionModel().getSelectedIndex() == -1) {
            Alert alert1 = new Alert(Alert.AlertType.WARNING);
            alert1.setTitle("No Selected Blog");
            alert1.setHeaderText("Error 403 !");
            alert1.setContentText("Select a blog to delete.");
            ButtonType buttonTypeYes = new ButtonType("OK");
            alert1.getButtonTypes().setAll(buttonTypeYes);
            alert1.showAndWait();
            System.out.println(selectedItem);
                 list_b.getItems().remove(selectedItem);
                 bdao.delete(selectedItem);
        } else {
            Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Are you sure to delete?");
            alert1.setHeaderText("Confirmation!");
            alert1.setContentText("You're attempting to delete a blog.");
            ButtonType buttonTypeYes = new ButtonType("OK");
            ButtonType buttonTypeNo = new ButtonType("Cancel");
            alert1.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);
            Optional<ButtonType> result = alert1.showAndWait();
            if (alert1.getResult().getText().equals("OK")) {
           
                System.out.println(selectedItem);
                 list_b.getItems().remove(selectedItem);
                 bdao.delete(selectedItem);
                
            }
           Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information ");
        
        alert.setContentText("Blog deleted successfully!");
        alert.showAndWait();
        getEvents();

        }

        

    }   
  public void getEvents() {  

        
            // TODO
    
            list_b.setItems(listdata.getBlogs());
            ObservableList<Blog> data=FXCollections.observableArrayList();
            

  }

     @FXML
    private void modifier2(ActionEvent event) throws IOException  { 
		 Blog current = list_b.getSelectionModel().getSelectedItem();
            Blog p = new Blog();
//            p.setId(Integer.parseInt(txt_id.getText()));
            p.setId_blog(current.getId_blog());
            p.settitre_blog(titre_blog.getText());
            p.settext_blog(text_blog.getText());
            p.setimage_blog(image_blog.getText());

             String titre,text,image;
        titre=titre_blog.getText();
        text=text_blog.getText();
        image=image_blog.getText();
             if (titre.isEmpty() ||text.isEmpty() || image.isEmpty()) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText("Champ(s) vide(s)");
        alert.setContentText("Veuillez remplir tous les champs obligatoires");
        alert.showAndWait();
    }else{
            
            BlogDao pdao = BlogDao.getInstance();
            pdao.update(p);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Blog modifiée avec succés!");
        alert.show();
        titre_blog.setText("");
            text_blog.setText("");
        image_blog.setText("");	
	 
//reload
//Parent root2 = FXMLLoader .load(getClass().getResource("/com/fithnity/view/affiche.fxml"));
//    Stage window = (Stage) btn_modif.getScene().getWindow();
//    window.setScene(new Scene(root2));
        menuController.loadPage("/com/fithnity/view/affiche");
	
        
    
    
    }}
    

    @FXML
    private void Load_Comment(MouseEvent event) {
        
                 Blog selectedItem = list_b.getSelectionModel().getSelectedItem();
                titre_blog.setText(selectedItem.gettitre_blog());
                text_blog.setText(selectedItem.gettext_blog());
                image_blog.setText(selectedItem.getimage_blog());

                     
            list_c.setItems(bdaoC.displayById_Blog(selectedItem.getId_blog()));
            datac=FXCollections.observableArrayList();

    }

    @FXML
    private void PDFbtn(ActionEvent event) throws FileNotFoundException, SQLException, DocumentException {
        Pdf p = new Pdf();
        p.add("Blogs");
        
        
    }

    public void setMenuController(MenuController menuController) {
    this.menuController = menuController;
    }

    @FXML
    private void goretour(ActionEvent event) throws IOException {
        Parent root3 = FXMLLoader .load(getClass().getResource("/com/fithnity/view/Ajout_Blog.fxml"));
    Stage window = (Stage) retour.getScene().getWindow();
    window.setScene(new Scene(root3));
    } 
        
    @FXML
    private void go_userback(ActionEvent event) {
        try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/com/fithnity/view/AdminPanel.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                    Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    @FXML
    private void go_dashboardback(ActionEvent event) throws IOException {
        Parent root3 = FXMLLoader .load(getClass().getResource("/com/fithnity/view/Acceuil.fxml"));
    Stage window = (Stage) btn_reclamation.getScene().getWindow();
    window.setScene(new Scene(root3));
    }

    @FXML
    private void go_reclamationback(ActionEvent event) throws IOException {
 Parent root3 = FXMLLoader .load(getClass().getResource("/com/fithnity/view/reclamationback.fxml"));
    Stage window = (Stage) btn_reclamation.getScene().getWindow();
    window.setScene(new Scene(root3));
    }

    

    @FXML
    private void go_employeback(ActionEvent event) {
        try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/com/fithnity/view/Ajout_employée.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                    Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    @FXML
    private void go_reservationback(ActionEvent event) {
    }

    
}


