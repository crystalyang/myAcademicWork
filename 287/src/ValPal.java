/**
 * Created by Crystal on 2/4/17.
 */
public class ValPal {

    public boolean valPal(String s) {

        int i = 0;
        int j = s.length()-1;
        while (i<j){
            char f = s.charAt(i);
            char b = s.charAt(j);
            if(f == ' '){
                i++;
            }else if(b == ' '){
                j--;
            }else if(Character.toLowerCase(f)!=Character.toLowerCase(b)){
                return false;
            }else{
                i++;
                j--;
            }

        }
        return true;
    }

}
