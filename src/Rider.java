import java.util.*;

public class Rider extends Persoana implements Comparable{
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

    public void adaugaComanda(Comanda comanda) {
        livrari.add(comanda);
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
}
