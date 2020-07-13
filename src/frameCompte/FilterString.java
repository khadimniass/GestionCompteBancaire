/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frameCompte;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

/**
 *
 * @author NIASS
 */
public class FilterString extends DocumentFilter {
    @Override
    public void replace(DocumentFilter.FilterBypass fb, int i, int j, String str, AttributeSet as) throws BadLocationException {
        for (int n = str.length(); n > 0; n--) {
            char c = str.charAt(n - 1);
            // tenir compte de l'espace pour certains noms ou prenom
            if (Character.isAlphabetic(c) || c == ' ') {
                super.replace(fb, i, j, String.valueOf(c), as);
            }
        }
    }
}