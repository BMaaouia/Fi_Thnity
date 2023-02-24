/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Services.reservationService;
import entity.reservation;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class ModifierReservationController implements Initializable {

    @FXML
    private TextField tf_prix;
    @FXML
    private TextField tf_poids;
    @FXML
    private TextField tf_v_d;
    @FXML
    private TextField tf_v_a;
    @FXML
    private TextField tf_DR;
    @FXML
    private Button btn_back;
    @FXML
    private Label lb_err_prix;
    @FXML
    private Label lb_err_poids;
    @FXML
    private Label lb_err_VD;
    @FXML
    private Label lb_err_VA;
    @FXML
    private Label lb_err_DR;
    @FXML
    private Label lb_err_add;
    @FXML
    private Label lb_success_add;
    @FXML
    private Label lb_err_id;
    @FXML
    private TextField tf_id;
    
    @FXML
    private ImageView iv_img;
    @FXML
    private Button btn_update_res;
    private final reservation reservationToUpda = new reservation();
    private ImageView iv_gallerie;
    private File selectedFile;
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btn_update_res.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                update_res();
            }
        });
        btn_back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    redirectToMyGallerie(event);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        });
        // TODO
    }  
    @FXML
    private void update_res() {
          if (tf_id.getText().toString().equals("") || tf_prix.getText().toString().equals("")||tf_poids.getText().toString().equals("")
                || tf_v_d.getText().toString().equals("") || tf_v_a.getText().toString().equals("") || tf_DR.getText().toString().equals("")) {

            lb_success_add.setVisible(false);

            lb_err_add.setVisible(true);
            
            if (tf_id.getText().toString().equals("")) {
                lb_err_id.setVisible(true);
            } else {
                lb_err_prix.setVisible(false);
            }
            if (tf_prix.getText().toString().equals("")) {
                lb_err_prix.setVisible(true);
            } else {
                lb_err_prix.setVisible(false);
            }
            if (tf_poids.getText().toString().equals("")) {
                lb_err_poids.setVisible(true);
            } else {
                lb_err_poids.setVisible(false);
            }
            if (tf_v_d.getText().toString().equals("")) {
                lb_err_VD.setVisible(true);
            } else {
                lb_err_VD.setVisible(false);
            }
            if (tf_v_a.getText().toString().equals("")) {
                lb_err_VA.setVisible(true);
            } else {
                lb_err_VA.setVisible(false);
            }
            if (tf_DR.getText().equals("")) {
                lb_err_DR.setVisible(true);
            } else {
                lb_err_DR.setVisible(false);
            }
        } else {

            lb_err_add.setVisible(false);
            lb_err_id.setVisible(false);
            lb_err_prix.setVisible(false);
            lb_err_poids.setVisible(false);
            lb_err_VD.setVisible(false);
            lb_err_VA.setVisible(false);
            lb_err_DR.setVisible(false);

            lb_success_add.setVisible(true);

            reservationService rs = new reservationService();
            reservation r = new reservation();
            reservationToUpda.setPrix(Integer.valueOf(tf_id.getText()));
            reservationToUpda.setPrix(Integer.valueOf(tf_prix.getText()));
            reservationToUpda.setPoids(Integer.valueOf(tf_poids.getText()));
            reservationToUpda.setVilleDepart(String.valueOf(tf_v_d.getText()));
            reservationToUpda.setVilleArrive(String.valueOf(tf_v_a.getText()));
            reservationToUpda.setDateReser(String.valueOf(tf_DR.getText()));


            rs.updateReservation(r, 0);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Your Reservation has been created successfully!");
            alert.show();
            
            try {
                copyImageToServer(selectedFile);
            } catch (IOException ex) {
                System.out.println(ex);            
            }
        }
            
       
    }
    public void redirectToMyGallerie(ActionEvent event) throws Exception {
        
        Parent page1 = FXMLLoader.load(getClass().getResource("/Interfaces/Gallerie_reservation.fxml"));
        Scene scene = new Scene(page1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }


   @FXML
    private void AddImage(ActionEvent event) {
         Stage s = new Stage();
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image files", "*.jpg", "*.png", "*.jpeg", "*.JPEG"));
        
        selectedFile = fc.showOpenDialog(s);

         
        String imgPath = selectedFile.getAbsoluteFile().getPath();

        reservationToUpda.setImage(selectedFile.getAbsoluteFile().getName());
        
      

        Image img = new Image(selectedFile.toURI().toString());

        iv_gallerie.setImage(img);
    }
      private void copyImageToServer(File selectedFile) throws IOException{
      
        String to = "C:\\xampp\\htdocs\\GestionLivraison\\" + selectedFile.getAbsoluteFile().getName(); 
        
        Path src = Paths.get(selectedFile.getAbsoluteFile().getPath());
        Path dest = Paths.get(to);
        Files.copy(src, dest,StandardCopyOption.REPLACE_EXISTING);
        
        System.out.println("src : " + src);
        System.out.println("dest : " + dest);
    }
    
    
}
