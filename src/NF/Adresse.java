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
public class Adresse {
    
    private String NomRue;
    private int codePostal;
    private String ville;
    
    public Adresse (String NomRue, int codePostal,String ville ){
        this.codePostal=codePostal;
        this.NomRue=NomRue;
        this.ville=ville;
        
    }
    
    public String toString(){
        return NomRue+" "+codePostal+" "+ville;
    }
    
}
