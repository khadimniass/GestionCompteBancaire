/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion.synchro;
import gestion.transaction.Compte;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
//import java.sql.PreparedStatement;
/**
 *
 * @author NIASS
 */
public class ThreadCompteDepot extends Thread{
    private Compte CompteDep;
    private double depot;
    private int numCompte;
    private PreparedStatement st;
    //Constructeur
    public ThreadCompteDepot(String name, Compte CompteDep, double depot, int num){
       super(name);
        this.CompteDep = CompteDep;
        this.depot = depot;
        this.numCompte = num;
    }
@Override
public void run() {
    Compte Comptesynchrone=CompteDep;
    synchronized(Comptesynchrone){
        try {
            try {
            double newSolde=this.CompteDep.crediter(depot);
            st=Compte.connexion().prepareStatement("UPDATE `client` SET `solde`= ? WHERE numerocompte = ?");
            st.setDouble(1, newSolde);
            st.setInt(2, this.numCompte);
            if(st.executeUpdate()!=0){
                JOptionPane.showMessageDialog(null, "Operation en cours d'execution,patientez !!! ","Chargement en cours...", 1, null);
                ThreadCompteDepot.sleep(1000);
                JOptionPane.showMessageDialog(null, "Merci, le depot a fait avec succees...", "Operation reussite",1, null);   
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            Logger.getLogger(ThreadCompteDepot.class.getName()).log(Level.SEVERE, null, ex);
        }
        } catch (InterruptedException e) {
          JOptionPane.showMessageDialog(null, "quelque chose à mal tourner !!!");  
        }
        
    }
}
}

