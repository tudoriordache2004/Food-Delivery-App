import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
    private static final String URL = "jdbc:mysql://localhost:3306/food_delivery";
    private static final String USER = "root";
    private static final String PASSWORD = "tudiparola";

    static {
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {
            String createRestaurantTable = """
                    CREATE TABLE IF NOT EXISTS Restaurante (
                        id INT PRIMARY KEY,
                        nume VARCHAR(100),
                        adresa VARCHAR(200),
                        tip_mancare VARCHAR(50),
                        medie_rating DOUBLE
                    );
                    """;
            stmt.execute(createRestaurantTable);
            String createUserTable = """
                    CREATE TABLE IF NOT EXISTS Useri (
                        id INT PRIMARY KEY,
                        nume VARCHAR(100),
                        prenume VARCHAR(100),
                        adresa VARCHAR(200),
                        varsta INT,
                        email VARCHAR(100)
                    );
                    """;
            stmt.execute(createUserTable);

            String createRiderTable = """
                    CREATE TABLE IF NOT EXISTS Rideri (
                        id INT PRIMARY KEY,
                        nume VARCHAR(100),
                        prenume VARCHAR(100),
                        adresa VARCHAR(200),
                        varsta INT,
                        email VARCHAR(100)
                    );
                    """;
            stmt.execute(createRiderTable);

            String createProdusTable = """
                    CREATE TABLE IF NOT EXISTS Produse (
                        id INT PRIMARY KEY,
                        numeProdus VARCHAR(100),
                        descriere VARCHAR(200),
                        pret INT
                    );
                    """;
            stmt.execute(createProdusTable);


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}