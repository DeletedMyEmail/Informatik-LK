package de.kaitokuntatsu.random;

import de.kaitokuntatsu.datastructures.DBMS.DatabaseConnector;

public class Zugriff {

    private final DatabaseConnector mConnecctor;

    public Zugriff(String pPfad, int pPort) {
        mConnecctor = new DatabaseConnector("", pPort, pPfad, "", "");
    }

    // Gib Firmen zurück, die mit a anfangen und auf o enden
    public String[] gibDieAsUndOsDerFirmen() {
        mConnecctor.executeStatement("SELECT Firmenname FROM Kunde WHERE Firmenname LIKE 'a%' AND Firmenname LIKE '%o'");
        String[] lResult = new String[mConnecctor.getCurrentQueryResult().getRowCount()];
        mConnecctor.getCurrentQueryResult().getData();
        for (int i = 0; i < mConnecctor.getCurrentQueryResult().getRowCount(); ++i) {
            lResult[i] = mConnecctor.getCurrentQueryResult().getData()[i][0];
        }

        return lResult;
    }
}