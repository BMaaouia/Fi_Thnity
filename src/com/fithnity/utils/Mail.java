/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fithnity.utils;

import java.net.PasswordAuthentication;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author aneex
 */
public class Mail {

    private String username = "saber.melki@esprit.tn";
    private String password = "221SMT0228";

//    public void envoyerMailReservation(String email, int reservationID, String dateDebut, String dateFin) {
//        Properties props = new Properties();
//        props.put("mail.smtp.auth", "true");
//        props.put("mail.smtp.starttls.enable", "true");
//        props.put("mail.smtp.host", "smtp.gmail.com");
//        props.put("mail.smtp.port", "587");
//        props.put("mail.smtp.ssl.trust", "*");
//
//        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
//            protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
//                return new javax.mail.PasswordAuthentication(username, password);
//            }
//        });
//        try {
//            Message message = new MimeMessage(session);
//            message.setFrom(new InternetAddress("saber.melki@esprit.tn"));
//            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
//            message.setSubject("Confirmation de réservation");
//            String body = "<h2>Votre réservation numéro "+ reservationID +" est confirmée</h2>"
//                    + "<h3>- Date début : "+dateDebut+"</h3> "
//                    + "<h3>- Date fin : "+dateFin+"</h3> ";
//            
//            message.setContent(body, "text/html");
//            Transport.send(message);
//            System.out.println("message envoyé");
//        } catch (MessagingException e) {
//            throw new RuntimeException(e);
//        }
//    }
    
     public void envoyerMailEmploye(String email, String nom, String prenom) {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.ssl.trust", "*");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                return new javax.mail.PasswordAuthentication(username, password);
            }
        });
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("saber.melki@esprit.tn"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
            message.setSubject("Confirmation d'ajout d'employé");
            String body = "<h2>Ajout avec succès !"
                    + "<h3>- Nom : "+nom+"</h3> "
                    + "<h3>- Prénom : "+prenom+"</h3> ";
            
            message.setContent(body, "text/html");
            Transport.send(message);
            System.out.println("message envoyé");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

}
