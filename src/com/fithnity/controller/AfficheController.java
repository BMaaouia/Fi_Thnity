/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fithnity.controller;

import com.esprit.dao.BlogDao;
import com.esprit.entity.Blog;
import com.esprit.entity.Comment;
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
    
  

    @FXML
    private Button btn_afficher;
    @FXML
    private Button btn_supprimer;
    @FXML
    private Button ajout;
    
    
    BlogDao bdao = BlogDao.getInstance();
    @FXML
    private Button btn_modif;
    @FXML
    private TextField text_blog;
    @FXML
    private TextField image_blog;
    @FXML
    private ImageView imageview;
    
    


    @Override
    public void initialize(URL url, ResourceBundle rb) {
         // TODO
        
             list_b.setItems(listdata.getBlogs());
            list_b.setOnMouseClicked(e->{Blog current = list_b.getSelectionModel().getSelectedItem();
//     current.getId_blog();
     text_blog.setText(current.gettext_blog());
     image_blog.setText(current.getimage_blog());
            });
     
    }

    

    @FXML
    private void show(ActionEvent event) {
        
        
    }

    @FXML
    private void add(ActionEvent event) {
        try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/com/esprit/view/Ajout_Blog.fxml"));
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

                System.out.println(selectedItem);
                 list_b.getItems().remove(selectedItem);
                 bdao.delete(selectedItem);
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information ");
        alert.setHeaderText("Event delete");
        alert.setContentText("Event deleted successfully!");
        alert.showAndWait();
        getEvents();

        

    }   
  public void getEvents() {  

        
            // TODO
    
            list_b.setItems(listdata.getBlogs());
            ObservableList<Blog> data=FXCollections.observableArrayList();
            

  }

     @FXML
    private void modifier2(ActionEvent event) throws IOException { 
		 Blog current = list_b.getSelectionModel().getSelectedItem();
            Blog p = new Blog();
//            p.setId(Integer.parseInt(txt_id.getText()));
            p.setId_blog(current.getId_blog());
            p.settext_blog(text_blog.getText());
            p.setimage_blog(image_blog.getText());
            
            BlogDao pdao = BlogDao.getInstance();
            pdao.update(p);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Blog modifiée avec succés!");
        alert.show();
            text_blog.setText("");
        image_blog.setText("");	
	 
//reload
Parent root2 = FXMLLoader .load(getClass().getResource("/com/esprit/view/affiche.fxml"));
    Stage window = (Stage) btn_modif.getScene().getWindow();
    window.setScene(new Scene(root2));
		
		
	}
    @FXML
    private void uploadImage(ActionEvent event)throws FileNotFoundException, IOException  {
           Random rand = new Random();
        int x = rand.nextInt(1000);
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Upload File Path");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
        File file = fileChooser.showOpenDialog(null);
        String DBPath = "C:\\\\xampp\\\\htdocs\\\\imageP\\\\"  + x + ".jpg";
        if (file != null) {
            FileInputStream Fsource = new FileInputStream(file.getAbsolutePath());
            FileOutputStream Fdestination = new FileOutputStream(DBPath);
            BufferedInputStream bin = new BufferedInputStream(Fsource);
            BufferedOutputStream bou = new BufferedOutputStream(Fdestination);
            System.out.println(file.getAbsoluteFile());
            String path=file.getAbsolutePath();
            Image img = new Image(file.toURI().toString());
            imageview.setImage(img);
           /* File File1 = new File(DBPath);
            Image img = new Image(File1.getAbsolutePath());
            image_event.setImage(img);*/
            image_blog.setText(DBPath);
            int b = 0;
            while (b != -1) {
                b = bin.read();
                bou.write(b);
            }
            bin.close();
            bou.close();
            
        } else {
            System.out.println("error");

        }
    }  
}


