/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fithnity.utils;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 *
 * @author aneex
 */
public class Pdf {
    Connection cnx = DataSource.getInstance().getCnx();
ResultSet query_set;
PreparedStatement pst;


    public void pdfs() {

    try {
                Document my_pdf_report = new Document(PageSize.LETTER);

                PdfWriter.getInstance(my_pdf_report, new FileOutputStream("listeEmplyés.pdf"));
                my_pdf_report.open();
                PdfPTable my_report_table = new PdfPTable(4);
                PdfPCell table_cell;
                table_cell = new PdfPCell(new Phrase("Name"));
                my_report_table.addCell(table_cell);
                table_cell = new PdfPCell(new Phrase("Last Name"));
                my_report_table.addCell(table_cell);
                table_cell = new PdfPCell(new Phrase("Email"));
                my_report_table.addCell(table_cell);
                table_cell = new PdfPCell(new Phrase("Ville"));
                my_report_table.addCell(table_cell);
            
                try {
                    String query = "SELECT * from employée";
                    pst = cnx.prepareStatement(query);
                    query_set = pst.executeQuery();
                    while (query_set.next()) {
                        
                        String nom = query_set.getString("firstname_employée");
                        table_cell = new PdfPCell(new Phrase(nom));
                        my_report_table.addCell(table_cell);
                                
                         String pre = query_set.getString("lastname_employée");
                        table_cell = new PdfPCell(new Phrase(pre));
                        my_report_table.addCell(table_cell);
                        
                         String email = query_set.getString("email_employée");
                        table_cell = new PdfPCell(new Phrase(email));
                        my_report_table.addCell(table_cell);
                        
                        String ville = query_set.getString("address_employée");
                        table_cell = new PdfPCell(new Phrase(ville));
                        my_report_table.addCell(table_cell);
                    }
                    my_pdf_report.add(my_report_table);
                    my_pdf_report.close();
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            } catch (FileNotFoundException | DocumentException ex) {
                System.out.println(ex.getMessage());
            }}
    
    
}
