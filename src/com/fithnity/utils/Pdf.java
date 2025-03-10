/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fithnity.utils;

import com.fithnity.service.BlogDao;
import com.fithnity.utils.ConnexionSingleton;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author user
 */
public class Pdf {
    private static BlogDao instance;
    private Statement st;
    private ResultSet rs;
    
    public Pdf()  {
        ConnexionSingleton cs=ConnexionSingleton.getInstance();
        try {
            st=cs.getCnx().createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(BlogDao.class.getName()).log(Level.SEVERE, null, ex);
        }
          
    
}
public void add(String file) throws FileNotFoundException, SQLException, DocumentException {
    Document my_pdf_report = new Document();
    PdfWriter.getInstance(my_pdf_report, new FileOutputStream(file));
    my_pdf_report.open();
    
   

// Add logo image
        Image logo;
        try {
            logo = Image.getInstance("C:/Users/user/Downloads/logo.png");
            logo.setAlignment(Image.ALIGN_RIGHT);
        logo.scaleToFit(100, 100);
        my_pdf_report.add(logo);
        } catch (BadElementException ex) {
            Logger.getLogger(Pdf.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Pdf.class.getName()).log(Level.SEVERE, null, ex);
        }
        

    // Add a title to the PDF
    Paragraph title = new Paragraph("Fi thnity", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18));
    title.setAlignment(Element.ALIGN_CENTER);
    my_pdf_report.add(title);

    // Add some spacing
    my_pdf_report.add(new Paragraph(" ", FontFactory.getFont(FontFactory.HELVETICA, 12)));

    // Create the table
    PdfPTable table = new PdfPTable(2);
    table.setWidthPercentage(100);
    table.setHeaderRows(1);

    // Define table header font
    Font headerFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12);

    // Add table headers
    PdfPCell cell1 = new PdfPCell(new Phrase("Titre", headerFont));
    cell1.setBackgroundColor(BaseColor.LIGHT_GRAY);
    table.addCell(cell1);

      PdfPCell cell2 = new PdfPCell(new Phrase("Text", headerFont));
     cell2.setBackgroundColor(BaseColor.LIGHT_GRAY);
     table.addCell(cell2);

    // Define table data font
    Font dataFont = FontFactory.getFont(FontFactory.HELVETICA, 10);

    // Add table data from database
    ResultSet res = st.executeQuery("select * from Blog");
    while (res.next()) {
        String title1 = res.getString(2);
        String title2 = res.getString(3);
        
       // String image = res.getString(3);

        PdfPCell cellTitre = new PdfPCell(new Phrase(title1, dataFont));
        PdfPCell cellText = new PdfPCell(new Phrase(title2, dataFont));
        table.addCell(cellTitre);
        table.addCell(cellText);
    }

    // Add the table to the PDF
    my_pdf_report.add(table);

    // Add a footer to the PDF
    Paragraph footer = new Paragraph("Generated by Fi thnity on " + new Date(), FontFactory.getFont(FontFactory.HELVETICA, 8, Font.ITALIC));
    footer.setAlignment(Element.ALIGN_CENTER);
    footer.setSpacingBefore(20);
    my_pdf_report.add(footer);

    // Close the PDF document
    my_pdf_report.close();
}
}
