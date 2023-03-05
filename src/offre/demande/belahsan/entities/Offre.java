package offre.demande.belahsan.entities;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Objects;
import javafx.beans.property.SimpleObjectProperty;


public class Offre {
 private int offre_id;
 private String metier, secteur, ville, Nombredeposte,salaire;
// private LocalDate dateOffre;
 private SimpleObjectProperty <Date> dateOffre;
public Offre() {
    }

 
    public Offre(int id_offre, String metier, String secteur, String ville, String Nombredeposte,String salaire, Date dateOffre) {
        this.offre_id = id_offre;
        this.metier = metier;
        this.secteur = secteur;
        this.ville = ville;
        this.Nombredeposte = Nombredeposte;
        this.dateOffre = new SimpleObjectProperty <Date> (dateOffre);
        this.salaire = salaire;
    }

    public Offre(String metier, String secteur, String ville, String Nombredeposte,String salaire,Date dateOffre) {
        this.metier = metier;
        this.secteur = secteur;
        this.ville = ville;
        this.Nombredeposte = Nombredeposte;
        this.salaire = salaire;
         this.dateOffre = new SimpleObjectProperty <Date> (dateOffre);
    }
    
    public Offre(String metier, String secteur, String ville, String Nombredeposte,String salaire) {
        this.metier = metier;
        this.secteur = secteur;
        this.ville = ville;
        this.Nombredeposte = Nombredeposte;
        this.salaire = salaire;
    }

    public int getoffre_id() {
        return offre_id;
    }

    public void setId_offre(int id_offre) {
        this.offre_id = offre_id;
    }

    public String getMetier() {
        return metier;
    }

    public void setMetier(String metier) {
        this.metier = metier;
    }

    public String getSecteur() {
        return secteur;
    }

    public void setSecteur(String secteur) {
        this.secteur = secteur;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getNombredeposte() {
        return Nombredeposte;
    }

    public void setNombredeposte(String Nombredeposte) {
        this.Nombredeposte = Nombredeposte;
    }

  

//      public LocalDate getdateOffre() {
//        return dateOffre;
//    }
//
//    public void setdateOffre(LocalDate dateOffre) {
//        this.dateOffre = dateOffre;
//    }
    public void setDateOffre(Date dateOffre) {
       this.dateOffre =  new SimpleObjectProperty(dateOffre);
       
  }
    
    public Date getDateOffre() {
       return dateOffre.get();
   }
    
    public SimpleObjectProperty getDateProperty(){
        return dateOffre;
    }
    
    
    

    public String getSalaire() {
        return salaire;
    }

    public void setSalaire(String salaire) {
        this.salaire = salaire;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + this.offre_id;
        hash = 59 * hash + Objects.hashCode(this.metier);
        hash = 59 * hash + Objects.hashCode(this.secteur);
        hash = 59 * hash + Objects.hashCode(this.ville);
        hash = 59 * hash + Objects.hashCode(this.Nombredeposte);
       
        hash = 59 * hash + Objects.hashCode(this.dateOffre);
        hash = 59 * hash + Objects.hashCode(this.salaire);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Offre other = (Offre) obj;
        if (this.offre_id != other.offre_id) {
            return false;
        }
        if (!Objects.equals(this.metier, other.metier)) {
            return false;
        }
        if (!Objects.equals(this.secteur, other.secteur)) {
            return false;
        }
        if (!Objects.equals(this.ville, other.ville)) {
            return false;
        }
        if (!Objects.equals(this.Nombredeposte, other.Nombredeposte)) {
            return false;
        }
        
        if (!Objects.equals(this.dateOffre, other.dateOffre)) {
            return false;
        }
        if (!Objects.equals(this.salaire, other.salaire)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Offre{" + "offre_id=" + offre_id + ", metier=" + metier + ", secteur=" + secteur + ", ville=" + ville + ", Nombredeposte=" + Nombredeposte +   ", salaire=" + salaire + ", dateOffre=" + dateOffre +'}';
    }
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
}




   
