import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Restaurant implements GenericService<Restaurant> {
    private int idRestaurant; // No manual assignment
    private String nume;
    private String adresa;
    private String tipMancare;
    private ArrayList<Produs> meniu;
    private HashSet<Recenzie> recenzii;
    private double medieRating;

    // Constructor fara parametri
    public Restaurant() {
        this.nume = "";
        this.adresa = "";
        this.tipMancare = "";
        this.meniu = new ArrayList<>();
        this.recenzii = new HashSet<>();
        this.medieRating = 0.00;
    }

    // Constructor parametrizat
    public Restaurant(String nume, String adresa, String tipMancare) {
        this.nume = nume;
        this.adresa = adresa;
        this.tipMancare = tipMancare;
        this.meniu = new ArrayList<>();
        this.recenzii = new HashSet<>();
        this.medieRating = 0.00;
    }

    // Constructor de copiere
    Restaurant(Restaurant other) {
        this.nume = other.nume;
        this.adresa = other.adresa;
        this.tipMancare = other.tipMancare;
        this.recenzii = new HashSet<>(other.recenzii);
        this.meniu = new ArrayList<Produs>();
        this.meniu.addAll(other.meniu);
        this.medieRating = other.medieRating;
    }

    // Getteri
    public String getNume() {
        return nume;
    }

    public double getMedieRating() {
        return medieRating;
    }

    public String getAdresa() {
        return adresa;
    }

    public int getIdRestaurant() {
        return idRestaurant;
    }


    public String getTipMancare() {
        return tipMancare;
    }

    public ArrayList<Produs> getMeniu() {
        return meniu;
    }

    // Setteri
    public void setNume(String nume) {
        this.nume = nume;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public void setMeniu(ArrayList<Produs> meniu) {
        this.meniu = meniu;
    }

    public void setTipMancare(String tipMancare) {
        this.tipMancare = tipMancare;
    }


    // Alte metode
    @Override
    public String toString() {
        return idRestaurant + ". " + nume + " - " + adresa + ", tip mancare: " + tipMancare + ". Rating: " + medieRating;
    }

    public void afiseazaMeniu() {
        System.out.println("Meniu - " + nume);
        for (Produs prod : meniu) {
            prod.afiseazaDetalii();
        }
    }

    public void adaugaProdusInMeniu(Produs produs) {
        meniu.add(produs);
    }

    public void preiaComanda(Comanda comanda) {
        comanda.setStatusComanda(StatusComanda.PRELUATA);
    }

    public void preparaComanda(Comanda comanda) {
        comanda.setStatusComanda(StatusComanda.IN_PREPARARE);
    }

    public void adaugaRecenzie(Recenzie recenzie) {
        recenzii.add(recenzie);
    }

    public void actualizeazaMedieRating() {
        if (recenzii.isEmpty()) {
            medieRating = 0.0;
        } else {
            double suma = 0;
            for (Recenzie recenzie : recenzii) {
                suma += recenzie.getRating();
            }
            this.medieRating = suma / recenzii.size();
        }
    }

    @Override
    public void insert(Restaurant restaurant) throws SQLException {
        String sql = "INSERT INTO Restaurante (nume, adresa, tip_mancare, medie_rating) VALUES (?, ?, ?, ?)";

        try (Connection conn = Database.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, restaurant.getNume());
            pstmt.setString(2, restaurant.getAdresa());
            pstmt.setString(3, restaurant.getTipMancare());
            pstmt.setDouble(4, restaurant.getMedieRating());

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating restaurant failed, no rows affected.");
            }

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    restaurant.idRestaurant = generatedKeys.getInt(1); // Set the generated ID
                } else {
                    throw new SQLException("Creating restaurant failed, no ID obtained.");
                }
            }
        }
    }

    @Override
    public Restaurant cautaDupaID(int id) {
        String sql = "SELECT * FROM Restaurante WHERE id_restaurant = ?";
        Restaurant restaurant = null;

        try (Connection conn = Database.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                restaurant = new Restaurant();
                restaurant.idRestaurant = rs.getInt("id_restaurant");
                restaurant.nume = rs.getString("nume");
                restaurant.adresa = rs.getString("adresa");
                restaurant.tipMancare = rs.getString("tip_mancare");
                restaurant.medieRating = rs.getDouble("medie_rating");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return restaurant;
    }

    @Override
    public List<Restaurant> cauta() {
        String sql = "SELECT * FROM Restaurante";
        List<Restaurant> restaurante = new ArrayList<>();

        try (Connection conn = Database.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Restaurant restaurant = new Restaurant();
                restaurant.idRestaurant = rs.getInt("id_restaurant");
                restaurant.nume = rs.getString("nume");
                restaurant.adresa = rs.getString("adresa");
                restaurant.tipMancare = rs.getString("tip_mancare");
                restaurant.medieRating = rs.getDouble("medie_rating");

                restaurante.add(restaurant);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return restaurante;
    }

    @Override
    public void update(Restaurant restaurant) {
        String sql = "UPDATE Restaurante SET nume = ?, adresa = ?, tip_mancare = ?, medie_rating = ? WHERE id_restaurant = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, restaurant.getNume());
            pstmt.setString(2, restaurant.getAdresa());
            pstmt.setString(3, restaurant.getTipMancare());
            pstmt.setDouble(4, restaurant.getMedieRating());
            pstmt.setInt(5, restaurant.getIdRestaurant());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM Restaurante WHERE id_restaurant = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}


