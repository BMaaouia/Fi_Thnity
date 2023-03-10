/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fithnity.service;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.fithnity.entity.Demande;
import com.fithnity.entity.Offre;
import com.fithnity.utils.DataSource;
import javafx.scene.control.Alert;


public class ServiceDemande implements IServiceDemande <Demande> {

    private static ServiceDemande instance;
    private Statement st;
    private ResultSet rs;
    private DataSource ds = DataSource.getInstance();
    public ServiceDemande() {
        DataSource cs=DataSource.getInstance();
        try {
            st=cs.getCnx().createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceDemande.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static ServiceDemande getInstance(){
        if(instance==null)

            instance =new ServiceDemande();

        return instance;
    }

    Connection cnx = DataSource.getInstance().getCnx();

    public void ajouter(Demande d){
        try{
            String requete = "INSERT INTO demande (cin,cv,lettreMotivation,cartegrise,competences) VALUES (?,?,?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(requete);

            pst.setString(1, d.getcin());
            pst.setString(2, d.getcv());
            pst.setString(3, d.getlettreMotivation());
            pst.setString(4, d.getcartegrise());
            pst.setString(5, d.getcompetences());
            pst.executeUpdate();
            System.out.println("Demande ajoutée !");
        }

        catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }
    public List<Demande> getAllDemande() {
        List<Demande> list = new ArrayList<>();

        try {
            String requete = "SELECT * FROM demande";
           // String requete = "select offre_id,metier,secteur,ville,cin from offre join demande on offre.id_demande=demande.id;";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new Demande(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)));
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return list;
    }

      /*public boolean update(Demande d) {
       // String qry = "UPDATE Reclamation SET nom = '"+p.getNom()+"', prenom = '"+p.getPrenom()+"', email = '"+p.getEmail()+"', numTel = '"+p.getNumTel()+"', message = '"+p.getMessage()+"' WHERE id = "+p.getId();
       //  String qry = "UPDATE Offre SET  metier = '"+p.getMetier()+"', secteur = '"+p.getSecteur()+"', ville = '"+p.getVille()+", Nombredeposte = '"+p.getNombredeposte()+"',salaire = '"+p.getSalaire()+"'' WHERE offre_id = "+p.getoffre_id();
      // String qry = "UPDATE Offre SET metier = '"+p.getMetier()+"', secteur = '"+p.getSecteur()+"', ville = '"+p.getVille()+"', Nombredeposte = '"+p.getNombredeposte()+"', salaire = '"+p.getSalaire()+"' WHERE offre_id = "+p.getoffre_id();
      String qry = "UPDATE demande SET cin = '"+d.getcin()+"', cv = '"+d.getcv()+"', lettreMotivation = '"+d.getlettreMotivation()+"', cartegrise = '"+d.getcartegrise()+"', competences = '"+d.getcompetences()+"' WHERE id = "+d.getId();
      try {
            if (st.executeUpdate(qry) > 0) {
                return true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(ServiceOffre.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }*/




    public Demande displayById(int Id) {
        String req="select * from demande where id ="+Id;
        Demande d=new Demande();
        try {
            rs=st.executeQuery(req);
            // while(rs.next()){
            rs.next();
            d.setcin(rs.getString(1));
            d.setcv(rs.getString("cv"));
            d.setlettreMotivation(rs.getString(3));
            d.setcartegrise(rs.getString(4));
            d.setcompetences(rs.getString(5));


        } catch (SQLException ex) {
            Logger.getLogger(ServiceDemande.class.getName()).log(Level.SEVERE, null, ex);
        }
        return d ;
    }




















    @Override
    public void add(Demande t) throws SQLException {

        try {
            Statement st = cnx.createStatement();
            String query = "insert into demande (id,cin,cv,lettreMotivation,cartegrise,competences,id_offre)values(NULL, '" +t.getcin()+ "', '" + t.getcv() +"','"+t.getlettreMotivation()+ "','" + t.getcartegrise() + "','" + t.getcompetences() + "','"+t.getId_offre()+"')";
            st.executeUpdate(query);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Added Successfully!");
            alert.show();
        }catch (SQLException ex){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("you've a constraint key!");
            alert.show();
        }

    }

    public List<Demande> read() throws SQLException {
        List<Demande> ls = new ArrayList<Demande>();
        Statement st = cnx.createStatement();
        String req = "select * from demande order by id";
        ResultSet rs = st.executeQuery(req);
        while (rs.next()) {
            Integer id = rs.getInt("id");
            String cin = rs.getString("cin");
            String cv = rs.getString("cv");
            String lettreMotivation = rs.getString("lettreMotivation");
            String cartegrise = rs.getString("cartegrise");
            String competences = rs.getString("competences");
            Demande p = new Demande(id,cin,cv,lettreMotivation,cartegrise,competences);
            ls.add(p);
        }

        return ls;
    }

    public void update(Demande d) throws SQLException {

        Statement st = cnx.createStatement();

        String query = "UPDATE demande SET cin = '"+d.getcin()+"', cv = '"+d.getcv()+"', lettreMotivation = '"+d.getlettreMotivation()+"', cartegrise = '"+d.getcartegrise()+"', competences = '"+d.getcompetences()+"' WHERE id = "+d.getId();

        /*String query = "UPDATE `userblog` SET `nom_c` = '" + t.getNom_c() + "',`prenom_c` = '" + t.getPrenom_c() + "', `email` = '" + t.getEmail() + "', `ville` = '" + t.getVille() + "', `code_postale` = '" + t.getCode_postale() + "', `adresse` = '"
                + t.getAdresse() + "', `telephone` = '" + t.getTelephone() + "' WHERE `userblog`.`id` = " + t.getIdResrv() + " ;";*/
        st.executeUpdate(query);

    }

    public void updates(Demande d) throws SQLException {

        Statement st = cnx.createStatement();

        String query = "UPDATE demande SET cin = '"+d.getcin()+"', competences = '"+d.getcompetences()+"' WHERE id = "+d.getId();

        /*String query = "UPDATE `userblog` SET `nom_c` = '" + t.getNom_c() + "',`prenom_c` = '" + t.getPrenom_c() + "', `email` = '" + t.getEmail() + "', `ville` = '" + t.getVille() + "', `code_postale` = '" + t.getCode_postale() + "', `adresse` = '"
                + t.getAdresse() + "', `telephone` = '" + t.getTelephone() + "' WHERE `userblog`.`id` = " + t.getIdResrv() + " ;";*/
        st.executeUpdate(query);

    }

    @Override
    public void delete(Long id) throws SQLException {

        Statement st = cnx.createStatement();
        String query = "DELETE FROM `demande` WHERE `demande`.`id` = '" + id + "'";
        st.executeUpdate(query);
    }
    //this
    public void deletes(Demande o) {
        //String req="delete from demande where id="+o.getId();
        String req= "DELETE FROM `demande` WHERE `demande`.`id` = '" + o.getId() + "'";
        Demande p=displayById(o.getId());

        if(p!=null)
            try {

                st.executeUpdate(req);

            } catch (SQLException ex) {
                Logger.getLogger(ServiceDemande.class.getName()).log(Level.SEVERE, null, ex);
            }else System.out.println("n'existe pas");
    }



}






























//    public void modifier(Demande d, int id){
//         try {
//            String requete = "UPDATE demande SET cin=?,cv=?,lettreMotivation=?,cartegrise=?,competences=? WHERE id=?";
//            PreparedStatement pst = cnx.prepareStatement(requete);
//            pst.setString(1, d.getcin());
//            pst.setString(2, d.getcv());
//            pst.setString(3, d.getlettreMotivation());
//            pst.setString(4, d.getcartegrise());
//            pst.setString(5, d.getcompetences());
//            pst.setInt(6, id);
//            
//            pst.executeUpdate();
//        } catch (SQLException ex) {
//            System.err.println(ex.getMessage());
//        }
//    }
//
//    public void supprimer(int id){
//          try {
//            String requete = "DELETE FROM demande WHERE id=?";
//            PreparedStatement pst = cnx.prepareStatement(requete);
//            pst.setInt(1, id);
//            pst.executeUpdate();
//        } catch (SQLException ex) {
//            System.err.println(ex.getMessage());
//        }
//    }

  
