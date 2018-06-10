package NF;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class DossierMedical {

    List<FicheDeSoins> fiches;     // contient des objets de classe 'FicheDeSoins'
    ArrayList<Medecin> medecins;
    Vector<Patient> patients;
    Vector<Secretaire> secretaire;
    public DossierMedical() {
        fiches = new Vector<FicheDeSoins>();  // liste vide
        patients=new Vector<Patient>();
        secretaire=new Vector<Secretaire>();
    }
public ArrayList<Medecin> getMedecins() {
        return medecins;
    }

    /**
     * Permet de retourner une liste de patients
     * @return liste de patients (Vector)
     */
    public Vector<Patient> getPatients() {
        return patients;
    }
    public void ajouterFiche(FicheDeSoins fiche) {
        fiches.add(fiche);
    }
    public void ajouterPatient(Patient p) {
        patients.add(p);
    }
    public void ajouterSecretaire(Secretaire s){
        secretaire.add(s);
    }

    public int nombreActes(Medecin m) {

        int acte = 0;
        for (int i = 0; i < fiches.size(); i++) {

            if (m.equals(fiches.get(i).getMedecin())) {

                acte += fiches.get(i).getActes().size();
            }

        }

        return acte;
    }

    public Vector<FicheDeSoins> getFDS(Medecin m) {
        Vector<FicheDeSoins> fiche = new Vector<>();
        for (int i = 0; i < fiches.size(); i++) {
            FicheDeSoins f = fiches.get(i);
            if (m.equals(fiches.get(i).getMedecin())) {
                fiche.add(f);
            }
        }
        return fiche;
    }

    public Vector<FicheDeSoins> getFDS(Patient p) {
        Vector<FicheDeSoins> fiche = new Vector<>();
        Vector<Patient> p1=new Vector<>();
        
        for (int i = 0; i < fiches.size(); i++) {
            FicheDeSoins f=fiches.get(i);
            if(p.getNumeroSS()==fiches.get(i).getPatient().getNumeroSS())
                
                       
                fiche.add(f);
            
        }
            
 return fiche;
        }
        
    

    public void afficher() {
        System.out.println("Dossier medical informatise :");
        System.out.println("-----------------------------");
        for (int i = 0; i < fiches.size(); i++) {
            FicheDeSoins f = fiches.get(i);
            f.afficher();
            // pour separer les fiches de soins :
            System.out.println("--------------------------------------");
        }
    }

    public double coutPatient(Patient p) {
        double cout = 0;
        for (int i = 0; i < fiches.size(); i++) {
            FicheDeSoins f = fiches.get(i);
            if (p.getNumeroSS()==f.getPatient().getNumeroSS()) {
                cout += f.coutTotal();
            }
        }
        return cout;
    }

    public double coutMedecin(Medecin m) {
        double cout = 0;
        for (int i = 0; i < fiches.size(); i++) {
            FicheDeSoins f = fiches.get(i);
            if (m.equals(f.getMedecin())) {
                cout += f.coutTotal();
            }
        }
        return cout;
    }

    public double coutSpecialite(String specialite) {
        double cout = 0;
        for (int i = 0; i < fiches.size(); i++) {
            FicheDeSoins f = fiches.get(i);
            if (specialite.equals(f.getMedecin().getSpecialite())) {
                cout += f.coutTotal();
            }
        }
        return cout;
    }

    public void afficherListePatients(Medecin m) {
        System.out.println("> liste des patients du " + m.toString() + " :");
        Vector<Patient> liste = new Vector<Patient>();
        // 'liste' contient tous les patients deja affiches
        // --> ceci permet de ne pas reafficher un patient deja affiche
        for (int i = 0; i < fiches.size(); i++) {
            FicheDeSoins f = fiches.get(i);
            if (m.equals(f.getMedecin())) {
                Patient p = f.getPatient();
                if (!liste.contains(p)) {
                    System.out.println(" - " + p.toString());
                    liste.add(p);
                }
            }
        }
    }

    public void afficherMedecin(Patient p) {
        System.out.println("> liste des médecins du " + p.toString() + " :");
        Vector<Medecin> liste = new Vector<Medecin>();
        for (int i = 0; i < fiches.size(); i++) {
            FicheDeSoins f = fiches.get(i);
            if (p.equals(f.getPatient())) {
                Medecin m = f.getMedecin();
                if (!liste.contains(m)) {
                    System.out.println(" - " + m.toString());
                    liste.add(m);
                }
            }
        }
    }

    public void afficherToutLesMedecins() {
        System.out.println("> liste des médecins :");

        Vector<Medecin> liste = new Vector<Medecin>();
        for (int i = 0; i < fiches.size(); i++) {
            FicheDeSoins f = fiches.get(i);
            Medecin m = f.getMedecin();
            if (!liste.contains(m)) {
                System.out.println(m.getNom() + " " + m.getPrenom());
                liste.add(m);

            }
        }

    }

    public int nombreFichesIntervalle(Date d1, Date d2) {
        int n = 0;
        for (int i = 0; i < fiches.size(); i++) {
            FicheDeSoins f = fiches.get(i);
            Date d = f.getDate();
            if (d.compareTo(d1) >= 0 && d.compareTo(d2) <= 0) {
                n++;
            }
        }
        return n;
    }

    public ArrayList<FicheDeSoins> listeFicheEntreDeuxDate(Date d1, Date d2) {
        ArrayList<FicheDeSoins> l1;
        l1 = new ArrayList<FicheDeSoins>();
        for (int i = 0; i < fiches.size(); i++) {
            FicheDeSoins f = fiches.get(i);
            Date d = f.getDate();

            if (d.compareTo(d1) >= 0 && d.compareTo(d2) <= 0) {
                l1.add(f);
            }

        }
        return l1;
    }

    public void trierDates() {
        Vector<FicheDeSoins> copieFiches = new Vector<FicheDeSoins>(fiches);

        while (!copieFiches.isEmpty()) {
            // on cherche la fiche de soins de date minimale :
            int imin = 0;
            FicheDeSoins f1 = copieFiches.get(imin);
            for (int i = 1; i < copieFiches.size(); i++) {
                FicheDeSoins f2 = copieFiches.get(i);
                if (f2.getDate().compareTo(f1.getDate()) < 0) {
                    imin = i;
                    f1 = f2;
                }
            }
            // on affiche la fiche de soins trouvee :
            f1.afficher();
            System.out.println("------------------------");
            //on la supprime de la liste :
            copieFiches.remove(imin);
        }
    }
    public Vector<FicheDeSoins> trierDates(Patient p) {
        Vector<FicheDeSoins> copieFiches = getFDS(p);
        Vector<FicheDeSoins> wesh=new Vector<FicheDeSoins>();
        

        while (!copieFiches.isEmpty()) {
            // on cherche la fiche de soins de date minimale :
            int imin = 0;
            FicheDeSoins f1 = copieFiches.get(imin);
            for (int i = 1; i < copieFiches.size(); i++) {
                FicheDeSoins f2 = copieFiches.get(i);
                if (f2.getDate().compareTo(f1.getDate()) < 0) {
                    imin = i;
                    f1 = f2;
                }
            }
            // on affiche la fiche de soins trouvee :
            wesh.add(f1);
           
            //on la supprime de la liste :
            copieFiches.remove(imin);
        }
        
       return wesh;
    }
    public Vector<FicheDeSoins> trierDates(Medecin m) {
        Vector<FicheDeSoins> copieFiches = getFDS(m);
        Vector<FicheDeSoins> wesh=new Vector<FicheDeSoins>();
        

        while (!copieFiches.isEmpty()) {
            // on cherche la fiche de soins de date minimale :
            int imin = 0;
            FicheDeSoins f1 = copieFiches.get(imin);
            for (int i = 1; i < copieFiches.size(); i++) {
                FicheDeSoins f2 = copieFiches.get(i);
                if (f2.getDate().compareTo(f1.getDate()) < 0) {
                    imin = i;
                    f1 = f2;
                    
                }
            }
            // on affiche la fiche de soins trouvee :
            wesh.add(f1);
           f1.afficher();
            //on la supprime de la liste :
            copieFiches.remove(imin);
        }
        
       return wesh;
    }

    // tri generique :
    public void trier(ComparaisonFiches c) {
        Vector<FicheDeSoins> copieFiches = new Vector<FicheDeSoins>(fiches);

        while (!copieFiches.isEmpty()) {
            // on cherche la fiche de soins minimale :
            int imin = 0;
            FicheDeSoins f1 = copieFiches.get(imin);
            for (int i = 1; i < copieFiches.size(); i++) {
                FicheDeSoins f2 = copieFiches.get(i);
                if (c.comparer(f2, f1) < 0) {
                    imin = i;
                    f1 = f2;
                }
            }
            // on affiche la fiche de soins trouvee :
            f1.afficher();
            System.out.println("------------------------");
            //on la supprime de la liste :
            copieFiches.remove(imin);
        }
    }

    public FicheDeSoins[] FicheTrierParCout() {
        FicheDeSoins tab[] = new FicheDeSoins[fiches.size()];
        for (int k = 01; k < fiches.size(); k++) {
            tab[k] = fiches.get(k);
        }
        for (int i = 0; i < fiches.size(); i++) {
            FicheDeSoins f1 = fiches.get(i);
            double c1 = f1.coutTotal();
            for (int j = 0; j < fiches.size(); j++) {
                FicheDeSoins f2 = fiches.get(j);
                double c2 = f2.coutTotal();
                if (c1 > c2) {
                    tab[i] = fiches.get(j);
                    tab[j] = fiches.get(i);

                }

            }

        }
        return tab;

    }

    public FicheDeSoins[] FicheSansTrie() {
        FicheDeSoins tab[] = new FicheDeSoins[fiches.size()];
        for (int k = 01; k < fiches.size(); k++) {
            tab[k] = fiches.get(k);
        }
        return tab;
    }
    
     public boolean isInAllPatient(Vector<Patient> p, Patient p1) {
        for (int i = 0; i < p.size(); i++) {
            if (p.get(i).getNumeroSS()==p1.getNumeroSS()) {
               p1=p.get(i);
                return true;
            }

        }
        return false;

    }

    public Vector<Patient> allPatient() {
        Vector<Patient> p = new Vector<>();
        for (int i = 0; i < fiches.size(); i++) {
            FicheDeSoins f = fiches.get(i);
            Patient p1 = f.getPatient();
            if (!isInAllPatient(p,p1)) {
                      p.add(p1);

            }
        }
        return p;
    }

    public Patient getPatient(String nom, String prenom) {
        Vector<Patient> pat = new Vector<Patient>();
        int i = 0;
        int j = 0;
        Patient b = null;
        for (i = 0; i < fiches.size(); i++) {
            FicheDeSoins f = fiches.get(i);
            Patient p = f.getPatient();
            if (!pat.contains(p)) {
                pat.add(p);            
            }
        }
        for(j=0; j<pat.size();j++){
        if (pat.get(j).getNom().equals(nom) && pat.get(j).getPrenom().equals(prenom)) {

            b = pat.get(j);
        }
        
    }return b;}

    public Medecin getMedecin(String nom) {
        Vector<Medecin> med = new Vector<Medecin>();
        int i = 0;
        int j = 0;
        Medecin b = null;
        for (i = 0; i < fiches.size(); i++) {
            FicheDeSoins f = fiches.get(i);
            Medecin m = f.getMedecin();
            if (!med.contains(m)) {
                med.add(m);
            }
        }
        for (j = 0; j < med.size(); j++) {
            if (med.get(j).getNom().equals(nom)) {
                b = med.get(j);
            }
        }
        return b;
    }

    public boolean verifiePatient(String nom, String prenom) {
        boolean login = false;
        int i = 0;
        int j = 0;
        Vector<Patient> pat = new Vector<Patient>();
        for (i = 0; i < fiches.size(); i++) {
            FicheDeSoins f = fiches.get(i);
            Patient p = f.getPatient();
            if (!pat.contains(p)) {
                pat.add(p);
            }

        }

        while (login == false && j < pat.size()) {
            if (pat.get(j).getNom().equals(nom) && pat.get(j).getPrenom().equals(prenom)) {

                login = true;
            } else {

                login = false;
                j++;
            }
        }

        return login;

    }

    public boolean verifieLoginAdmin(String user, String password) {
        boolean login = false;
        int i = 0;
        int j = 0;
        Vector<Secretaire> sec = new Vector<Secretaire>();
        for (i = 0; i < secretaire.size(); i++) {
            Secretaire s = secretaire.get(i);
                        if (!sec.contains(s)) {
                sec.add(s);
            }
        }

        while (login == false && j < sec.size()) {
            if (sec.get(j).getNom().equals(user) && sec.get(j).getPassword().equals(password)) {
                login = true;
            } else {
                login = false;
                j++;
            }
        }

        return login;

    }
    public boolean verifieLogin(String user, String password) {
        boolean login = false;
        int i = 0;
        int j = 0;
        Vector<Medecin> med = new Vector<Medecin>();
        for (i = 0; i < fiches.size(); i++) {
            FicheDeSoins f = fiches.get(i);
            Medecin m = f.getMedecin();
            if (!med.contains(m)) {
                med.add(m);
            }
        }

        while (login == false && j < med.size()) {
            if (med.get(j).getNom().equals(user) && med.get(j).getPassword().equals(password)) {
                login = true;
            } else {
                login = false;
                j++;
            }
        }

        return login;

    }

    public Code getCode(Acte a) {
        return a.getCode();
    }

    private Object getActes() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
//    
//         public void  TierParDate (comparable c){
//              for (int i = 0; i < dm.getFDS(m).size(); i++) {
//                    for (int j =0; j<dm.getFDS(m).size();j++){
//                        FicheDeSoins f = dm.getFDS(m).get(i);
//                        FicheDeSoins f1 = dm.getFDS(m).get(j);
//                        if ()
//             
//         }
//   
}
