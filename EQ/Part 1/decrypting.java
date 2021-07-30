import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

class decrypter{
    private String number_escape="💯";
    private String emojis ="🐶🐱🐭🐹🐰🦊🐻🐼🐨🐯🦁🐮🐷🐽🐸🐵🙈🙉🙊🐒🐔🐧🐦🐤🐣🐥🦆🦅🦉🦇🐺🐗🐴🦄🐝🪱🐛🦋🐌🐞🐜🪰🐬🪲🦟🦗🕷🕸🦂🐢🐍🦎🦖🦕";
    private String letters ="abcdefghijklmnopqrstuvwxyz";
    private String alpha_scape;
    private String[] symbols = emojis.codePoints().boxed().map(i -> new String(Character.toChars(i))).toArray(String[]::new);
    private HashMap<String,Character> encrypterDict = new HashMap<>();
    public void decryptByDate(String date,String encryptedDate){
        String firstNumber_symbol = encryptedDate.codePoints().boxed().map(i-> new String(Character.toChars(i))).toArray(String[]::new)[0];
        int firstNumber_symbolIndex = findFirstIndex(date.charAt(0),firstNumber_symbol);
        fillDict(firstNumber_symbolIndex);
    }
    private boolean isEscape(String Symbol){
        return (number_escape.contains(Symbol)||alpha_scape.contains(Symbol));
    }
    public String getCharacter(String Symbol,String nextS){
        if(!emojis.contains(Symbol)&&!isEscape(Symbol)) return Symbol;
        if(isEscape(Symbol)) return "";
        if(isEscape(nextS)){
            return ""+encrypterDict.get(Symbol+nextS);    
        }
        return ""+encrypterDict.get(Symbol);
    }
    public String getCharacter(String Symbol){
        if(number_escape.contains(Symbol)||alpha_scape.contains(Symbol)) return "";
        if(!emojis.contains(Symbol)) return Symbol;
        return ""+encrypterDict.get(Symbol);
    }
    private boolean compareEmojis(String emoji1, String emoji2){
        return !emoji1.contains(emoji2);
    }
    private int findFirstIndex(char firstNumberChar,String firstNumber_symbol){
        int firstNumber = Integer.parseInt(Character.toString(firstNumberChar));
        int firstNumber_symbolIndex =0;
        String symbolFromList =symbols[firstNumber_symbolIndex];
        while(compareEmojis(symbolFromList, firstNumber_symbol)){
            firstNumber_symbolIndex++;
            symbolFromList =symbols[firstNumber_symbolIndex];
        }
        if(firstNumber==1) return firstNumber_symbolIndex;
        if(firstNumber==0){
            firstNumber=9;
            firstNumber_symbolIndex=checkSymbolInd(firstNumber_symbolIndex-1);
        }
        while(firstNumber!=1){
            firstNumber--;
            firstNumber_symbolIndex=checkSymbolInd(firstNumber_symbolIndex-1);
        }
        return firstNumber_symbolIndex;
    }
    private void fillDict(int start){
        char[] lettersArr = letters.toCharArray();
        int i = 0;
        int symbolInd = checkSymbolInd(start);
        set_alpha_scape(symbolInd,symbols);
        while(encrypterDict.size()<62){
            if(i<9) encrypterDict.put(symbols[symbolInd]+number_escape,digitToCharacter(i+1));
            if(i==9)encrypterDict.put(symbols[symbolInd]+number_escape,digitToCharacter(0));
            encrypterDict.put(symbols[symbolInd],lettersArr[i]);
            encrypterDict.put(symbols[symbolInd]+alpha_scape,Character.toUpperCase(lettersArr[i]));
            i++;
            symbolInd = checkSymbolInd(symbolInd+1);
        }

    }
    private char digitToCharacter(int number){
        return Integer.toString(number).toCharArray()[0];
    }
    private int checkSymbolInd(int index){
        if(index>53) index = 0;
        if(index<0) index = 53;
        return index;
    }
    private void set_alpha_scape(int initial_symbol_index,String[] symbols){
        int alpha_scape_ind = initial_symbol_index+26;
        if(alpha_scape_ind>53) alpha_scape_ind-=53;
        alpha_scape = symbols[alpha_scape_ind];
    }
}
public class decrypting extends decrypter{
    public static void main(String[] args) throws IOException{
        File reader = new File("encryptedMessage.txt");
        Scanner myReader =new Scanner(reader);
        FileWriter myWriter = new FileWriter("decryptedMessage.txt");
        decrypting Decrypting = new decrypting();
        Decrypting.decryptByDate("29/07/2021", "🦎💯🐰💯/🦊💯🐭💯/🦎💯🦊💯🦎💯🐍💯");
        while(myReader.hasNext()){
            myWriter.write(Decrypting.reveal_line(myReader.nextLine()));
        }
        myReader.close();
        myWriter.close();
    }
    public String reveal_line(String line){
        String[] line_symbols = line.codePoints().boxed().map(i -> new String(Character.toChars(i))).toArray(String[]::new);
        String Line="";
        for(int i=0;i<line_symbols.length;i++){
            if(i<line_symbols.length-1) Line+= getCharacter(line_symbols[i],line_symbols[i+1]);
            else Line+=getCharacter(line_symbols[i])+"\n";
        }
        return Line;
    }
}
