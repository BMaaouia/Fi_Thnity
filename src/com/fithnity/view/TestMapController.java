/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esprit.com.views;
//package com.fithnity.view;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker.State;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

/**
 * FXML Controller class
 *
 * @author Yassine
 */
public class TestMapController implements Initializable {

    public static double lon;
        public static double lat;

    @FXML
    private WebView webview;
    private WebEngine webengine;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        webengine = webview.getEngine();

        url = this.getClass().getResource("/com/fithnity/view/map/index_2.html");
        webengine.load(url.toString());
        
        try {
      // Replace YOUR_API_KEY with your actual API key from OpenCage
      String apiKey = "6550afa4c4e4494b94e7a58c52844fa1";
      
      // Replace YOUR_ADDRESS with the address you want to geocode
      String address = "YOUR_ADDRESS";
      
      // Encode the address as a URL parameter
      String encodedAddress = URLEncoder.encode(address, "UTF-8");
      
      // Construct the URL for the geocoding API request
      String url1 = "https://api.opencagedata.com/geocode/v1/json?key=" + apiKey + "&q=" + encodedAddress;

      // Send the HTTP request and get the response
      URL requestUrl = new URL(url1);
      BufferedReader reader = new BufferedReader(new InputStreamReader(requestUrl.openStream()));
      String response = reader.readLine();

      // Parse the JSON response to get the latitude and longitude
      // Note: you may want to use a JSON library like Gson to make this easier
      String lat = response.substring(response.indexOf("lat\":") + 5, response.indexOf(",\"lng"));
      String lng = response.substring(response.indexOf("lng\":") + 5, response.indexOf(",\"accuracy"));

      System.out.println("Latitude: " + lat);
      System.out.println("Longitude: " + lng);
    } catch (Exception e) {
      e.printStackTrace();
    }

       
      
}

    @FXML
    private void tt(ActionEvent event) {
      String latString = (String) webview.getEngine().executeScript("lat");
String lonString = (String) webview.getEngine().executeScript("lon");
           
lat = Double.parseDouble(latString);
lon = Double.parseDouble(lonString);


        System.out.println("Lat: " + lat);
                System.out.println("LOn " + lon);


    }

// JavaScript interface object
//private class JavaApp {
//  public void exit() {
//    Platform.exit();
//  }
//
//    }
}
