
package com.fithnity.controller;

import com.fithnity.utils.DatabaseHelper;
import animatefx.animation.FadeIn;
import offre.demande.belahsan.animations.*;
import com.jfoenix.controls.JFXPopup;
import com.sun.javafx.scene.control.skin.DatePickerSkin;
import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.chart.PieChart;
import javafx.scene.control.DatePicker;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class StatisticsController implements Initializable {

    @FXML
    private AnchorPane rootStatistics;

    @FXML
    private AnchorPane rootDate;

    @FXML
    private PieChart pieChart;

    @FXML
    private Text title;

    @FXML
    private HBox hbox;

    @FXML
    private HBox hboxImage;

    @FXML
    private ImageView emptyImage;

    private final DatePicker datepicker = new DatePicker();

    private final DatePickerSkin datePickerSkin = new DatePickerSkin(datepicker);

    private final Node popupContent = datePickerSkin.getPopupContent();

    private final JFXPopup popup = new JFXPopup();

    private final ObservableList<PieChart.Data> data = FXCollections.observableArrayList();
    @FXML
    private Button btn_acceuil;
    @FXML
    private Button btn_user;
    @FXML
    private Button btn_blog;
    @FXML
    private Button vers_demande;
    @FXML
    private Button btn_reclamation;
    @FXML
    private Button btn_employe;
    @FXML
    private Button btn_reservation;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setGraphics();
        setDatePicker();
        setPopup();
        setNodeStartupConfiguration();
        setAnimations();
    }

    private void setNodeStartupConfiguration() {
        popupContent.getStyleClass().addAll("jfx-date-picker");
        hboxImage.setVisible(true);
        hbox.setVisible(false);
        pieChart.setLegendVisible(false);
    }

    private void setAnimations() {
        Animations.fadeInUp(hboxImage);
        Animations.fadeInUp(title);
    }

    private void setDatePicker() {
        popupContent.setVisible(false);
        rootDate.getChildren().add(popupContent);
    }

    private void setPopup() {
        popup.setPopupContent(rootDate);

        pieChart.setOnMouseClicked(ev -> {
            if (ev.getButton().equals(MouseButton.SECONDARY)) {
                popupContent.setVisible(true);
                popup.show(pieChart);
                popup.setAnchorX(ev.getScreenX());
                popup.setAnchorY(ev.getScreenY());
            }
        });

        hboxImage.setOnMouseClicked(ev -> {
            if (ev.getButton().equals(MouseButton.SECONDARY)) {
                popupContent.setVisible(true);
                popup.show(hboxImage);
                popup.setAnchorX(ev.getScreenX());
                popup.setAnchorY(ev.getScreenY());
            }
        });

        emptyImage.setOnMouseClicked(ev -> {
            if (ev.getButton().equals(MouseButton.SECONDARY)) {
                popupContent.setVisible(true);
                popup.show(emptyImage);
                popup.setAnchorX(ev.getScreenX());
                popup.setAnchorY(ev.getScreenY());
            }
        });

        rootStatistics.setOnMouseClicked(ev -> {
            if (ev.getButton().equals(MouseButton.SECONDARY)) {
                popupContent.setVisible(true);
                popup.show(rootStatistics);
                popup.setAnchorX(ev.getScreenX());
                popup.setAnchorY(ev.getScreenY());
            }
        });
    }
    
    private void setGraphics() {
        datepicker.setOnAction(ev -> {

            popup.hide();

            pieChart.getData().clear();

            java.sql.Date date = java.sql.Date.valueOf(datepicker.getValue());

            int count = DatabaseHelper.getDemands(date);
            int count3 = DatabaseHelper.getOffres(date);

            if (count == 0  && count3 == 0) {
                hboxImage.setVisible(true);
                hbox.setVisible(false);
                new FadeIn(hboxImage).play();
            } else {
                hboxImage.setVisible(false);
                hbox.setVisible(true);

                PieChart.Data one = new PieChart.Data("les demandes: " + count, count);
                data.add(one);
                pieChart.setData(data);
                Animations.hover(one.getNode(), 50, 1.1);



                PieChart.Data three = new PieChart.Data("les offres: " + count3, count3);
                data.add(three);
                pieChart.setData(data);
                Animations.hover(three.getNode(), 50, 1.1);
            }
            pieChart.setData(data);
        });
    }

    @FXML
    private void vers_demande(ActionEvent event) throws IOException {
        
                   Parent page1 = FXMLLoader.load(getClass().getResource("/com/fithnity/view/Table_view.fxml"));
        Scene scene = new Scene(page1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
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
