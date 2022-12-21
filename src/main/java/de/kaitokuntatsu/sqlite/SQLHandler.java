package de.kaitokuntatsu.sqlite;
import de.joshua.DataStructures.DBMS.*;

public class SQLHandler {

    DatabaseConnector connector;

    public SQLHandler(String pDBName)
    {
        connector = new DatabaseConnector(null, 0, pDBName, null, null);
    }

    public void insert_test_data()
    {
        connector.executeStatement("INSERT INTO Personen VALUES(\"Fritz\")");
    }

    public void print_db()
    {
        connector.executeStatement("SELECT * FROM Personen");

        String[][] data = connector.getCurrentQueryResult().getData();


        for (String[] datensatz : data)
        {
            for (String attribut : datensatz)
            {
                System.out.println(attribut);
            }
        }

    }

    public static void main(String[] args) {
        SQLHandler handler = new SQLHandler("C:\\Users\\derdi\\OneDrive\\Informatik\\Java-Q1\\src\\de\\joshua\\sqlite\\[DB.JAVA.04].db");
        handler.insert_test_data();
        handler.print_db();
    }
}
