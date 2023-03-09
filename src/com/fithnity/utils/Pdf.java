/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fithnity.utils;

import com.fithnity.service.ServiceOffre;
import com.itextpdf.text.Document;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.fithnity.entity.Offre;
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
      
        ServiceOffre m=new ServiceOffre();
        List<Offre> list=m.getAllOffre();    
        document.add(new Paragraph("La liste des Report :"));
        document.add(new Paragraph("     "));
         for(Offre u:list)
        {
            
        document.add(new Paragraph("prix:"+u.getMetier()));
        document.add(new Paragraph("poids :"+u.getNombredeposte()));
               document.add(new Paragraph("DATE :"+u.getSalaire()));
        document.add(new Paragraph("Ville depart:"+u.getVille()));
        document.add(new Paragraph("ville arrivé :"+u.toString()));
        
        
        document.add(new Paragraph("---------------------------------------------------------------------------------------------------------------------------------- "));
        }
        document.close();
        Process pro=Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler "+filename+".pdf");
    }
    
}
