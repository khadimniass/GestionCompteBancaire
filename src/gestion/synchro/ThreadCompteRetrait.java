/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion.synchro;

import gestion.transaction.Compte;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
/**
 *
 * @author NIASS
 */
public class ThreadCompteRetrait extends Thread{
    private Compte CompteRetrait;
    private double depot;
    private int numCompte;
    private PreparedStatement st;
   public ThreadCompteRetrait(String name, Compte c, double retrait, int num){
        super(name);
        this.CompteRetrait = CompteRetrait;
        this.depot = depot;
        this.numCompte = num;
    }   
	@Override
	public void run() {
    Compte Comptesynchrone=CompteRetrait;
    synchronized(Comptesynchrone){
    double newSolde=this.CompteRetrait.debiter(this.depot);
    if(newSolde!=-1){
        try {
            try {
    st=Compte.connexion().prepareStatement("UPDATE `client` SET `solde`= ? WHERE numerocompte = ?");
    st.setDouble(1, newSolde);
    st.setInt(2, this.numCompte);
    if(st.executeUpdate()!=0){
     JOptionPane.showMessageDialog(null, "Operation en cours d'execution,patientez !!! ","Chargement en cours...", 1, null);
     ThreadCompteRetrait.sleep(10000);
    JOptionPane.showMessageDialog(null, "Merci, le retrait fait avec succees","Operation reussite",1, null);   
            }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (InterruptedException e) {
        JOptionPane.showMessageDialog(null, "quelque chose à mal tourner");
        }
    }
}
}
}