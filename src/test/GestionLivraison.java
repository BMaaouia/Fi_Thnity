/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import DataBase.My_DB;
import Services.produitService;
import entity.produit;

/**
 *
 * @author DELL
 */
public class GestionLivraison {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
      My_DB c =My_DB.getInstance();
       produit p= new produit(1,"saber",20,"hawarya");
       produit p2= new produit(1,"saber",80,"lasvegas");

       produitService SP= new produitService();
       SP.deleteProduit(3);
       
    }
    
}
