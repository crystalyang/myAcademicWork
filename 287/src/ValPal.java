/**
 * Created by Crystal on 2/4/17.
 */
public class ValPal {

    public boolean valPal(String s) {
        if(s == null) return false;
        int i = 0;
        int j = s.length()-1;
        while (i<=j){
            char f = s.charAt(i);
            char b = s.charAt(j);
            if(i==j){
                if(Character.isLetterOrDigit(f)|| f==' '){
                    return true;
                }else{
                    return false;
                }
            }else {
                if (f == ' ') {
                    i++;
                } else if (b == ' ') {
                    j--;
                } else if (!Character.isLetterOrDigit(f) ||!Character.isLetterOrDigit(b)) {
                    return false;
                } else if (Character.toLowerCase(f) != Character.toLowerCase(b)) {
                    return false;
                } else {
                    i++;
                    j--;
                }
            }
        }
        return true;
    }

}
