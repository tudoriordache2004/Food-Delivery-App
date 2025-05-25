import java.util.*;
import java.sql.*;


public class Rider extends Persoana implements Comparable, GenericService<Rider> {
    private static int nrRideri;
    private int idRider;
    private ArrayList<Comanda> livrari;

    // Bloc static
    static {
        nrRideri = 0;
        System.out.println("Initializare, numar de rideri = 0");
    }

    // Bloc nestatic
    {
        idRider = ++nrRideri;
    }

    // Constructor fara parametri
    Rider() {
        super();
        livrari = new ArrayList<Comanda>();
    }

    // Constructor parametrizat
    Rider(String nume, String prenume, String adresa, int varsta, String email) {
        super(nume, prenume, adresa, varsta, email);
        livrari = new ArrayList<Comanda>();
    }

    // Constructor de copiere
    Rider(Rider other) {
        this.nume = other.nume;
        this.prenume = other.prenume;
        this.adresa = other.adresa;
        this.varsta = other.varsta;
        this.email = other.email;
        this.idRider = other.idRider;
        this.livrari = new ArrayList<Comanda>();
        this.livrari.addAll(other.livrari);
    }

    // Getteri
    @Override
    public int getId() {
        return idRider;
    }

    public void adaugaComanda(Comanda comanda)
    {
        livrari.add(comanda);
        comanda.setStatusComanda(StatusComanda.IN_LIVRARE);
    }

    public void livreazaComanda(Comanda comanda) {
        comanda.setStatusComanda(StatusComanda.LIVRATA);
    }

    public int compareTo(Object obj) {
        Rider other = (Rider)obj;
        if(varsta < other.varsta) return -1;
        if(varsta > other.varsta) return 1;
        return 0;
    }

    @Override
    public String toString() {
        return nume + " " + prenume;
    }

    @Override
    public void insert(Rider rider) {
        String sql = "INSERT INTO Rideri (id, nume, prenume, adresa, varsta, email) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, rider.getId());
            stmt.setString(2, rider.nume);
            stmt.setString(3, rider.prenume);
            stmt.setString(4, rider.adresa);
            stmt.setInt(5, rider.varsta);
            stmt.setString(6, rider.email);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Rider cautaDupaID(int id) {
        String sql = "SELECT * FROM Rideri WHERE id = ?";
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Rider(
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
    public List<Rider> cauta() {
        List<Rider> lista = new ArrayList<>();
        String sql = "SELECT * FROM Rideri";
        try (Connection conn = Database.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Rider rider = new Rider(
                        rs.getString("nume"),
                        rs.getString("prenume"),
                        rs.getString("adresa"),
                        rs.getInt("varsta"),
                        rs.getString("email")
                );
                lista.add(rider);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    @Override
    public void update(Rider rider) {
        String sql = "UPDATE Rideri SET nume=?, prenume=?, adresa=?, varsta=?, email=? WHERE id=?";
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, rider.nume);
            stmt.setString(2, rider.prenume);
            stmt.setString(3, rider.adresa);
            stmt.setInt(4, rider.varsta);
            stmt.setString(5, rider.email);
            stmt.setInt(6, rider.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM Rideri WHERE id=?";
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

