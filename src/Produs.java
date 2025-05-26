import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Produs implements GenericService<Produs> {
    private static int nrProduse;
    private int idProdus;
    private String numeProdus;
    private String descriere;
    private int pret;
    private int idRestaurant; // Foreign key to Restaurant

    // Bloc static
    static {
        nrProduse = 0;
        System.out.println("Initializare, numar de produse = 0");
    }

    // Bloc nestatic
    {
        idProdus = ++nrProduse;
    }

    // Constructor fara parametri
    public Produs() {
        this.numeProdus = "";
        this.descriere = "";
        this.pret = 0;
        this.idRestaurant = 0;
    }

    // Constructor parametrizat
    public Produs(String numeProdus, String descriere, int pret, int idRestaurant) {
        this.numeProdus = numeProdus;
        this.descriere = descriere;
        this.pret = pret;
        this.idRestaurant = idRestaurant;
    }

    // Constructor de copiere
    public Produs(Produs other) {
        this.numeProdus = other.numeProdus;
        this.descriere = other.descriere;
        this.pret = other.pret;
        this.idProdus = other.idProdus;
        this.idRestaurant = other.idRestaurant;
    }

    // Getteri
    public int getIdProdus() {
        return idProdus;
    }

    public int getPret() {
        return pret;
    }

    public String getDescriere() {
        return descriere;
    }

    public String getNumeProdus() {
        return numeProdus;
    }

    public int getIdRestaurant() {
        return idRestaurant;
    }

    public static int getNrProduse() {
        return nrProduse;
    }

    // Setteri
    public void setDescriere(String descriere) {
        this.descriere = descriere;
    }

    public void setNumeProdus(String numeProdus) {
        this.numeProdus = numeProdus;
    }

    public void setPret(int pret) {
        this.pret = pret;
    }

    public void setIdRestaurant(int idRestaurant) {
        this.idRestaurant = idRestaurant;
    }

    @Override
    public String toString() {
        return idProdus + ". " + numeProdus +
                ", Descriere: " + descriere +
                ", Pret: " + pret + " RON" +
                ", Restaurant ID: " + idRestaurant;
    }

    public void afiseazaDetalii() {
        System.out.println(toString());
    }

    @Override
    public void insert(Produs produs) throws SQLException {
        String sql = "INSERT INTO Produse (numeProdus, descriere, pret, id_restaurant) VALUES (?, ?, ?, ?)";

        try (Connection conn = Database.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, produs.getNumeProdus());
            pstmt.setString(2, produs.getDescriere());
            pstmt.setInt(3, produs.getPret());
            pstmt.setInt(4, produs.getIdRestaurant());

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating product failed, no rows affected.");
            }

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    produs.idProdus = generatedKeys.getInt(1);
                } else {
                    throw new SQLException("Creating product failed, no ID obtained.");
                }
            }
        }
    }

    @Override
    public Produs cautaDupaID(int id) {
        String sql = "SELECT * FROM Produse WHERE id = ?";
        Produs produs = null;

        try (Connection conn = Database.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                produs = new Produs();
                produs.idProdus = rs.getInt("id");
                produs.numeProdus = rs.getString("numeProdus");
                produs.descriere = rs.getString("descriere");
                produs.pret = rs.getInt("pret");
                produs.idRestaurant = rs.getInt("id_restaurant");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return produs;
    }

    @Override
    public List<Produs> cauta() {
        String sql = "SELECT * FROM Produse";
        List<Produs> produse = new ArrayList<>();

        try (Connection conn = Database.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Produs produs = new Produs();
                produs.idProdus = rs.getInt("id");
                produs.numeProdus = rs.getString("numeProdus");
                produs.descriere = rs.getString("descriere");
                produs.pret = rs.getInt("pret");
                produs.idRestaurant = rs.getInt("id_restaurant");
                produse.add(produs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return produse;
    }

    @Override
    public void update(Produs produs) {
        String sql = "UPDATE Produse SET numeProdus = ?, descriere = ?, pret = ?, id_restaurant = ? WHERE id = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, produs.getNumeProdus());
            pstmt.setString(2, produs.getDescriere());
            pstmt.setInt(3, produs.getPret());
            pstmt.setInt(4, produs.getIdRestaurant());
            pstmt.setInt(5, produs.getIdProdus());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM Produse WHERE id = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}