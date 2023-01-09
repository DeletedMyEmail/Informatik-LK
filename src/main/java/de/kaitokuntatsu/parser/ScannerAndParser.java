package de.kaitokuntatsu.parser;

import de.kaitokuntatsu.datastructures.*;
import java.io.IOException;

public class ScannerAndParser {

    static final String[] COMMANDS = {"RE","LI", "WH", "VW"};

    public static List<Befehl> parse(String pInput) {
        List<Befehl> lResult = new List<>();
        if (parse(pInput.split(" "), lResult, 0) == -1) {
            return null;
        }
        return lResult;
    }

    private static int parse(String[] pPotentialCommands, List<Befehl> pParsedCommands, int pStartIndex) {
        int i;
        for (i = pStartIndex; i < pPotentialCommands.length; ++i) {
            if (isCommand(pPotentialCommands[i]) && i + 1 < pPotentialCommands.length) {
                int lValue = parseToInt(pPotentialCommands[i+1]);
                if (lValue == -1) {
                    return -1;
                }

                if (pPotentialCommands[i].equals("WH")) {

                    if (!pPotentialCommands[i+2].equals("[")) {
                        return -1;
                    }

                    List<Befehl> lCommandsInLoop = new List<>();
                    int lNewIndex = parse(pPotentialCommands, lCommandsInLoop, i+3);
                    for (int j = 0; j < lValue; ++j) {
                        lCommandsInLoop.toFirst();
                        while (lCommandsInLoop.hasAccess()) {
                            pParsedCommands.append(lCommandsInLoop.getContent());
                            lCommandsInLoop.next();
                        }
                    }

                    i = lNewIndex;
                }
                else {
                    pParsedCommands.append(new Befehl(pPotentialCommands[i], lValue));
                }

                ++i;
            }
            else if (pPotentialCommands[i].equals("]")) {
                return i;
            }
            else {
                return -1;
            }
        }

        return i;
    }

    private static boolean isCommand(String pPotentialCommand) {
        for (String lCmd : COMMANDS) {
            if (lCmd.equals(pPotentialCommand)) {
                return true;
            }
        }
        return false;
    }

    private static int parseToInt(String pStr) {
        char[] lChars = pStr.toCharArray();
        for (char c : lChars) {
            if (c < '0' || c > '9')
                return -1;
        }
        return Integer.parseInt(pStr);
    }

    public static void main(String[] args) throws IOException {
        List<Befehl> cmds = ScannerAndParser.parse("VW 5 WH 5 [ LI 5 RE 4 WH 2 [ VW 2 VW 1 ] ]");
        if (cmds == null) {
            System.out.println("Invalid word");
            return;
        }
        cmds.toFirst();
        while (cmds.hasAccess()) {
            System.out.println(cmds.getContent().typ() + " + " + cmds.getContent().wert());
            cmds.next();
        }
     }
}

