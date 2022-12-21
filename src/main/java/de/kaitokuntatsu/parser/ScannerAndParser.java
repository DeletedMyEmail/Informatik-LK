package de.kaitokuntatsu.parser;

import de.kaitokuntatsu.utils.SortUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import de.kaitokuntatsu.datastructures.*;
import java.io.IOException;
import java.io.InputStream;

public class ScannerAndParser {

    static final String[] terminals = {"[","]", "RE","LI", "WH", "VW"};

    public List<Token> scan(String pInput) {
        List<Token> lTokenList = new List<>();
        String[] lPotentialTokens = pInput.split(" ");

        for (int i = 0; i < lPotentialTokens.length; ++i) {
            if (getNumber(lPotentialTokens[i]) > -1)
                lTokenList.append(new Token(Token.Type.ZAHL, lPotentialTokens[i]));
            else if (isTerminal(lPotentialTokens[i]))
                lTokenList.append(new Token(Token.Type.ZAHL, lPotentialTokens[i]));
            else
                return null;
        }

        return lTokenList;
    }

    public boolean parse(List<Token> pTokenList) {
        return false;
    }

    private boolean isTerminal(String pPotentialTerminal) {
        for (String term : terminals) {
            if (term.equals(pPotentialTerminal))
                return true;
        }
        return false;
    }

    private int getNumber(String pStr) {
        try {
            return Integer.parseInt(pStr);
        } catch (NumberFormatException ex) {
            return -1;
        }
    }

    public int getIndex(String pElement, String[] pArr) {
        for (int i = pArr.length-1; i >= 0; --i) {
            if (pArr[i].equals(pElement)) return i;
        }
        return -1;
    }

    public static void main(String[] args) throws IOException {

    }
}

