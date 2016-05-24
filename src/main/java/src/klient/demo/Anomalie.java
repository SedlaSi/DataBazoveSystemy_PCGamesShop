package src.klient.demo;


import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Anomalie {
    private Connection connection1;
    private Connection connection2;
    private final long FIRST_ID = Long.MAX_VALUE - 64;
    private String url = "jdbc:postgresql://127.0.0.1:5432/sedlasi_db?user=postgres&password=postgres";

    /*
     * http://www.postgresql.org/docs/current/static/transaction-iso.html
     *
     *
     * Na aktulni verzi PostgreSQL je nejnizsi stupen izolovanosti TRANSACTION_READ_COMMITTED, tudiz nejde vynutit dirty read.
     * Ten se nam povedlo vynutit na MySQL
     *
     * Na aktulnich verzi PostgreSQL a MySQL nejde pri stupni izolovanosti TRANSACTION_REPEATABLE_READ vynutit Phantom read.
     * Dohledali jsme, ze na Oracle a MsSQL jde pri tomto stupni izolovanosti vynutit.
     *
     * Pri stupni izolovanosti TRANSACTION_SERIALIZABLE, demonstrace anomalii konci deadlockem. Protoze testovane transakce se nedaji serializovat.
     *
     * */

    public Map<String, List<String>> otestujAnomalie() throws SQLException {
        connection1 = DriverManager.getConnection(url);
        connection2 = DriverManager.getConnection(url);

        PreparedStatement statement = connection1.prepareStatement("INSERT INTO police (id_police, nazev, popis) VALUES (?, 'Police Test', 'Testovaci police')");
        statement.setLong(1, FIRST_ID);
        statement.executeUpdate();

        connection1.setAutoCommit(false);
        connection2.setAutoCommit(false);

        Map<String, List<String>> result = new HashMap<>();
        List<String> iResult;

        connection1.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
        connection2.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
        iResult = demonstrujAnomalie();
        result.put("TRANSACTION_READ_UNCOMMITTED", iResult);


        connection1.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
        connection2.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
        iResult = demonstrujAnomalie();
        result.put("TRANSACTION_READ_COMMITTED", iResult);

        connection1.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);
        connection2.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);
        iResult = demonstrujAnomalie();
        result.put("TRANSACTION_REPEATABLE_READ", iResult);

        statement = connection1.prepareStatement("DELETE FROM police WHERE id_police = ?");
        statement.setLong(1, FIRST_ID);
        statement.executeUpdate();
        connection1.commit();

        connection1.close();
        connection2.close();

        return result;
    }

    public boolean dirtyRead() throws SQLException {
        boolean result = false;
        String change = "Zmeneny popis";

        PreparedStatement statement1 = connection1.prepareStatement("UPDATE police SET popis = ? WHERE id_police = ?");
        statement1.setString(1, change);
        statement1.setLong(2, FIRST_ID);
        statement1.executeUpdate();

        PreparedStatement statement2 = connection2.prepareStatement("SELECT popis FROM police WHERE id_police = ?");
        statement2.setLong(1, FIRST_ID);

        ResultSet resultSet = statement2.executeQuery();
        connection2.commit();


        if (resultSet.next() && change.equals(resultSet.getString("popis"))) {
            result = true;
        }

        resultSet.close();

        connection1.rollback();

        PreparedStatement statement3 = connection1.prepareStatement("UPDATE police SET popis = 'Testovaci police' WHERE id_police = ?");
        statement3.setLong(1, FIRST_ID);
        statement3.executeUpdate();

        connection1.commit();

        return result;
    }

    public boolean nonrepeatable_read() throws SQLException {
        boolean result = false;

        PreparedStatement statement1 = connection2.prepareStatement("SELECT popis FROM police WHERE id_police = ?");
        statement1.setLong(1, FIRST_ID);

        ResultSet set1 = statement1.executeQuery();

        PreparedStatement statement2 = connection1.prepareStatement("UPDATE police SET popis = 'Zmeneny popis' WHERE id_police = ?");
        statement2.setLong(1, FIRST_ID);
        statement2.executeUpdate();

        connection1.commit();

        PreparedStatement statement3 = connection2.prepareStatement("SELECT popis FROM police WHERE id_police = ?");
        statement3.setLong(1, FIRST_ID);

        ResultSet set2 = statement3.executeQuery();

        connection2.commit();

        if (set1.next() && set2.next() && !set1.getString("popis").equals(set2.getString("popis"))) {
            result = true;
        }

        set1.close();
        set2.close();

        PreparedStatement statement4 = connection1.prepareStatement("UPDATE police SET popis = 'Testovaci police' WHERE id_police = ?");
        statement4.setLong(1, FIRST_ID);
        statement4.executeUpdate();

        connection1.commit();

        return result;
    }

    public boolean phantom_read() throws SQLException {
        boolean result = false;
        long firstRowCount = 0;
        long secondRowCount = 0;

        PreparedStatement statement1 = connection2.prepareStatement("SELECT popis FROM police WHERE id_police >= ?");
        statement1.setLong(1, FIRST_ID);

        ResultSet set1 = statement1.executeQuery();

        while (set1.next()) {
            firstRowCount++;
        }

        set1.close();

        PreparedStatement statement2 = connection1.prepareStatement("INSERT INTO police (id_police, nazev, popis) VALUES (?, 'Police Test2', 'Testovaci police2')");
        statement2.setLong(1, FIRST_ID + 1);
        statement2.executeUpdate();

        connection1.commit();

        PreparedStatement statement3 = connection2.prepareStatement("SELECT popis FROM police WHERE id_police >= ?");
        statement3.setLong(1, FIRST_ID);

        ResultSet set2 = statement3.executeQuery();

        while (set2.next()) {
            secondRowCount++;
        }

        connection2.commit();

        set2.close();

        PreparedStatement statement4 = connection1.prepareStatement("DELETE FROM police WHERE id_police = ?");
        statement4.setLong(1, FIRST_ID + 1);
        statement4.executeUpdate();

        connection1.commit();


        return firstRowCount != secondRowCount;
    }

    public List<String> demonstrujAnomalie() throws SQLException {
        List<String> result = new ArrayList<>();

        if (dirtyRead()) {
            result.add("Dirty read");
        }

        if (nonrepeatable_read()) {
            result.add("Nonrepeatable read");
        }

        if (phantom_read()) {
            result.add("Phantom read");
        }

        return result;
    }

}
