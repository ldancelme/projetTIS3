/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NF;

/**
 *
 * @author begeym
 */
public class Secretaire {
    private String  nom;
    private String MotDePasse;
    private String prenom;
    /**
     * @return the nom
     */
    public Secretaire(String nom,String Prenom,String MotDePasse){
        this.nom=nom;
        this.MotDePasse=MotDePasse;
        this.prenom=prenom;
    }
    public String getNom() {
        return nom;
    }

    /**
     * @return the MotDePasse
     */
    public String getPassword() {
        return MotDePasse;
    }
    public String getPrenom(){
        return prenom;
    }
    
}
