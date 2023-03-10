package com.fithnity.controller;

import com.jfoenix.controls.JFXListView;
import com.fithnity.entity.Demande;
import com.fithnity.utils.DataSource;
import com.fithnity.service.ServiceDemande;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.scene.Node;

public class commandsControllor implements Initializable {
    public ImageView imageView;
    public ImageView imageViewLM;
    public ImageView imageViewCV;
    public Button user_button;
    public Button buttonLM;
    public Button buttonCV;
    public JFXListView id_list_commande;
    public Button backing;
    public TextField txt_offre;
    @FXML
    public TextField txt_offer;
    String imagePath = null;
    String imagePathForCV=null;
    String imagePathForLM=null;

    private File file;
    private FileInputStream fin;
    public AnchorPane anchorpane;

    @FXML
    private TextField txt_fn;

    @FXML
    private TextField txt_ln;

    private TextField txt_mail;


    @FXML
    private Button button;


    @FXML
    private TableView<Demande> table_commands;

    private TableColumn<Demande, Integer> col_id;

    @FXML
    private TableColumn<Demande, String> cin;

    @FXML
    private TableColumn<Demande, String> lname;


    @FXML
    private TableColumn<Demande, String> ville;

    @FXML
    private TableColumn<Demande, String> codepostal;


    @FXML
    private TableColumn<Demande, String> face;




    @FXML
    private TextField filterField;


    private Image image;
    private Image imageCV;
    private Image imageLM;

    private Button toaddfile;

    private Button showRelatedFiles;

    ObservableList<Demande> listM;
    ObservableList<Demande> dataList;




    int index = -1;

    Connection conn =null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    @FXML
    private AnchorPane rootStatistics;
    @FXML
    private Button btn_acceuil;
    @FXML
    private Button btn_user;
    @FXML
    private Button btn_blog;
    @FXML
    private Button btn_reclamation;
    @FXML
    private Button btn_employe;
    @FXML
    private Button btn_reservation;

   
    private boolean validatecin(){
        Pattern p = Pattern.compile("[1-9]+[1-9]+[1-9]+[1-9]+[1-9]+[1-9]+[1-9]+[1-9]");
        Matcher m = p.matcher(txt_fn.getText());
        if(m.find() && m.group().equals(txt_fn.getText())){
            return true;
        }else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Validate your CIN number");
            alert.setHeaderText(null);
            alert.setContentText("Please Enter Valid CIN number");
            alert.showAndWait();

            return false;
        }
    }


    private boolean validateSkills(){
        Pattern p = Pattern.compile("[a-zA-Z\\s]+");
        Matcher m = p.matcher(txt_ln.getText());
        if(m.find() && m.group().equals(txt_ln.getText())){
            return true;
        }else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Validate your skills");
            alert.setHeaderText(null);
            alert.setContentText("Please Enter Valid skills");
            alert.showAndWait();

            return false;
        }
    }


    @FXML
    public void Add_demands() throws SQLException {

        if (validatecin()&& validateSkills()){

            if(txt_fn.getText().isEmpty() || txt_offer.getText().isEmpty()

                    || txt_ln.getText().isEmpty()

                    || imagePathForLM== null
                    || imagePathForCV== null
                    || imagePath== null){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Insert Failed, information missing");
                alert.show();
            }
            else{
                Demande t = new Demande(null,
                        txt_fn.getText(),
                        imagePathForCV,
                        imagePathForLM,
                        imagePath,
                        txt_ln.getText(),
                        txt_offer.getText());

                try{


                ServiceDemande st= new ServiceDemande();
                st.add(t);
               }catch (SQLException ex){
                   System.out.println("ici----"+ex);
               }
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Ajout succes");
                alert.show();
                UpdateTable();
                search_bycinorcompetences();
            }
            /**********************************added*************************************/

        }
    }


    //////// methode select users ///////
    @FXML
    void getSelected (MouseEvent event){
        index = table_commands.getSelectionModel().getSelectedIndex();
        if (index <= -1){

            return;
        }
        
        txt_fn.setText(cin.getCellData(index));
        txt_ln.setText(lname.getCellData(index));





        /***********original***********************/
        Image image = new Image("file:///"+ table_commands.getSelectionModel().getSelectedItem().getcartegrise());
        imageView.setImage(image);
        imagePath= table_commands.getSelectionModel().getSelectedItem().getcartegrise();

        /*********************************************/



        /**********************************/
        Image imageLM = new Image("file:///"+ table_commands.getSelectionModel().getSelectedItem().getlettreMotivation());
        imageViewLM.setImage(imageLM);
        imagePathForLM= table_commands.getSelectionModel().getSelectedItem().getlettreMotivation();

        /*********************************************/
        /**********************************/
        Image imageCV = new Image("file:///"+ table_commands.getSelectionModel().getSelectedItem().getcv());
        imageViewCV.setImage(imageCV);
        imagePathForCV= table_commands.getSelectionModel().getSelectedItem().getcv();

        /*********************************************/


    }

    public void Edit () throws SQLException {

        if(txt_fn.getText().isEmpty()
                || txt_ln.getText().isEmpty()
                ){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Update Failed, information missing");
            alert.show();
        }
        else{
            Demande t = new Demande(table_commands.getSelectionModel().getSelectedItem().getId(),
                txt_fn.getText(),
                txt_ln.getText(),
                imagePathForCV,
                imagePathForLM,
                imagePath);
            ServiceDemande st= new ServiceDemande();
            st.update(t);
            UpdateTable();
            search_bycinorcompetences();}
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Update succes");
        alert.show();

    }

    public void Delete() throws SQLException {

        ServiceDemande st= new ServiceDemande();
        st.delete((long) table_commands.getSelectionModel().getSelectedItem().getId());
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Delete Success");
        alert.show();
        UpdateTable();
        search_bycinorcompetences();
    }


    public void UpdateTable(){
       // col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        cin.setCellValueFactory(new PropertyValueFactory<>("cin"));
        ville.setCellValueFactory(new PropertyValueFactory<>("cv"));
        codepostal.setCellValueFactory(new PropertyValueFactory<>("lettreMotivation"));
        face.setCellValueFactory(new PropertyValueFactory<>("cartegrise"));
        lname.setCellValueFactory(new PropertyValueFactory<>("competences"));

        listM = DataSource.getDatausers();
        table_commands.setItems(listM);
    }




    @FXML
    void search_bycinorcompetences() {
        cin.setCellValueFactory(new PropertyValueFactory<Demande,String>("cin"));
        codepostal.setCellValueFactory(new PropertyValueFactory<Demande,String>("competences"));

        dataList = DataSource.getDatausers();
        table_commands.setItems(dataList);
        FilteredList<Demande> filteredData = new FilteredList<>(dataList, b -> true);
        filterField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(person -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (person.getcin().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                    return true; // Filter matches username
                } else if (person.getcompetences().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches password
                }

                else
                    return false; // Does not match.
            });
        });
        SortedList<Demande> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(table_commands.comparatorProperty());
        table_commands.setItems(sortedData);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {


        UpdateTable();
        search_bycinorcompetences();
        // Code Source in description
    }



    public void handleButtonAction(ActionEvent event) throws IOException {

        Stage stage = (Stage)anchorpane.getScene().getWindow();

        FileChooser fc = new FileChooser();
        FileChooser.ExtensionFilter ext1 = new FileChooser.ExtensionFilter("JPG files(*.jpg)","*.JPG");
        FileChooser.ExtensionFilter ext2 = new FileChooser.ExtensionFilter("PNG files(*.png)","*.PNG");
        FileChooser.ExtensionFilter ext3 = new FileChooser.ExtensionFilter("PDF files(*.pdf)","*.PDF");
        FileChooser.ExtensionFilter ext4 = new FileChooser.ExtensionFilter("TXT files(*.txt)","*.txt");
        fc.getExtensionFilters().addAll(ext1,ext2,ext3,ext4);
        File file = fc.showOpenDialog(stage);

        BufferedImage bf;
        try {
            bf = ImageIO.read(file);
            Image image = SwingFXUtils.toFXImage(bf, null);
            imageView.setImage(image);
            FileInputStream fin = new FileInputStream(file);
            int len = (int)file.length();

        } catch (IOException ex) {
            Logger.getLogger(commandsControllor.class.getName()).log(Level.SEVERE, null, ex);

        }


    }



    public void addAttachment(ActionEvent event ) throws Exception {

    }

    public void showHandleBtn(ActionEvent event) throws Exception {

    }


    @FXML
    String Filechooser() {

        FileChooser fc = new FileChooser();

        fc.setInitialDirectory(new File("C:\\Users\\Maaouia\\Desktop\\Fi thnity Integration\\Fi_Thnity\\src\\images"));
//        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.jpg"));
//        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.jpeg"));
//        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF Files", "*.pdf"));
//        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("TXT Files", "*.txt"));
//        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("PNG Files", "*.png"));

        File f = fc.showOpenDialog(null);
        if(f != null)
        {
            System.out.println(f);
        }
        imagePath=f.getPath();
        imagePath =imagePath.replace("\\","\\\\");
        return f.getName();
    }

    @FXML
    public String FilechooserLM(ActionEvent actionEvent) {
        FileChooser fc = new FileChooser();

        fc.setInitialDirectory(new File("C:\\Users\\Maaouia\\Desktop\\Fi thnity Integration\\Fi_Thnity\\src\\images"));
//        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.jpg"));
//        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.jpeg"));
//        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("pdf Files", "*.pdf"));

        File f = fc.showOpenDialog(null);
        if(f != null)
        {
            System.out.println(f);
        }
        imagePathForLM=f.getPath();
        imagePathForLM =imagePathForLM.replace("\\","\\\\");
        return f.getName();
    }

    @FXML
    public String FilechooserCV(ActionEvent actionEvent) {
        FileChooser fc = new FileChooser();

        fc.setInitialDirectory(new File("C:\\Users\\Maaouia\\Desktop\\Fi thnity Integration\\Fi_Thnity\\src\\images"));
//        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.jpg"));
//        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.jpeg"));
//        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("video Files", "*.mp4"));

        File f = fc.showOpenDialog(null);
        if(f != null)
        {
            System.out.println(f);
        }
        imagePathForCV=f.getPath();
        imagePathForCV =imagePathForCV.replace("\\","\\\\");
        return f.getName();
    }

    @FXML
    public void retour(ActionEvent event) throws Exception{
        Parent root  = FXMLLoader.load(getClass().getResource("/com/fithnity/view/Table_view.fxml"));

        Stage window =(Stage) backing.getScene().getWindow();
        window.setScene(new Scene(root, 1500, 1700));
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
    private void go_reclamationback(ActionEvent event) {
        try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/com/fithnity/view/reclamationback.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                    Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    @FXML
    private void go_blogback(ActionEvent event) {
        try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/com/fithnity/view/Ajout_Blog.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                    Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex);
            }
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
        try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/com/fithnity/view/ADDlivraison.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                    Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    @FXML
    private void go_acceuil(ActionEvent event) throws IOException {
          Parent root3 = FXMLLoader .load(getClass().getResource("/com/fithnity/view/Acceuil.fxml"));
    Stage window = (Stage) btn_acceuil.getScene().getWindow();
    window.setScene(new Scene(root3));
    }





}
