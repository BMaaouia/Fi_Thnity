/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fithnity.main;

import com.fithnity.entity.Employée;
import com.fithnity.service.ServiceEmployée;
import com.fithnity.utils.DataSource;

/**
 *
 * @author LENOVO
 */
public class employée {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
       DataSource c =DataSource.getInstance();
       
       //Employée p= new Employée(3,"saber","fff","hawarya","fsd");
      // produit p2= new produit(1,"saber",80,"lasvegas");

       ServiceEmployée SP= new ServiceEmployée();
      // SP.updateEmployée(p,3);
       
    }
    
}
