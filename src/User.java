import java.io.ObjectInputFilter;
import java.lang.reflect.Array;
import java.util.*;
import java.sql.*;

public class User extends Persoana implements GenericService<User> {
    private static int nrUseri;
    private int idUser;
    private ArrayList<Comanda> comenzi;
    private ArrayList<Voucher> vouchere;

    // Bloc static
    static {
        nrUseri = 0;
        System.out.println("Initializare, numar de useri = 0");
    }

    // Bloc nestatic
    {
        idUser = ++nrUseri;
    }

    // Constructor fara parametri
    User() {
        super();
        comenzi = new ArrayList<Comanda>();
        vouchere = new ArrayList<>();
    }

    // Constructor parametrizat
    User(String nume, String prenume, String adresa, int varsta, String email) {
        super(nume, prenume, adresa, varsta, email);
        comenzi = new ArrayList<Comanda>();
        vouchere = new ArrayList<>();
    }

    // Constructor de copiere
    User(User other) {
        super(other.nume, other.prenume, other.adresa, other.varsta, other.email);
        this.idUser = other.idUser;
        this.comenzi = new ArrayList<>(other.comenzi);
        this.vouchere = new ArrayList<>(other.vouchere);
        this.comenzi.addAll(other.comenzi);
    }


    // Getteri
    @Override
    public int getId() {
        return idUser;
    }

    public ArrayList<Voucher> getVouchere() {
        return vouchere;
    }

    // Alte metode
    public void adaugaVoucher(Voucher voucher) {
        this.vouchere.add(voucher);
    }

    public void adaugaRecenzie(Restaurant restaurant, int rating, String text) {
        Recenzie recenzie = new Recenzie(this, restaurant, rating, text);
        restaurant.adaugaRecenzie(recenzie);
    }

    public void comenziActive() {
        if (comenzi.isEmpty())
            System.out.println("Nu aveti comenzi active.");
        for (Comanda comanda : comenzi) {
            System.out.println(comanda);
        }
    }

    public void adaugaComanda(Comanda comanda) {
        this.comenzi.add(comanda);
    }

    @Override
    public void insert(User user) {
        String sql = "INSERT INTO Useri (id, nume, prenume, adresa, varsta, email) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, user.getId());
            stmt.setString(2, user.nume);
            stmt.setString(3, user.prenume);
            stmt.setString(4, user.adresa);
            stmt.setInt(5, user.varsta);
            stmt.setString(6, user.email);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User cautaDupaID(int id) {
        String sql = "SELECT * FROM Useri WHERE id = ?";
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new User(
                        rs.getString("nume"),
                        rs.getString("prenume"),
                        rs.getString("adresa"),
                        rs.getInt("varsta"),
                        rs.getString("email")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<User> cauta() {
        List<User> lista = new ArrayList<>();
        String sql = "SELECT * FROM Useri";
        try (Connection conn = Database.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                User user = new User(
                        rs.getString("nume"),
                        rs.getString("prenume"),
                        rs.getString("adresa"),
                        rs.getInt("varsta"),
                        rs.getString("email")
                );
                lista.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    @Override
    public void update(User user) {
        String sql = "UPDATE Useri SET nume=?, prenume=?, adresa=?, varsta=?, email=? WHERE id=?";
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, user.nume);
            stmt.setString(2, user.prenume);
            stmt.setString(3, user.adresa);
            stmt.setInt(4, user.varsta);
            stmt.setString(5, user.email);
            stmt.setInt(6, user.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM Useri WHERE id=?";
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

