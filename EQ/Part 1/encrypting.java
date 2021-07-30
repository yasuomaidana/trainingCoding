import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

class encrypter{
    private String number_escape="💯";
    private String emojis ="🐶🐱🐭🐹🐰🦊🐻🐼🐨🐯🦁🐮🐷🐽🐸🐵🙈🙉🙊🐒🐔🐧🐦🐤🐣🐥🦆🦅🦉🦇🐺🐗🐴🦄🐝🪱🐛🦋🐌🐞🐜🪰🐬🪲🦟🦗🕷🕸🦂🐢🐍🦎🦖🦕";
    private String letters ="abcdefghijklmnopqrstuvwxyz";
    private String alpha_scape;
    private HashMap<Character,String> encrypterDict = new HashMap<>();
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
    
    encrypter(int start){
        fillDict(start);
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
    public String encrypt(char original){
        String encrypted = Character.toString(original);
        if (Character.isLetterOrDigit(original)) encrypted = encrypterDict.get(original);
        return encrypted;
    }
}
public class encrypting{
    encrypter encrypterTool;
    public static void main(String args[]) throws IOException{
        File reader = new File("originalMessage.txt");
        Scanner myReader =new Scanner(reader);
        FileWriter myWriter = new FileWriter("encryptedMessage.txt");

        encrypting Encrypting = new encrypting(50);

        while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            myWriter.write(Encrypting.encryptLine(data)+"\n");
        }
        myWriter.close();
        myReader.close();
    }
    encrypting(int start){
        encrypterTool = new encrypter(start);
    }
    public String encryptLine(String line){
        String encryptedLine="";
        for(char toEncrypt: line.toCharArray()){
            encryptedLine+=encrypterTool.encrypt(toEncrypt);
        }
        return encryptedLine;
    }
}