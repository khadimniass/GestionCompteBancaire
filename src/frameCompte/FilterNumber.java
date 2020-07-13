/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frameCompte;
import java.awt.Toolkit;
import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
/**
 *
 * @author NIASS
 */
public class FilterNumber extends DocumentFilter{
        private static int counter = 0;
    private char currentCharacter;
    private JTextField zone;

    public FilterNumber(JTextField champs) {
        this.zone = champs;
    }
    @Override
    public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String str, AttributeSet as)
            throws BadLocationException {
        for (int n = str.length(); n > 0; n--) {
            char c=str.charAt(n-1);
            if (Character.isDigit(c)) {
                super.replace(fb, offset, length, String.valueOf(c), as);
            }
            if (c == '.') {
                if (++counter > 1) {
                    Toolkit.getDefaultToolkit().beep();
                    continue;
                }
                super.replace(fb, offset, length, String.valueOf(c), as);
                continue;
            }
            if (Character.isDigit(c) ) continue;
            Toolkit.getDefaultToolkit().beep();
        }
    }

    @Override
    public void remove(DocumentFilter.FilterBypass fb, int offset, int length) throws BadLocationException {
    }
}