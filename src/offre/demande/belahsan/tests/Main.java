/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package offre.demande.belahsan.tests;

import offre.demande.belahsan.entities.Demande;
import offre.demande.belahsan.entities.Offre;
import offre.demande.belahsan.services.ServiceDemande;
import offre.demande.belahsan.utils.DataSource;

/**
 *
 * @author andol
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        DataSource DS=DataSource.getInstance();
        Offre o=new Offre(1,"andolsi","111111","andolsi@","1122121","1122121","1122121","1122121");
        
        //ServiceOffre Ds =new ServiceOffre();
       //SO.supprimer(2);
     //Ds.ajouter(o);
    }
    
}
