package src;


import java.sql.*;

public class Anomalie {
    private static Connection connection1;
    private static Connection connection2;
    private static long policeId;

    public static void main(String[] args) throws SQLException {
        commited_read();


    }

    private static void commited_read() throws SQLException {
        String url = "jdbc:mysql://127.0.0.1:3306/pokus?user=root&password=";
        connection1 = DriverManager.getConnection(url);
        connection2 = DriverManager.getConnection(url);

        connection1.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
        connection2.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);

        connection1.setAutoCommit(false);
        connection2.setAutoCommit(false);

        PreparedStatement statement = connection1.prepareStatement("INSERT INTO police (id_police, nazev, popis) VALUES (1, 'Police Test', 'Testovaci police')", Statement.RETURN_GENERATED_KEYS);

        if (statement.executeUpdate() > 0) {
            ResultSet resultSet = statement.getGeneratedKeys();

            if (resultSet.next()) {
                policeId = resultSet.getLong(1);
            } else {
                System.err.println("Nebylo obdrzeno ID.");
                //return;
            }
            resultSet.close();
        } else {
            System.err.println("Nepodarilo se vytvorit testovaci data.");
            return;
        }

        connection1.commit();

        PreparedStatement statement3 = connection2.prepareStatement("SELECT popis FROM police WHERE id_police = ?");
        statement3.setLong(1, 1);

        ResultSet set2 = statement3.executeQuery();

        while (set2.next()) {
            String first = set2.getString("popis");
            System.out.println(first);
        }
        set2.close();

        PreparedStatement statement1 = connection1.prepareStatement("UPDATE police SET popis = 'Pokus'");
        statement1.executeUpdate();
        connection1.commit();


        PreparedStatement statement2 = connection2.prepareStatement("SELECT popis FROM police WHERE id_police = ?");
        statement2.setLong(1, 1);

        ResultSet set1 = statement2.executeQuery();
        connection2.commit();

        while (set1.next()) {
            String first = set1.getString("popis");
            System.out.println(first);
        }
        set1.close();



        connection1.close();
        connection2.close();
    }

    private static void uncommited_read() throws SQLException {
        String url = "jdbc:mysql://127.0.0.1:3306/pokus?user=root&password=";
        connection1 = DriverManager.getConnection(url);
        connection2 = DriverManager.getConnection(url);

        connection1.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
        connection2.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);

        connection1.setAutoCommit(false);
        connection2.setAutoCommit(false);

        PreparedStatement statement = connection1.prepareStatement("INSERT INTO police (id_police, nazev, popis) VALUES (1, 'Police Test', 'Testovaci police')", Statement.RETURN_GENERATED_KEYS);

        if (statement.executeUpdate() > 0) {
            ResultSet resultSet = statement.getGeneratedKeys();

            if (resultSet.next()) {
                policeId = resultSet.getLong(1);
            } else {
                System.err.println("Nebylo obdrzeno ID.");
                //return;
            }
            resultSet.close();
        } else {
            System.err.println("Nepodarilo se vytvorit testovaci data.");
            return;
        }

        connection1.commit();

        PreparedStatement statement1 = connection1.prepareStatement("UPDATE police SET popis = 'Pokus'");
        statement1.executeUpdate();


        PreparedStatement statement2 = connection2.prepareStatement("SELECT popis FROM police WHERE id_police = ?");
        statement2.setLong(1, 1);

        ResultSet set1 = statement2.executeQuery();
        connection2.commit();

        while (set1.next()) {
            String first = set1.getString("popis");
            System.out.println(first);
        }
        set1.close();

        connection1.rollback();

        connection1.close();
        connection2.close();
    }

}
