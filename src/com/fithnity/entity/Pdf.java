/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fithnity.entity;

import com.fithnity.dao.BlogDao;
import com.fithnity.utils.ConnexionSingleton;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
    public void add(String file) throws FileNotFoundException, SQLException, DocumentException{
        
                Document my_pdf_report = new Document();
                PdfWriter.getInstance(my_pdf_report, new FileOutputStream(file));
                my_pdf_report.open();            
                //we have four columns in our table
                PdfPTable my_report_table = new PdfPTable(2);
                //create a cell object
                PdfPCell table_cell;
                                
                table_cell=new PdfPCell(new Phrase("Text"));
                my_report_table.addCell(table_cell);
                table_cell=new PdfPCell(new Phrase("Image"));
                my_report_table.addCell(table_cell);

            ResultSet res = st.executeQuery("select * from Blog");
            while(res.next()){
                table_cell=new PdfPCell(new Phrase(res.getString(2)));
                my_report_table.addCell(table_cell);
                table_cell=new PdfPCell(new Phrase(res.getString(3)));
                my_report_table.addCell(table_cell);
            }

                               
                                
                /* Attach report table to PDF */
                my_pdf_report.add(my_report_table);                       
                my_pdf_report.close();
                
               /* Close all DB related objects */

        
    }
}
