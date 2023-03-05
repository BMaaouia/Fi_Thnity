/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package offre.demande.belahsan.entities;

import java.util.Objects;

/**
 *
 * @author andol
 */
public class Demande {
 private int id;
 private String cin,cv, lettreMotivation , cartegrise,competences  ;

    public Demande() {
    }
    
     public Demande(int id,String cin, String cv, String lettreMotivation, String cartegrise,String competences ){
        this.id = id;
        this.cv = cv;
        this.lettreMotivation = lettreMotivation;
        this.cin = cin;
        this.cartegrise = cartegrise;
        this.competences = competences;
     }
    
        
  
     public Demande(String cin, String cv, String lettreMotivation, String cartegrise,String competences ){
        
        this.cv = cv;
        this.cin = cin;
        this.lettreMotivation = lettreMotivation;
        this.cartegrise = cartegrise;
        this.competences = competences;
     }
    
    
  
  

  

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
      public String getcin() {
        return cin;
    }

    public void setcin(String cin) {
        this.cin = cin;
    }

    public String getlettreMotivation() {
        return lettreMotivation;
    }

    public void setlettreMotivation(String lettreMotivation) {
        this.lettreMotivation = lettreMotivation;
    }
     public String getcv() {
        return cv;
    }

    public void setcv(String cv) {
        this.cv = cv;
    }
  
    public String getcartegrise() {
        return cartegrise;
    }

    public void setcartegrise(String cartegrise) {
        this.cartegrise = cartegrise;
    }
    
    public String getcompetences() {
        return competences;
    }

    public void setcompetences(String competences) {
        this.competences = competences;
    }

    @Override
    public String toString() {
        return "Demande{" + "id=" + id + ", cin=" + cin + ", cv=" + cv + ", lettreMotivation=" + lettreMotivation + ", cartegrise=" + cartegrise + ", competences=" + competences + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + this.id;
        hash = 37 * hash + Objects.hashCode(this.cin);
        hash = 37 * hash + Objects.hashCode(this.cv);
        hash = 37 * hash + Objects.hashCode(this.lettreMotivation);
        hash = 37 * hash + Objects.hashCode(this.cartegrise);
        hash = 37 * hash + Objects.hashCode(this.competences);
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
        final Demande other = (Demande) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.cin, other.cin)) {
            return false;
        }
        if (!Objects.equals(this.cv, other.cv)) {
            return false;
        }
        if (!Objects.equals(this.lettreMotivation, other.lettreMotivation)) {
            return false;
        }
        if (!Objects.equals(this.cartegrise, other.cartegrise)) {
            return false;
        }
        if (!Objects.equals(this.competences, other.competences)) {
            return false;
        }
        return true;
    }}

    

 
    
    
    
   

   
    

