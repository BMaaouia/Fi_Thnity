package offre.demande.belahsan.entities;

import java.util.Objects;


public class Offre {
 private int id_offre;
 private String metier, secteur, ville, Nombredeposte, voiture, Duree,salaire;
public Offre() {
    }

 
    public Offre(int id_offre, String metier, String secteur, String ville, String Nombredeposte, String voiture, String Duree, String salaire) {
        this.id_offre = id_offre;
        this.metier = metier;
        this.secteur = secteur;
        this.ville = ville;
        this.Nombredeposte = Nombredeposte;
        this.voiture = voiture;
        this.Duree = Duree;
        this.salaire = salaire;
    }

    public Offre(String metier, String secteur, String ville, String Nombredeposte, String voiture, String Duree, String salaire) {
        this.metier = metier;
        this.secteur = secteur;
        this.ville = ville;
        this.Nombredeposte = Nombredeposte;
        this.voiture = voiture;
        this.Duree = Duree;
        this.salaire = salaire;
    }

    public int getId_offre() {
        return id_offre;
    }

    public void setId_offre(int id_offre) {
        this.id_offre = id_offre;
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

    public String getVoiture() {
        return voiture;
    }

    public void setVoiture(String voiture) {
        this.voiture = voiture;
    }

    public String getDuree() {
        return Duree;
    }

    public void setDuree(String Duree) {
        this.Duree = Duree;
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
        hash = 59 * hash + this.id_offre;
        hash = 59 * hash + Objects.hashCode(this.metier);
        hash = 59 * hash + Objects.hashCode(this.secteur);
        hash = 59 * hash + Objects.hashCode(this.ville);
        hash = 59 * hash + Objects.hashCode(this.Nombredeposte);
        hash = 59 * hash + Objects.hashCode(this.voiture);
        hash = 59 * hash + Objects.hashCode(this.Duree);
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
        if (this.id_offre != other.id_offre) {
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
        if (!Objects.equals(this.voiture, other.voiture)) {
            return false;
        }
        if (!Objects.equals(this.Duree, other.Duree)) {
            return false;
        }
        if (!Objects.equals(this.salaire, other.salaire)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Offre{" + "id_offre=" + id_offre + ", metier=" + metier + ", secteur=" + secteur + ", ville=" + ville + ", Nombredeposte=" + Nombredeposte + ", voiture=" + voiture + ", Duree=" + Duree + ", salaire=" + salaire + '}';
    }
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
}




   
