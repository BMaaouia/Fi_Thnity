/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fithnity.utils;

import com.fithnity.entity.Employée;
import com.fithnity.service.ServiceEmployée;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


/**
 *
 * @author aneex
 */
public class Pdf {
    Connection cnx = DataSource.getInstance().getCnx();
ResultSet query_set;
PreparedStatement pst;
   

 public void GeneratePdf(String filename) throws FileNotFoundException, DocumentException, BadElementException, IOException, InterruptedException, SQLException
    {
        Document document= new  Document() {};
        PdfWriter.getInstance(document, new FileOutputStream(filename+".pdf"));
        document.open();
      
        ServiceEmployée m=new ServiceEmployée();
        List<Employée> list=m.getAllemployée();    
        document.add(new Paragraph("La liste des Employée :"));
        document.add(new Paragraph("     "));
         for(Employée u:list)
        {
            
        document.add(new Paragraph("First_name:"+u.getFirstname_employée()));
        document.add(new Paragraph("Last_name :"+u.getLastname_employée()));
         document.add(new Paragraph("Email :"+u.getEmail_employée()));
          document.add(new Paragraph("Address :"+u.getAddress_employée()));
               
        
        
        document.add(new Paragraph("---------------------------------------------------------------------------------------------------------------------------------- "));
        }
        document.close();
        Process pro=Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler "+filename+".pdf");
    }
    
    

//    public void pdfs() {
//
//    try {
//                Document my_pdf_report = new Document(PageSize.LETTER);
//
//                PdfWriter.getInstance(my_pdf_report, new FileOutputStream("listeEmployées.pdf"));
//                my_pdf_report.open();
//                PdfPTable my_report_table = new PdfPTable(4);
//                PdfPCell table_cell;
//                table_cell = new PdfPCell(new Phrase("Name"));
//                my_report_table.addCell(table_cell);
//                table_cell = new PdfPCell(new Phrase("Last Name"));
//                my_report_table.addCell(table_cell);
//                table_cell = new PdfPCell(new Phrase("Email"));
//                my_report_table.addCell(table_cell);
//                table_cell = new PdfPCell(new Phrase("Address"));
//                my_report_table.addCell(table_cell);
//            
//                try {
//                    String query = "SELECT * from employée";
//                    pst = cnx.prepareStatement(query);
//                    query_set = pst.executeQuery();
//                    while (query_set.next()) {
//                        
//                        String nom = query_set.getString("firstname_employée");
//                        table_cell = new PdfPCell(new Phrase(nom));
//                        my_report_table.addCell(table_cell);
//                                
//                         String pre = query_set.getString("lastname_employée");
//                        table_cell = new PdfPCell(new Phrase(pre));
//                        my_report_table.addCell(table_cell);
//                        
//                         String email = query_set.getString("email_employée");
//                        table_cell = new PdfPCell(new Phrase(email));
//                        my_report_table.addCell(table_cell);
//                        
//                        String ville = query_set.getString("address_employée");
//                        table_cell = new PdfPCell(new Phrase(ville));
//                        my_report_table.addCell(table_cell);
//                    }
//                    my_pdf_report.add(my_report_table);
//                    my_pdf_report.close();
//                } catch (SQLException ex) {
//                    System.out.println(ex.getMessage());
//                }
//            } catch (FileNotFoundException | DocumentException ex) {
//                System.out.println(ex.getMessage());
//            }}
//    
//    
}
