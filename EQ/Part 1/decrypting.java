import java.util.HashMap;

class decrypter{
    private String number_escape="💯";
    private String emojis ="🐶🐱🐭🐹🐰🦊🐻🐼🐨🐯🦁🐮🐷🐽🐸🐵🙈🙉🙊🐒🐔🐧🐦🐤🐣🐥🦆🦅🦉🦇🐺🐗🐴🦄🐝🪱🐛🦋🐌🐞🐜🪰🐬🪲🦟🦗🕷🕸🦂🐢🐍🦎🦖🦕";
    private String letters ="abcdefghijklmnopqrstuvwxyz";
    private String alpha_scape;
    private HashMap<Character,String> encrypterDict = new HashMap<>();
    public void decryptByDate(String date,String encryptedDate){
        String[] symbols = emojis.codePoints().boxed().map(i -> new String(Character.toChars(i))).toArray(String[]::new);
        String[] date_symbols = encryptedDate.codePoints().boxed().map(i-> new String(Character.toChars(i))).toArray(String[]::new);
        int firstNumber = Integer.parseInt(Character.toString(date.charAt(0)));
        int firstNumber_symbolIndex =0;
        while(symbols[firstNumber_symbolIndex]!=date_symbols[0]){
            firstNumber_symbolIndex++;
        }
    }
    private void fillDict(int start){
        String[] symbols = emojis.codePoints().boxed().map(i -> new String(Character.toChars(i))).toArray(String[]::new);
        char[] lettersArr = letters.toCharArray();
        int i = 0;
        int symbolInd = checkSymbolInd(start);
        set_alpha_scape(symbolInd,symbols);
        while(encrypterDict.size()<62){
            if(i<9) encrypterDict.put(digitToCharacter(i+1), symbols[symbolInd]+number_escape);
            if(i==9)encrypterDict.put(digitToCharacter(0), symbols[symbolInd]+number_escape);
            encrypterDict.put(lettersArr[i], symbols[symbolInd]);
            encrypterDict.put(Character.toUpperCase(lettersArr[i]), symbols[symbolInd]+alpha_scape);
            i++;
            symbolInd = checkSymbolInd(symbolInd+1);
        }
    }
    private char digitToCharacter(int number){
        return Integer.toString(number).toCharArray()[0];
    }
    private int checkSymbolInd(int index){
        if(index>53||index<0) index = 0;
        return index;
    }
    private void set_alpha_scape(int initial_symbol_index,String[] symbols){
        int alpha_scape_ind = initial_symbol_index+26;
        if(alpha_scape_ind>53) alpha_scape_ind-=53;
        alpha_scape = symbols[alpha_scape_ind];
    }
}
public class decrypting {
       
}
