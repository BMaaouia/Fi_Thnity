/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fithnity.utils;

import com.fithnity.entity.livraison;
import com.fithnity.service.produitService;
import com.fithnity.service.reservationService;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.fithnity.entity.produit;
import com.fithnity.entity.reservation;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
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
           
    // Create a table with one column and set the border
    PdfPTable table = new PdfPTable(1);
    table.getDefaultCell().setBorder(Rectangle.BOX);

    for(produit u : list) {
        // Add content to the table cell
        PdfPCell cell = new PdfPCell();
       cell.addElement(new Paragraph("Nom:"+u.getNom_produit()));
        cell.addElement(new Paragraph("poids :"+u.getPoids()));
       
        cell.addElement(new Paragraph("Description :"+u.getDescription()));
        // Add the table cell to the table
        table.addCell(cell);
    }

    // Add the table to the document
    document.add(table);
        document.close();
        Process pro=Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler "+filename+".pdf");
    }
    
}
