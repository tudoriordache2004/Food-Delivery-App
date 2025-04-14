import java.util.ArrayList;

public class Restaurant {
    private static int nrRestaurante;
    private int idRestaurant;
    private String nume;
    private String adresa;
    private String tipMancare;
    private ArrayList<Produs> meniu;
    private ArrayList<Recenzie> recenzii;
    private double medieRating;

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
        this.meniu = new ArrayList<>();
        this.recenzii = new ArrayList<>();
        this.medieRating = 0.00;
    }

    // Constructor parametrizat
    public Restaurant(String nume, String adresa, String tipMancare) {
        this.nume = nume;
        this.adresa = adresa;
        this.tipMancare = tipMancare;
        this.recenzii = new ArrayList<>();
        this.meniu = new ArrayList<>();
        this.medieRating = 0.00;
    }

    // Constructor de copiere
    Restaurant(Restaurant other) {
        this.nume = other.nume;
        this.adresa = other.adresa;
        this.tipMancare = other.tipMancare;
        this.recenzii = new ArrayList<>(other.recenzii);
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

    public void trimiteComanda(Comanda comanda, ArrayList<Rider> rideri) {
        comanda.setStatusComanda(StatusComanda.IN_LIVRARE);
        int index = (int) (Math.random() * rideri.size());
        rideri.get(index).adaugaComanda(comanda);
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

}


