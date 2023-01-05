package de.kaitokuntatsu.parser;

import de.kaitokuntatsu.datastructures.*;
import java.io.IOException;

public class ScannerAndParser {

    static final String[] COMMANDS = {"RE","LI", "WH", "VW"};

    public List<Befehl> parse(String pInput) {
        return parse(pInput.split(" "), 0);
    }

    public List<Befehl> parse(String[] pPotentialCommands, int pStartIndex) {
        List<Befehl> lCommands = new List<>();

        for (int i = pStartIndex; i < pPotentialCommands.length; ++i) {
            if (isCommand(pPotentialCommands[i]) && i + 1 < pPotentialCommands.length) {
                int lValue = parseToInt(pPotentialCommands[i+1]);
                if (lValue == -1) {
                    return null;
                }

                if (pPotentialCommands[i].equals("WH")) {
                    if (!pPotentialCommands[i+2].equals("[")) {
                        return null;
                    }

                    List<Befehl> lCommandsInLoop = parse(pPotentialCommands, i+3);
                    for (int j = 0; j < lValue; ++j) {
                        lCommandsInLoop.toFirst();
                        while (lCommandsInLoop.hasAccess()) {
                            lCommands.append(lCommandsInLoop.getContent());
                        }
                    }
                }
                else {
                    lCommands.append(new Befehl(pPotentialCommands[i], lValue));
                }

                ++i;
            }
            else if (pPotentialCommands[i].equals("]")) {
                return lCommands;
            }
            else {
                return null;
            }
        }

        return lCommands;
    }

    private boolean isCommand(String pPotentialCommand) {
        for (String lCmd : COMMANDS) {
            if (lCmd.equals(pPotentialCommand)) {
                return true;
            }
        }
        return false;
    }

    private int parseToInt(String pStr) {
        char[] lChars = pStr.toCharArray();
        for (char c : lChars) {
            if (c < '0' || c > '9')
                return -1;
        }
        return Integer.parseInt(pStr);
    }

    public static void main(String[] args) throws IOException {
        ScannerAndParser sut = new ScannerAndParser();
        List<Befehl> cmds = sut.parse("VW 5 WH 5 [ LI 5 ]");
        cmds.toFirst();
        while (cmds.hasAccess()) {
            System.out.println(cmds.getContent().typ() + " + " + cmds.getContent().wert());
            cmds.next();
        }
     }
}

