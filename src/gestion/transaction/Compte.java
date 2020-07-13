/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion.transaction;
import java.sql.*;
import javax.swing.JOptionPane;
/**
 *
 * @author NIASS
 */
public class Compte {
    private String nom;
    private String prenom;
    private double solde;
    private double decouvert;
    private int numeroCompte;
    
    public Compte(String n, String p, double s, double d, int num){
        this.nom = n;
        this.prenom = p;
        this.solde = s;
        this.decouvert = d;
        this.numeroCompte = num;
    }
    public String getNom() {
        return this.nom;
    }
    public String getPrenom (){
        return this.prenom;
    }
    
    public double getSolde (){
        return this.solde;
    }
    public double getDecouvert(){
        return this.decouvert;
    }
        public int getNumeroCompte() {
        return this.numeroCompte;
    }
    public String getHistorique(){
       return "Numéro du compte : " + this.numeroCompte+"\n"+
               "Prénom : "+this.prenom +"\n"+
               "Nom : " + this.nom +"\n"+
               "Solde : "+ this.solde +"\n"+ "Découvert : " + this.decouvert;
    }
    
    public void setNom(String n) {
        this.nom = n;
    }

    public void setPrenom(String p) {
        this.prenom = p;
    }

    public void setSolde(double s) {
        this.solde = s;
    }

    public void setDecouvert(double nouveauDecouvert) {
        this.decouvert = nouveauDecouvert;
    }

    public void setNumCompte(int num) {
        this.numeroCompte = num;
    }

    public double crediter(double montant) {
        return this.solde + montant;
    }
      public double debiter(double montant) {
        if (montant + this.decouvert > this.solde){
            JOptionPane.showMessageDialog(null, "Erreur, la solde est  insuffisant !", "Opération échouée", 0, null);
            return -1.0;
        }
        return this.solde-montant;
    }
    public static Connection connexion() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/gestioncompte";
        String user = "root";
        String passwd = " ";
        Connection con = null;
        try {
           Class.forName("com.mysql.jdbc.Driver");  
           con = DriverManager.getConnection(url, user,passwd); 
            return con;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,"Connexion non établie","ERROR",JOptionPane.ERROR_MESSAGE); 
            return null;
        }    
    }
}