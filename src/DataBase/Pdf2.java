/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataBase;

import Services.produitService;
import Services.reservationService;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import entity.produit;
import entity.reservation;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author DELL
 */
public class Pdf2 {public void GeneratePdf(String filename) throws FileNotFoundException, DocumentException, BadElementException, IOException, InterruptedException, SQLException
    {
        Document document= new  Document() {};
        PdfWriter.getInstance(document, new FileOutputStream(filename+".pdf"));
        document.open();
      
        produitService m=new produitService();
        List<produit> list=m.getAllProduit();    
        document.add(new Paragraph("La liste des Produits :"));
        document.add(new Paragraph("     "));
         for(produit u:list)
        {
       document.add(new Paragraph("---------------------------------------------------------------------------------------------------------------------------------- "));
            
        document.add(new Paragraph("Nom:"+u.getNom_produit()));
        document.add(new Paragraph("poids :"+u.getPoids()));
       
        document.add(new Paragraph("Description :"+u.getDescription()));
        
        
        document.add(new Paragraph("---------------------------------------------------------------------------------------------------------------------------------- "));
        }
        document.close();
        Process pro=Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler "+filename+".pdf");
    }
    
}
