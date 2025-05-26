import java.sql.*;
import java.util.ArrayList;

public class UserService implements GenericService<User> {
    private ArrayList<User> utilizatori;

    // Constructor
    public UserService() {
        this.utilizatori = new ArrayList<>();
    }

    public void adaugaUser(User user) throws SQLException {
        utilizatori.add(user);
        System.out.println("Utilizator adăugat cu succes: " + user.getNume() + " " + user.getPrenume());
    }


    public void adaugaRecenzie(User user, Restaurant restaurant, int rating, String text) {
        Recenzie recenzie = new Recenzie(user, restaurant, rating, text);
        restaurant.adaugaRecenzie(recenzie);
        System.out.println("Recenzie adăugată pentru restaurantul: " + restaurant.getNume());
    }

    public void comenziActive(User user) {
        if (user.getComenzi().isEmpty()) {
            System.out.println("Nu aveți comenzi active.");
        } else {
            System.out.println("Comenzile active:");
            for (Comanda comanda : user.getComenzi()) {
                System.out.println(comanda);
            }
        }
    }

    public void adaugaComanda(User user, Comanda comanda) {
        user.getComenzi().add(comanda);
        System.out.println("Comandă adăugată pentru utilizatorul: " + user.getNume());
    }

    public ArrayList<User> getUtilizatori() {
        return utilizatori;
    }

    // Implementare CRUD pentru baza de date

    @Override
    public void insert(User user) throws SQLException {
        String sql = "INSERT INTO Useri (nume, prenume, adresa, varsta, email, parola) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = Database.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, user.getNume());
            pstmt.setString(2, user.getPrenume());
            pstmt.setString(3, user.getAdresa());
            pstmt.setInt(4, user.getVarsta());
            pstmt.setString(5, user.getEmail());
            pstmt.setString(6, user.getParola());

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Inserarea utilizatorului a eșuat, nicio linie afectată.");
            }

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    user.setIdUser(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Inserarea utilizatorului a eșuat, ID-ul nu a fost generat.");
                }
            }
        }
    }

    @Override
    public User cautaDupaID(int id) {
        String sql = "SELECT * FROM Useri WHERE id = ?";
        try (Connection conn = Database.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                User user = new User();
                user.setIdUser(rs.getInt("id"));
                user.setNume(rs.getString("nume"));
                user.setPrenume(rs.getString("prenume"));
                user.setAdresa(rs.getString("adresa"));
                user.setVarsta(rs.getInt("varsta"));
                user.setEmail(rs.getString("email"));
                user.setParola(rs.getString("parola"));
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<User> cauta() {
        String sql = "SELECT * FROM Useri";
        ArrayList<User> users = new ArrayList<>();
        try (Connection conn = Database.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                User user = new User();
                user.setIdUser(rs.getInt("id"));
                user.setNume(rs.getString("nume"));
                user.setPrenume(rs.getString("prenume"));
                user.setAdresa(rs.getString("adresa"));
                user.setVarsta(rs.getInt("varsta"));
                user.setEmail(rs.getString("email"));
                user.setParola(rs.getString("parola"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public void update(User user) {
        String sql = "UPDATE Useri SET nume = ?, prenume = ?, adresa = ?, varsta = ?, email = ?, parola = ? WHERE id = ?";
        try (Connection conn = Database.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, user.getNume());
            pstmt.setString(2, user.getPrenume());
            pstmt.setString(3, user.getAdresa());
            pstmt.setInt(4, user.getVarsta());
            pstmt.setString(5, user.getEmail());
            pstmt.setString(6, user.getParola());
            pstmt.setInt(7, user.getIdUser());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM Useri WHERE id = ?";
        try (Connection conn = Database.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}