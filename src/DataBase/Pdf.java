/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataBase;

import Services.reservationService;
import com.itextpdf.text.Document;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import entity.reservation;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


/**
 *
 * @author oumayma
 */
public class Pdf {
        public void GeneratePdf(String filename) throws FileNotFoundException, DocumentException, BadElementException, IOException, InterruptedException, SQLException
    {
        Document document= new  Document() {};
        PdfWriter.getInstance(document, new FileOutputStream(filename+".pdf"));
        document.open();
      
        reservationService m=new reservationService();
        List<reservation> list=m.getAllReservation();    
        document.add(new Paragraph("La liste des Réservation :"));
        document.add(new Paragraph("     "));
         for(reservation u:list)
        {
            
        document.add(new Paragraph("prix:"+u.getPrix()));
        document.add(new Paragraph("poids :"+u.getPoids()));
               document.add(new Paragraph("DATE :"+u.getDateReser()));
        document.add(new Paragraph("Ville depart:"+u.getVilleDepart()));
        document.add(new Paragraph("ville arrivé :"+u.getVilleArrive()));
        
        
        document.add(new Paragraph("---------------------------------------------------------------------------------------------------------------------------------- "));
        }
        document.close();
        Process pro=Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler "+filename+".pdf");
    }
    
}
