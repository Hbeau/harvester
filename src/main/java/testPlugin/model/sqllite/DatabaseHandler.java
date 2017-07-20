package testPlugin.model.sqllite;


import testPlugin.model.sqllite.HarvestedBlockDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseHandler {
    public static final String NAME = "harvester.db";

    public static final String url = "jdbc:sqlite:plugins/database/"+NAME;



    private static Connection connection = null;



    public static Connection getConnection() {
        if (connection == null) {
            createConnexion();
        }
        return connection;
    }

    private static Connection createConnexion() {
        try {
            connection = DriverManager.getConnection(url);

            createDatabase();
            System.out.println("connexion");
            return connection;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }/* finally {
          /*  try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }*/
    }
    private static void createDatabase(){
        try {
            Statement stmt = connection.createStatement();
            stmt.execute(HarvestedBlockDAO.tableCreation);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
