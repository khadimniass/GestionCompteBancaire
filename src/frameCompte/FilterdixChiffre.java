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
public class FilterdixChiffre extends  DocumentFilter{
             int counter = 0;
    @Override
    public void replace(FilterBypass fb, int i, int il, String str, AttributeSet as) throws BadLocationException {
        for (int n = str.length(); n > 0; n--) {
            char c=str.charAt(n-1);
       if(Character.isDigit(c)&&counter!=10){
           super.replace(fb, i, il, String.valueOf(c), as);
                counter++;
       }
    }
}
@Override
public void remove(FilterBypass fb, int i, int i1) throws BadLocationException {
super.remove(fb, i, i1);
counter--;
}
@Override
public void insertString(FilterBypass fb, int i, String string, AttributeSet as) throws BadLocationException {
super.insertString(fb, i, string, as);
}  
}