/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fithnity.controller;
import javafx.scene.control.ListView;

import com.fithnity.service.BlogDao;
import com.fithnity.service.CommentDao;
import com.fithnity.entity.Blog;
import com.fithnity.entity.Comment;
import com.fithnity.entity.User;
import com.fithnity.service.UserManager;
import com.fithnity.utils.Pdf;
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
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
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
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import static jdk.nashorn.internal.objects.NativeString.search;
import org.controlsfx.control.Rating;

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
    private javafx.scene.control.Button btn_aj;
    private ListView<Blog> list_b;
    @FXML
    private ListView<Comment> list_c;
    @FXML
    private Label idblogselected;
    @FXML
    private javafx.scene.control.Button btn_dc;
    private javafx.scene.control.Button btn_up;
    private javafx.scene.control.Button btn_rate;
    @FXML
    private javafx.scene.control.Button btn_modif;
        @FXML
    private GridPane gridProduit;
    @FXML
    private ScrollPane scroll_blog;
    private Blog selectedBlog;
    @FXML
    private Rating rating;
    
    private MenuController menuController;
    @FXML
    private TextField search;
    @FXML
    private AnchorPane container;
    @FXML
    private javafx.scene.control.Button btn_acceuil;
    @FXML
    private javafx.scene.control.Button btn_user;
    @FXML
    private javafx.scene.control.Button btn_blog;
    @FXML
    private javafx.scene.control.Button pdfBtn;
    @FXML
    private javafx.scene.control.Button btn_rec;
    @FXML
    private javafx.scene.control.Button btn_em;
    @FXML
    private javafx.scene.control.Button btn_reser;
    User current =UserManager.getCurrentUser();
   
   
    
      /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                gridProduit.getChildren().clear();

            try {
                // TODO
                Affichage();
          
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Ajout_cController.class.getName()).log(Level.SEVERE, null, ex);
            }
       search.textProperty().addListener((observable, oldValue, newValue) -> {
     BlogDao bdao = BlogDao.getInstance();
    List<Blog> b = bdao.rechercher(newValue);
    ObservableList<Blog> BlogList = FXCollections.observableArrayList(b);
 
    ScrollPane scrollPane = new ScrollPane();
    
    scroll_blog.setContent((Node) BlogList);
    // Add the scroll pane to the parent container instead of the list view
    gridProduit.getChildren().add(scrollPane);
    
    
   
                });
      
  
     
       
    }    
     
    public void Affichage() throws FileNotFoundException
    {
        
            text_n.setText(current.getUser_firstname()+" "+current.getUser_lastname());
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
                
                Text blogTitre = new Text(blog.gettitre_blog());
                blogTitre.setFont(Font.font("Arial", FontWeight.BOLD, 14));
                blogTitre.setWrappingWidth(200);
                blogTitre.setTextAlignment(TextAlignment.CENTER);
                
                Text blogText = new Text(blog.gettext_blog());
                blogText.setFont(Font.font("Arial", FontWeight.BOLD, 14));
                blogText.setWrappingWidth(200);
                blogText.setTextAlignment(TextAlignment.CENTER);

                Rating Rt = new Rating(5);
                Rt.setRating(blog.getRating());
                
                VBox vBox = new VBox(10);
                vBox.getChildren().addAll(blogTitre,imageView, blogText,Rt);
                vBox.setAlignment(Pos.CENTER);
                
                gridProduit.setConstraints(vBox,column, row);
                gridProduit.getChildren().addAll(vBox);
                    
                    pane.getChildren().add(gridProduit);
                    
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
           try {
               Affichage();
           } catch (FileNotFoundException ex) {
               Logger.getLogger(Ajout_cController.class.getName()).log(Level.SEVERE, null, ex);
           }
    }
             list_c.setItems(bdaoC.displayById_Blog(selectedBlog.getId_blog()));
            datac=FXCollections.observableArrayList();
             try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/com/fithnity/view/ajout_c.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(Ajout_cController.class.getName()).log(Level.SEVERE, null, ex);
            }
            

    
 
    }
    private void refresh(ActionEvent event) {
         try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/com/fithnity/view/ajout_c.fxml"));
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
            Blog selectedItem = list_b.getSelectionModel().getSelectedItem();
            System.out.println(selectedItem);
            rating.setRating(selectedItem.getRating()); 
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
                        
            rating.setRating(selectedBlog.getRating()); 

    }




    @FXML
    private void rate(ActionEvent event) throws FileNotFoundException {
 
        System.out.println ("Rating given by use:" + rating.getRating());
         Float Somme =  ((float) rating.getRating());
        // Float Somme =  ((float) rating.getRating() + selectedBlog.getRating())/2;
       System.out.println ("final result " + Somme);
       
        selectedBlog.setRating(Somme);
        bdao.RateFN(selectedBlog);
            Rating Rt = new Rating(5);
            Rt.setRating(selectedBlog.getRating());
            
            refresh(event);
//            menuController.loadPage("/com/fithnity/view/ajout_c");
            
     
    }

   public void setMenuController(MenuController menuController) {
    this.menuController = menuController;
    }

//    private void goajouter(ActionEvent event) throws IOException {
//          
//       Parent root3 = FXMLLoader .load(getClass().getResource("/com/fithnity/view/Ajout_Blog.fxml"));
//    Stage window = (Stage) btn_aj.getScene().getWindow();
//    window.setScene(new Scene(root3));
//    }

    @FXML
    private void goo_user(ActionEvent event)  {
        try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/com/fithnity/view/Profile.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                    Logger.getLogger(Ajout_cController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    @FXML
    private void bara_rec(ActionEvent event)  {
        try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/com/fithnity/view/ajouterreclamationfront.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                    Logger.getLogger(Ajout_cController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    @FXML
    private void go__employe(ActionEvent event) {
    }

    @FXML
    private void go_reser(ActionEvent event) {
        try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/com/fithnity/view/ADDreservation.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                    Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    @FXML
    private void admin_blog(ActionEvent event) {
    }

    

  }