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
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
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
    private ListView<Blog> list_b;
    @FXML
    private ListView<Comment> list_c;
    @FXML
    private Label idblogselected;
    @FXML
    private javafx.scene.control.Button btn_dc;
    private javafx.scene.control.Button btn_up;
    @FXML
    private javafx.scene.control.Button btn_modif;
        @FXML
    private GridPane gridProduit;
    @FXML
    private ScrollPane scroll_blog;
    private Blog selectedBlog;
      /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
         
          try {
            List<Blog> blogs = bdao.displayAllList();
            int row = 0;
            int column = 0;
            for (int i = 0; i < blogs.size(); i++) {
                //chargement dynamique d'une interface
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/fithnity/view/ajout_c.fxml"));
                AnchorPane pane = new AnchorPane();
                
              
                Blog blog = blogs.get(i);
                String imagePath = blog.getimage_blog();
              
                FileInputStream input = new FileInputStream(imagePath);
                Image image = new Image(input);
                ImageView imageView = new ImageView(image);
                imageView.setFitHeight(200);
                imageView.setFitWidth(200);
                
                Text blogText = new Text(blog.gettext_blog());
                blogText.setFont(Font.font("Arial", FontWeight.BOLD, 14));
                blogText.setWrappingWidth(200);
                blogText.setTextAlignment(TextAlignment.CENTER);

                VBox vBox = new VBox(10);
                vBox.getChildren().addAll(imageView, blogText);
                vBox.setAlignment(Pos.CENTER);
    
                GridPane.setConstraints(vBox,column, row);
                gridProduit.getChildren().addAll(vBox);
             
                    pane.getChildren().addAll(gridProduit);
                    
                    vBox.setOnMouseClicked(event -> {
            // Set selected blog as a property of the VBox element
            selectedBlog = blog;
            // TODO: Add code to handle the selected blog
                });
                
                column++;
                if (column > 1) {
                    column = 0;
                    row++;
                }
            }
        } catch ( IOException ex) {
            System.out.println(ex.getMessage());
        }
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
        Comment c = new Comment(text, np,Integer.valueOf(selectedBlog.getId_blog()));
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
             list_c.setItems(bdaoC.displayById_Blog(selectedBlog.getId_blog()));
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

    private void Load_Comment(MouseEvent event) {
        
            //Blog selectedItem = list_b.getSelectionModel().getSelectedItem();
                     
            //idblogselected.setText(String.valueOf(selectedItem.getId_blog()));
            
            list_c.setItems(bdaoC.displayById_Blog(selectedBlog.getId_blog()));
            datac=FXCollections.observableArrayList();

    }

    @FXML
    private void PDFbtn(ActionEvent event) throws FileNotFoundException, SQLException, DocumentException {
        Pdf p = new Pdf();
        p.add("Blogs");
        
        
    }

    @FXML
    private void supprime(ActionEvent event) {
         Comment selectedItem = list_c.getSelectionModel().getSelectedItem();

                
                  if (list_c.getSelectionModel().getSelectedIndex() == -1) {
            Alert alert1 = new Alert(Alert.AlertType.WARNING);
            alert1.setTitle("No Selected Comment");
            alert1.setHeaderText("Error 403 !");
            alert1.setContentText("Select a comment to delete.");
            ButtonType buttonTypeYes = new ButtonType("OK");
            alert1.getButtonTypes().setAll(buttonTypeYes);
            alert1.showAndWait();
            System.out.println(selectedItem);
                 list_c.getItems().remove(selectedItem);
                 bdaoC.delete(selectedItem);
        } else {
            Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Are you sure to delete?");
            alert1.setHeaderText("Confirmation!");
            alert1.setContentText("You're attempting to delete a comment.");
            ButtonType buttonTypeYes = new ButtonType("OK");
            ButtonType buttonTypeNo = new ButtonType("Cancel");
            alert1.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);
            Optional<ButtonType> result = alert1.showAndWait();
            if (alert1.getResult().getText().equals("OK")) {
           
                System.out.println(selectedItem);
                 list_c.getItems().remove(selectedItem);
                 bdaoC.delete(selectedItem);
                
            }
           Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information ");
        
        alert.setContentText("Comment deleted successfully!");
        alert.showAndWait();
        getEvents();
    
  
    }
    }


    private void SelectComment(MouseEvent event) {
                 Comment current = list_c.getSelectionModel().getSelectedItem();
                 text_c.setText(current.gettext_comment());
                 
    }

    @FXML
    private void modif(ActionEvent event) throws IOException {
                 Comment current = list_c.getSelectionModel().getSelectedItem();
            Comment p = new Comment();
//            p.setId(Integer.parseInt(txt_id.getText()));
            p.setId_comment(current.getId_comment());
            p.settext_comment(text_c.getText());
            if (text_c.getText().isEmpty() ) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText("Champ(s) vide(s)");
        alert.setContentText("Veuillez remplir tous les champs obligatoires");
        alert.showAndWait();
    }else{
            
            CommentDao pdao = CommentDao.getInstance();
            pdao.update(p);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Comment modifiée avec succés!");
        alert.show();
        text_c.setText("");
        text_n.setText("");  
        //getEvents();
        list_c.setItems(bdaoC.displayById_Blog(selectedBlog.getId_blog()));
        datac=FXCollections.observableArrayList();

        
    }
    }

    @FXML
    private void load(MouseEvent event) {
        list_c.setItems(bdaoC.displayById_Blog(selectedBlog.getId_blog()));
            datac=FXCollections.observableArrayList();
    }

    

    
    
}
