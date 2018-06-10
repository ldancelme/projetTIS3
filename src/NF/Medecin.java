package NF;

import java.util.List;
import java.util.Vector;

public class Medecin {
    private String nom;
    private String prenom;
    private String specialite;
    private int NumTel;
    private String MDP;
    private Vector<Medecin> med;
    private String password;
    public Medecin(String nom, String prenom, String specialite,int NumTel) {
        this.nom = nom;
        this.prenom = prenom;
        this.specialite = specialite;
        this.NumTel=NumTel;
        this.MDP=MDP;
        }

    public Medecin(String nom, String prenom, String spe,String MDP) {
       this.nom=nom;
    this.prenom=prenom;
    this.specialite=spe;
    this.password=MDP;}
    
    public Medecin(){};
    public String getSpecialite() { return specialite; }
    
    public String toString() {
        return "Dr " + getPrenom() + " " + getNom() + ", " + specialite+"   "+NumTel;
        }
    
    public void afficherListeMedecins() {
        System.out.println("Liste medecin");
        System.out.println("-----------------------------");
         med = new Vector<Medecin>(); 
        for (int i = 0; i < med.size(); i++) {
            Medecin f = med.get(i);
            System.out.println(f.nom+""+f.prenom);
            // pour separer les fiches de soins :
            System.out.println("--------------------------------------");
        }
      
    }
    
    
    
    
    public boolean equals(Object o) {
        if (o instanceof Medecin) {
            Medecin p = (Medecin)o;
            return getNom().equals(p.getNom()) && getPrenom().equals(p.getPrenom());
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
    
 
   public String getPassword(){
       return password;
   } 
    }


