import java.lang.reflect.Array;
import java.util.ArrayList;

public class Restaurant {
    private static int nrRestaurante;
    private int idRestaurant;
    private String nume;
    private String adresa;
    private String tipMancare;
    private ArrayList<Produs> meniu;
    private double rating;

    // Bloc static
    static {
        nrRestaurante = 0;
    }

    // Bloc nestatic
    {
        this.idRestaurant = ++nrRestaurante;
    }

    // Constructor fara parametri
    public Restaurant() {
        this.nume = "";
        this.adresa = "";
        this.tipMancare = "";
        this.meniu = new ArrayList<Produs>();
        this.rating = 0.00;
    }

    // Constructor parametrizat
    public Restaurant(String nume, String adresa, String tipMancare) {
        this.nume = nume;
        this.adresa = adresa;
        this.tipMancare = tipMancare;
        this.meniu = new ArrayList<>();
        this.rating = 0.00;
    }

    // Constructor de copiere
    Restaurant(Restaurant other) {
        this.nume = other.nume;
        this.adresa = other.adresa;
        this.tipMancare = other.tipMancare;

        this.meniu = new ArrayList<Produs>();
        this.meniu.addAll(other.meniu);
        this.rating = other.rating;
    }

    // Getteri
    public String getNume() {
        return nume;
    }

    public double getRating() {
        return rating;
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
        return idRestaurant + ". " + nume + " - " + adresa + ", tip mancare: " + tipMancare + ". Rating: " + rating;
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

    public void trimiteComanda(Comanda comanda) {
        comanda.setStatusComanda(StatusComanda.IN_LIVRARE);
    }


}


