import java.time.LocalDate;
import java.util.ArrayList;

public class Comanda {
    private static int nrComenzi;
    private int idComanda;
    private User user;
    private Restaurant restaurant;
    private Voucher voucher;
    private ArrayList<Produs> listaProduse;
    private String dataComenzii;
    private StatusComanda status;

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
        this.status = StatusComanda.IN_ASTEPTARE;
        this.dataComenzii = "";
    }

    // Constructor parametrizat
    public Comanda(User user, Restaurant restaurant, String dataComenzii) {
        this.user = user;
        this.restaurant = restaurant;
        this.listaProduse = new ArrayList<>();
        this.status = StatusComanda.IN_ASTEPTARE;
        this.dataComenzii = dataComenzii;
    }

    // Constructor de copiere
    public Comanda(Comanda other) {
        this.idComanda = other.idComanda;
        this.user = other.user;
        this.restaurant = other.restaurant;
        this.listaProduse = new ArrayList<>(other.listaProduse);
        this.status = other.status;
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

    public String getDataComenzii() {
        return dataComenzii;
    }

    public static int getNrComenzi() {
        return nrComenzi;
    }

    public StatusComanda getStatus() {
        return status;
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

    public void setStatusComanda(StatusComanda status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return  restaurant +
                ", listaProduse=" + listaProduse + '\n' +
                "dataComenzii='" + dataComenzii + '\'' +
                ", status=" + status;
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
            return Math.max(costTotal - voucher.getValoareReducere(), 0);
        }
        return costTotal;
    }

    public void aplicaVoucher(Voucher voucher) {
        if (voucher.esteValid()) {
            this.voucher = voucher;
            voucher.setFolosit(true);
        } else {
            System.out.println("Voucher invalid sau expirat.");
        }
    }

}

