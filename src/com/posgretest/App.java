
package com.posgretest;
 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
 
/**
 *
 * @author postgresqltutorial.com
 */
public class App{
 
    private final String url = "jdbc:postgresql://localhost/filmydb";
    private final String user = "postgres";
    private final String password = "Banby123";
 
    /**
     * Connect to the PostgreSQL database
     *
     * @return a Connection object
     */
    public Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the PostgreSQL server successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
 
        return conn;
    }
 
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    	Connection spoj = null;

    	App app = new App();
        spoj = app.connect();
        try {
        	System.out.println("DB schema je:  " + spoj.getSchema());
        	System.out.println("DB catalog je:  " + spoj.getCatalog());
        	PreparedStatement dotaz = spoj.prepareStatement("SELECT * FROM groceries where id <= 9");
        	ResultSet vysledky = dotaz.executeQuery();
        	while (vysledky.next()) {
                int id = vysledky.getInt(1);
                String name = vysledky.getString("name");
                float pocet = vysledky.getFloat("quantity");
                System.out.println("Id: " + id + ", name: " + name + ", quantity: " + pocet);
        	}
        	spoj.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}