import java.io.ObjectInputFilter;
import java.lang.reflect.Array;
import java.util.*;

public class User extends Persoana {
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
}

