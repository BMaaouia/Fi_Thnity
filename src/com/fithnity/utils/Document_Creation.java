/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fithnity.utils;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import com.fithnity.entity.Reclamation;
import com.fithnity.controller.reclamationbackController;
import com.fithnity.service.ReclamationDao;
import java.awt.Color;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Set;
import javafx.collections.ObservableList;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
//import huntkingdom.services.ServiceArticle;


/**
 *
 * @author benra
 */
public class Document_Creation  {
    PDDocument doc = new PDDocument();
    
    public void generatePDF() throws IOException, SQLException{
        
         
       
           // huntkingdom.services.ServiceArticle ar=new ServiceArticle();
            ReclamationDao pdao = ReclamationDao.getInstance();
        ArrayList<Reclamation> articles = new ArrayList<>();
        
       // ArrayList<Article> list=articles.toArray();
         articles= (ArrayList<Reclamation>) pdao.displayAllList();
        
      //  Set<Consultation> list_cons =(Set<Consultation>) ms.getAllCons(id);
		 
       //     membres = ms.getAllUsers1(idd);
           
            
          //      System.out.println(membres);
                
        PDDocument doc = new PDDocument();
        PDPage page = new PDPage();
        doc.addPage(page);
        PDPageContentStream contentStream = new PDPageContentStream(doc, page);
                                
        
        
//	    
	      ///////////////////////////////////////////////////////////////////////////////////////////////
	      String operater_name="FI_THNITY";
	      PDFont font = PDType1Font.TIMES_ROMAN;
	      contentStream.beginText();
	      contentStream.setFont(font, 24);
	      contentStream.setLeading(14.5f);
	      contentStream.newLineAtOffset(220, 700);
	      contentStream.showText(operater_name);
	      contentStream.endText();
	      
	      String adress="Avenue Habib Bourguiba, Tunis 1000";
	      contentStream.beginText();
	      PDFont font3 = PDType1Font.COURIER_BOLD;
	      contentStream.setFont(font3, 12);
	      contentStream.newLineAtOffset(220, 670);
	      contentStream.showText(adress);
	      contentStream.endText();
	      
	      String operater_phone_number="50308022";
	      contentStream.beginText();
	      contentStream.setFont(font3, 12);
	      contentStream.newLineAtOffset(220, 650);
	      contentStream.showText(operater_phone_number);
	      contentStream.endText();
	      
	      String operater_email="www.FI_THNITY.tn";
	      contentStream.beginText();
	      contentStream.setFont(font3, 12);
	      contentStream.newLineAtOffset(220, 630);
	      contentStream.showText(operater_email);
	      contentStream.endText();
	      
			///////////////////////////////////////////////////////////////////////////////////////////////
			contentStream.beginText();
			contentStream.setNonStrokingColor(Color.black);
			PDFont font4 = PDType1Font.HELVETICA_BOLD_OBLIQUE;
			contentStream.setFont(font4, 24);
			contentStream.setLeading(14.5f);
			contentStream.newLineAtOffset(200, 520); //580
			contentStream.showText("Liste Reclamations");
			contentStream.endText();
	      
	      //////////////////////////////////Client Left //////////////////////////////////////////////////
                   //   contentStream.setNonStrokingColor(Color.LIGHT_GRAY);
                   //  contentStream.addRect(50, 420, 190, 80); //330
                     // contentStream.fill();
              //////////////////////////////////Client Right //////////////////////////////////////////////////
                 //   contentStream.setNonStrokingColor(Color.LIGHT_GRAY);
                   // contentStream.addRect(300, 420, 250, 80); //330
                   // contentStream.fill();
			
              ////////////// IMAGE BOX //////////////////
              
                  contentStream.addLine(50, 730, 560, 730);
		    contentStream.fill();
		    contentStream.addLine(50, 610, 50, 730);
		    contentStream.fill();
		   contentStream.addLine(50, 610, 560, 610);
		    contentStream.fill();
		   contentStream.addLine(560, 610, 560, 730);
		    contentStream.fill();
                         
              ///////////////////////////////////
                    
                   contentStream.addLine(30, 30, 580,30);
		    contentStream.fill();
		    contentStream.addLine(30, 30, 30,750);
		    contentStream.fill();
		    contentStream.addLine(30, 750, 580, 750);
		    contentStream.fill();
		   contentStream.addLine(580, 750, 580, 30);
                    
		    contentStream.fill();
                        
              drawTable(page, contentStream, 400, 50, articles);
             
              
                
                contentStream.setNonStrokingColor(Color.RED);
                String Contactus="Contact Us :";
                PDFont font5 = PDType1Font.COURIER_BOLD;
                contentStream.beginText();
                contentStream.setFont(font3, 12);
                contentStream.setLeading(14.5f);
                contentStream.newLineAtOffset(52, 130);
                contentStream.showText(Contactus);
                contentStream.endText();
                
                
                contentStream.setFont(PDType1Font.COURIER_BOLD,8);
		contentStream.setNonStrokingColor(Color.black);
                String Parg="If there are any questions regarding this privacy & security policy you may contact us using the information below :";    
                contentStream.beginText();
               contentStream.setFont(font3, 7);
              contentStream.setLeading(14.5f);
                contentStream.newLineAtOffset(52, 110);
                contentStream.showText(Parg);
                contentStream.endText();
                
                String Parg2="www.FI_THNITY.tn";    
                contentStream.beginText();
               contentStream.setFont(font3, 8);
              contentStream.setLeading(14.5f);
                contentStream.newLineAtOffset(52, 95);
                contentStream.showText(Parg2);
                contentStream.endText();
                
                String Parg3="Avenue Habib Bourguiba, Tunis 1000";    
                contentStream.beginText();
               contentStream.setFont(font3, 8);
               contentStream.setLeading(14.5f);
                contentStream.newLineAtOffset(52, 80);
                contentStream.showText(Parg3);
                contentStream.endText();
                
                String Parg4="E-Mail ID:FI_THNITY@gmail.com";    
                contentStream.beginText();
              contentStream.setFont(font3, 9);
               contentStream.setLeading(14.5f);
                contentStream.newLineAtOffset(52, 65);
                contentStream.showText(Parg4);
                contentStream.endText();
                
                
                contentStream.close();
              
              System.out.println(articles);
              
          doc.save("my_doc.pdf");
          doc.close();
          System.out.println("pdf saved");


    }
    
    public static void drawTable(PDPage page, PDPageContentStream contentStream,float y, float margin, ArrayList <Reclamation> articles) 
			throws IOException {
		
		final int rows = articles.size()+1;
		final int cols = 6;
		final float rowHeight = 20f;
		final float tableWidth = 517;
		final float tableHeight = rowHeight * rows;
		final float colWidth = tableWidth/(float)cols;
		final float cellMargin=5f;
                
                
//                PDFont font3 = PDType1Font.COURIER_BOLD;
//                contentStream.setNonStrokingColor(Color.black);
//                String client="Reclamations:";
//                PDFont font5 = PDType1Font.TIMES_BOLD_ITALIC;
//                contentStream.beginText();
//                contentStream.setFont(font3, 15);
//                contentStream.setLeading(14.5f);
//                contentStream.newLineAtOffset(52, 375);
//                contentStream.showText(client);
//                contentStream.endText();
                
		//draw the rows
		float nexty = y ;
		for (int i = 0; i <= rows; i++) {
                
		contentStream.drawLine(margin,nexty,margin+tableWidth,nexty);
		nexty-= rowHeight;
		}
		
		//draw the columns
		float nextx = margin;
		for (int i = 0; i <= cols; i++) {
		contentStream.drawLine(nextx,y,nextx,y-tableHeight);
		nextx += colWidth;
		}
		
		//now add the text
		contentStream.setFont(PDType1Font.HELVETICA_BOLD,11);
		
		float textx = margin+cellMargin;
		float texty = y-15;
		
		String[] list = {"nom","Prenom", "email","tel", "message","Date"};
		
		contentStream.setNonStrokingColor(Color.RED);
		for(int i=0; i<list.length; i++){
			contentStream.beginText();
			contentStream.moveTextPositionByAmount(textx,texty);
			contentStream.drawString(list[i]);
			contentStream.endText();
			textx += colWidth;
		}
		texty-=rowHeight;
		textx = margin+cellMargin;
                contentStream.setFont(PDType1Font.HELVETICA_BOLD,8);
		contentStream.setNonStrokingColor(Color.black);
		for(Reclamation cons: articles) {
			
				
				String text;
//				if(cons.getTitre().length()>14)
				//	text = cons.getTitre().substring(0, 13)+"..";
                                text = cons.getNom();
//				else text=cons.getDescription();
						
				contentStream.beginText();
				contentStream.moveTextPositionByAmount(textx,texty);
				contentStream.drawString(text);
				contentStream.endText();
				textx += colWidth;
				//////////////////////
				String text2 = ""+cons.getPrenom();
				contentStream.beginText();
				contentStream.moveTextPositionByAmount(textx,texty);
				contentStream.drawString(text2);
				contentStream.endText();
				textx += colWidth;
				//////////////////////
				String text3 = ""+cons.getEmail();
				contentStream.beginText();
				contentStream.moveTextPositionByAmount(textx,texty);
				contentStream.drawString(text3);
				contentStream.endText();
				textx += colWidth;
				//////////////////////
				String text4 = ""+cons.getNumTel();
				contentStream.beginText();
				contentStream.moveTextPositionByAmount(textx,texty);
				contentStream.drawString(text4);
				contentStream.endText();
				textx += colWidth;
				//////////////////////
				String text5 = ""+cons.getMessage();
				contentStream.beginText();
				contentStream.moveTextPositionByAmount(textx,texty);
				contentStream.drawString(text5);
				contentStream.endText();
				textx += colWidth;
				//////////////////////
                                String text6 = ""+cons.getDate();
				contentStream.beginText();
				contentStream.moveTextPositionByAmount(textx,texty);
				contentStream.drawString(text6);
				contentStream.endText();
				textx += colWidth;
				//////////////////////
//                                String text7 = ""+cons.getNumtel();
//				contentStream.beginText();
//				contentStream.moveTextPositionByAmount(textx,texty);
//				contentStream.drawString(text7);
//				contentStream.endText();
//				textx += colWidth;
				//////////////////////

			
			texty-=rowHeight;
			textx = margin+cellMargin;
		}
		

	
	}
	
//  
	
    
}
