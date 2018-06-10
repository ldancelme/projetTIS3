package NF;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class Patient {
    private String nom;
    private String prenom;
    private Adresse adresse;
    private long numeroSS;
    private int age;
   
    
            
    public Patient(String nom, String prenom,Adresse adresse,long numeroSS, int age) {
        this.nom = nom;
        this.prenom = prenom;
        this.adresse=adresse;
        this.numeroSS=numeroSS;
        this.age=age; 
     
        }
  
    
   
    
    public String toString() {
        return getPrenom() + " " + getNom();
        }
    
    public boolean equals(Object o) {
        if (o instanceof Patient) {
            Patient p = (Patient)o;
      
       return nom.equals(p.nom)
               && prenom.equals(p.prenom)
               && adresse==p.adresse
               && numeroSS==p.numeroSS;
            }
        else
            return false;
        }    

    /**
     * @return the nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * @return the prenom
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * @return the NumeroSS
     */
    public long getNumeroSS() {
        return numeroSS;
    }

    /**
     * @return the Adresse
     */
    public Adresse getAdresse() {
        return adresse;
    }

    /**
     * @return the age
     */
    public int getAge() {
        return age;
    }
    public void allPatient(){
        
    }
    }

