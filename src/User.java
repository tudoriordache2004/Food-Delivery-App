import java.util.ArrayList;

public class User extends Persoana {
    private int idUser;
    private String parola;
    private ArrayList<Comanda> comenzi;
    private ArrayList<Voucher> vouchere;

    // Constructor fără parametri
    public User() {
        super();
        this.parola = "";
        this.comenzi = new ArrayList<>();
        this.vouchere = new ArrayList<>();
    }

    @Override
    public int getId() {
        return 0;
    }

    // Constructor parametrizat
    public User(String nume, String prenume, String adresa, int varsta, String email, String parola) {
        super(nume, prenume, adresa, varsta, email);
        this.parola = parola;
        this.comenzi = new ArrayList<>();
        this.vouchere = new ArrayList<>();
    }

    // Getteri și setteri
    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getParola() {
        return parola;
    }

    public void setParola(String parola) {
        this.parola = parola;
    }

    public ArrayList<Comanda> getComenzi() {
        return comenzi;
    }

    public void setComenzi(ArrayList<Comanda> comenzi) {
        this.comenzi = comenzi;
    }

    public ArrayList<Voucher> getVouchere() {
        return vouchere;
    }

    public void setVouchere(ArrayList<Voucher> vouchere) {
        this.vouchere = vouchere;
    }
}