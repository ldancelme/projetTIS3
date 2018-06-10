/*
 * LectureXML.java
 *
 * Created on 5 janvier 2006, 18:26
 *
 * Lecture d'un document XML et transformation en instances Java
 */
package NF;

import java.io.IOException;
import java.io.InputStream;
import java.io.FileInputStream;
import java.util.List;
import java.util.Vector;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

/**
 * Lecture d'un document XML et transformation en instances Java.
 *
 * @author promayon
 */
public class LectureXML {

    /// nom du document XML a analyser
    private String nomFichier;
    private final static String repBase = "src/donnees/";

    // 'nomFichier' est le nom d'un fichier XML se trouvant dans le repertoire 'repBase' a lire :
    public LectureXML(String nomFichier) {
        this.nomFichier = nomFichier;
    }

    public DossierMedical getDossier() {
        DossierMedical dossierCourant = null;
        Date date = null;
        Medecin medecinCourant = null;
        Patient patientCourant = null;
        Adresse adresseCourant=null;
        Secretaire secretaireCourant=null;
        
        List<Acte> actes = new Vector<Acte>();
        List<Medecin> med = new Vector<Medecin>();
        
        String donneesCourantes = "";
        String nomCourant = "";
        String prenomCourant = "";
        String specialiteCourante = "";
        String rueCourant="";
        String villeCourant="";
       String mdpCourant = "";
        String speCourant  = "";
        
        Code codeCourant = null;
        
        
        
        int coefCourant = 0;
        long ssCourant  = 0;
        int ageCourant  = 0;
        int depCourant=0;

        // analyser le fichier par StAX
        try {
            // instanciation du parser
            InputStream in = new FileInputStream(repBase + nomFichier);
            XMLInputFactory factory = XMLInputFactory.newInstance();
            XMLStreamReader parser = factory.createXMLStreamReader(in);

            // lecture des evenements
            for (int event = parser.next(); event != XMLStreamConstants.END_DOCUMENT; event = parser.next()) {
                // traitement selon l'evenement
                switch (event) {
                    case XMLStreamConstants.START_ELEMENT:
                        if (parser.getLocalName().equals("dossiers")) {
                            dossierCourant = new DossierMedical();
                        }
                        break;
                    case XMLStreamConstants.END_ELEMENT:
                        if (parser.getLocalName().equals("acte")) {
                            actes.add(new Acte(codeCourant, coefCourant));
                        }

                        if (parser.getLocalName().equals("code")) {
                            codeCourant = getCode(donneesCourantes);
                            if (codeCourant == null) {
                                throw new XMLStreamException("Impossible de trouver le code d'acte = " + donneesCourantes);
                            }
                        }
                        if (parser.getLocalName().equals("coef")) {
                            coefCourant = Integer.parseInt(donneesCourantes);
                        }
                        if (parser.getLocalName().equals("date")) {
//                            2018-06-04
                            int annee = Integer.parseInt(donneesCourantes.substring(0, donneesCourantes.indexOf('-')));
                            int mois = Integer.parseInt(donneesCourantes.substring(donneesCourantes.indexOf('-') + 1, donneesCourantes.lastIndexOf('-')));
                            int jour = Integer.parseInt(donneesCourantes.substring(donneesCourantes.lastIndexOf('-') + 1, donneesCourantes.length()));

                            date = new Date(jour, mois, annee);
                        }
                        if (parser.getLocalName().equals("ficheDeSoins")) {
                            FicheDeSoins f = new FicheDeSoins(patientCourant, medecinCourant, date);
                            // ajout des actes
                            for (int i = 0; i < actes.size(); i++) {
                                Acte a = (Acte) actes.get(i);
                                f.ajouterActe(a);
                            }
                            // effacer tous les actes de la liste
                            actes.clear();
                            // ajouter la fiche de soin au dossiers
                            dossierCourant.ajouterFiche(f);
                        }
                        if (parser.getLocalName().equals("medecin")) {
                            medecinCourant = new Medecin(nomCourant, prenomCourant, specialiteCourante, mdpCourant);
//                            med.add(new Medecin(nom, prenom, spe));
                        }
                        if (parser.getLocalName().equals("nom")) {
                            nomCourant = donneesCourantes;
                        }
                        if(parser.getLocalName().equals("Secretaire")){
                            secretaireCourant =new Secretaire(nomCourant,prenomCourant,mdpCourant);
                            dossierCourant.ajouterSecretaire(secretaireCourant);
                        }
                        if (parser.getLocalName().equals("patient")) {
                            
//                               if(!dossierCourant.allPatient().contains(patientCourant)){
                            patientCourant = new Patient(nomCourant, prenomCourant,adresseCourant, ssCourant, ageCourant);
//                            dossierCourant.ajouterPatient(patientCourant);
//                            }
//                            else {
//                                int i=0;
//                                while (!(dossierCourant.getPatients().get(i).equals(patientCourant))&& (i<dossierCourant.getPatients().size())){
//                                    i++;
//                                }
//                                patientCourant = dossierCourant.getPatients().get(i);
//                            }
                            
                        }
                        if (parser.getLocalName().equals("adresse")) {
                           adresseCourant = new Adresse(rueCourant, depCourant, villeCourant);
                        }
                        if (parser.getLocalName().equals("prenom")) {
                            prenomCourant = donneesCourantes;
                        }
                        if (parser.getLocalName().equals("ss")) {
                           ssCourant = Long.parseLong(donneesCourantes);
                        }
                        if (parser.getLocalName().equals("codePostal")) {
                            depCourant = Integer.parseInt(donneesCourantes);
                        }
                        if (parser.getLocalName().equals("rue")) {
                            rueCourant = donneesCourantes;
                        }
                        
                        if (parser.getLocalName().equals("ville")) {
                            villeCourant = donneesCourantes;
                        }
                        if (parser.getLocalName().equals("age")) {
                            ageCourant = Integer.parseInt(donneesCourantes);
                        }
                        if (parser.getLocalName().equals("specialite")) {
                            specialiteCourante = donneesCourantes;
                        }
                        if (parser.getLocalName().equals("password")) {
                            mdpCourant = donneesCourantes;
                        }
                        break;
                    case XMLStreamConstants.CHARACTERS:
                        donneesCourantes = parser.getText();
                        break;
                } // end switch
            } // end while
            parser.close();
        } catch (XMLStreamException ex) {
            System.out.println("Exception de type 'XMLStreamException' lors de la lecture du fichier : " + nomFichier);
            System.out.println("Details :");
            System.out.println(ex);
        } catch (IOException ex) {
            System.out.println("Exception de type 'IOException' lors de la lecture du fichier : " + nomFichier);
            System.out.println("Verifier le chemin.");
            System.out.println(ex.getMessage());
        }

        return dossierCourant;
    }

    private static Code getCode(String code) {
        if (code.equals("CS")) {
            return Code.CS;
        }
        if (code.equals("CSC")) {
            return Code.CSC;
        }
        if (code.equals("FP")) {
            return Code.FP;
        }
        if (code.equals("KC")) {
            return Code.KC;
        }
        if (code.equals("KE")) {
            return Code.KE;
        }
        if (code.equals("K")) {
            return Code.K;
        }
        if (code.equals("KFA")) {
            return Code.KFA;
        }
        if (code.equals("KFB")) {
            return Code.KFB;
        }
        if (code.equals("ORT")) {
            return Code.ORT;
        }
        if (code.equals("PRO")) {
            return Code.PRO;
        }
        // probleme : code inconnu
        return null;
    }
// public Patient getPatient() {
//     Patient patientCourant=null;
//     String donneesCourante = "";
//     String nomCourant = "";
//     String prenomCourant = "";
//     Adresse adresseCourant=null;
//     int ssCourant  = 0;
//     int ageCourant  = 0;
//     String rueCourant="";
//     String villeCourant="";
//     int depCourant=0;
//     List<Patient> p = new Vector<Patient>();
// try {
//            // instanciation du parser
//            InputStream in = new FileInputStream(repBase + nomFichier);
//            XMLInputFactory factory = XMLInputFactory.newInstance();
//            XMLStreamReader parser = factory.createXMLStreamReader(in);
//
//            // lecture des evenements
//            for (int event = parser.next(); event != XMLStreamConstants.END_DOCUMENT; event = parser.next()) {
//                // traitement selon l'evenement
//                switch (event) {
//                    case XMLStreamConstants.START_ELEMENT:
//                        if (parser.getLocalName().equals("patient")) {
//                            patientCourant = new Patient(nomCourant,prenomCourant,adresseCourant,ssCourant,ageCourant);
//                        for (int i = 0; i < p.size(); i++) {
//                                Patient a = (Patient) p.get(i);
//                                a.ajouterPatient(a);
//                            }
//                        }
//                        break;
//                    case XMLStreamConstants.END_ELEMENT:
//                        if (parser.getLocalName().equals("nom")) {
//                            nomCourant=donneesCourante;
//                        }
//                        if (parser.getLocalName().equals("prenom")) {
//                            prenomCourant=donneesCourante;
//                        }
////                        
//                       
//                        
//                            
////                        if (parser.getLocalName().equals("ss")) {
////                            ssCourant = Integer.parseInt(donneesCourante);
////                        }
////                        if (parser.getLocalName().equals("age")) {
////                             ageCourant = Integer.parseInt(donneesCourante);
////                        }
////                        if (parser.getLocalName().equals("adresse")) {
////                           adresseCourant = new Adresse(rueCourant, depCourant, villeCourant);
////                        }
////                        if (parser.getLocalName().equals("rue")) {
////                            rueCourant=donneesCourante;
////                        }
////                        if (parser.getLocalName().equals("rue")) {
////                            depCourant=Integer.parseInt(donneesCourante);
////                        }
////                        if (parser.getLocalName().equals("rue")) {
////                            villeCourant=donneesCourante;
////                        }
//                        break;
//                        case XMLStreamConstants.CHARACTERS:
//                        donneesCourante = parser.getText();
//                        break;
//                } // end switch
//            } // end while
//            parser.close();
//        } catch (XMLStreamException ex) {
//            System.out.println("Exception de type 'XMLStreamException' lors de la lecture du fichier : " + nomFichier);
//            System.out.println("Details :");
//            System.out.println(ex);
//        } catch (IOException ex) {
//            System.out.println("Exception de type 'IOException' lors de la lecture du fichier : " + nomFichier);
//            System.out.println("Verifier le chemin.");
//            System.out.println(ex.getMessage());
//        }
//                        
//     
//    
// return patientCourant;}}
}
