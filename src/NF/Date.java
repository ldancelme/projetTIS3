package NF;

public class  Date implements Comparable {
    private int jour;
    private int mois;
    private int annee;
    
    public Date(int jour, int mois, int annee) {
        this.jour = jour;
        this.mois = mois;
        this.annee = annee;
        }
    
    public String toString() {
        return jour + "/" + mois + "/" + annee;
        }
    
    public boolean equals(Object o) {
        if (o instanceof Date) {
            Date d = (Date)o;
            return (annee == d.annee) && (mois == d.mois) && (jour == d.jour);
            }
        else
            return false;
        }
    
    // precondition : 'o' est une instance de 'Date' :
    public int compareTo(Object o) {
        Date d = (Date)o;
        if (annee != d.annee)
            return annee - d.annee;
        // ici on a forcement annee == d.annee :
        if (mois != d.mois)
            return mois  - d.mois;
        // ici on a forcement annee == d.annee et mois == d.mois :
        return jour - d.jour;
        }
       public Date  StringToDate (String s){
           int jour = Integer.parseInt(s.substring(0,s.indexOf("/")));
           int mois =  Integer.parseInt(s.substring(s.indexOf("/")+1,s.lastIndexOf("/")));
           int année =  Integer.parseInt(s.substring(s.lastIndexOf("/")+1,s.length()));
           Date d = new Date(jour, mois,année);
           return d;
           
           //                               int annee = Integer.parseInt(donneesCourantes.substring(0, donneesCourantes.indexOf('-')));
//                            int mois = Integer.parseInt(donneesCourantes.substring(donneesCourantes.indexOf('-') + 1, donneesCourantes.lastIndexOf('-')));
//                            int jour = Integer.parseInt(donneesCourantes.substring(donneesCourantes.lastIndexOf('-') + 1, donneesCourantes.length()));
//
//                            date = new Date(jour, mois, annee);
    }
       } 
       
