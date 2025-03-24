import java.time.LocalDate;
import java.util.ArrayList;

public class Comanda {
    private static int nrComenzi;
    private int idComanda;
    private User user;
    private Restaurant restaurant;
    private Voucher voucher;
    private ArrayList<Produs> listaProduse;
    boolean comandaLivrata;
    private String dataComenzii;

    // Bloc static
    static {
        nrComenzi = 0;
    }

    // Bloc nestatic
    {
        this.idComanda = ++nrComenzi;
    }

    // Constructor fara parametri
    public Comanda() {
        this.user = null;
        this.restaurant = null;
        this.listaProduse = new ArrayList<>();
        this.comandaLivrata = false;
        this.dataComenzii = "";
    }

    // Constructor parametrizat
    public Comanda(User user, Restaurant restaurant, String dataComenzii) {
        this.user = user;
        this.restaurant = restaurant;
        this.listaProduse = new ArrayList<>();
        this.comandaLivrata = false;
        this.dataComenzii = dataComenzii;
    }

    // Constructor de copiere
    public Comanda(Comanda other) {
        this.idComanda = other.idComanda;
        this.user = other.user;
        this.restaurant = other.restaurant;
        this.listaProduse = new ArrayList<>(other.listaProduse);
        this.comandaLivrata = other.comandaLivrata;
        this.dataComenzii = other.dataComenzii;
    }

    // Getteri
    public int getIdComanda() {
        return idComanda;
    }

    public User getUser() {
        return user;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public ArrayList<Produs> getListaProduse() {
        return listaProduse;
    }

    public boolean getcomandaLivrata() {
        return comandaLivrata;
    }

    public String getDataComenzii() {
        return dataComenzii;
    }

    public static int getNrComenzi() {
        return nrComenzi;
    }

    // Setteri
    public void setUser(User user) {
        this.user = user;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public void setListaProduse(ArrayList<Produs> listaProduse) {
        this.listaProduse = listaProduse;
    }


    public void setDataComenzii(String dataComenzii) {
        this.dataComenzii = dataComenzii;
    }

    @Override
    public String toString() {
        return "Restaurant: " + (restaurant != null ? restaurant.getNume() : "N/A") + '\n' +
                "Produse: " + listaProduse.toString() +
                ", Status: " + (comandaLivrata ? " livrata" : " nelivrata") +
                ", Data: " + dataComenzii;
    }

    public void afiseazaDetalii() {
        System.out.println(this);
    }

    public void adaugaProdus(Produs produs) {
        listaProduse.add(produs);
    }

    public double calculeazaCostTotal() {
        double costTotal = 0;
        for (Produs produs : listaProduse) {
            costTotal += produs.getPret();
        }
        if (voucher != null) {
            costTotal -= voucher.getValoareReducere();
        }
        if (costTotal <= 0) {
            return 0;
        }
        return costTotal;
    }

    public void aplicaVoucher(Voucher voucher) {
        if (voucher != null && voucher.getDataExpirare().isAfter(LocalDate.now())) {
            this.voucher = voucher;
        } else {
            System.out.println("Voucher invalid sau expirat.");
        }
    }

}

