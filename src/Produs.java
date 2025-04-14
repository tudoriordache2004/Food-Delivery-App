public class Produs {
    private static int nrProduse;
    private int idProdus;
    private String numeProdus;
    private String descriere;
    private int pret;

    // Bloc static
    static {
        nrProduse = 0;
        System.out.println("Initializare, numar de produse = 0");
    }

    // Bloc nestatic
    {
        idProdus = ++nrProduse;
    }

    // Constructor fara parametri
    public Produs() {
        this.numeProdus = "";
        this.descriere = "";
        this.pret = 0;
    }

    // Constructor parametrizat
    public Produs(String numeProdus, String descriere, int pret) {
        this.numeProdus = numeProdus;
        this.descriere = descriere;
        this.pret = pret;
    }

    // Constructor de copiere
    public Produs(Produs other) {
        this.numeProdus = other.numeProdus;
        this.descriere = other.descriere;
        this.pret = other.pret;
        this.idProdus = other.idProdus;
    }

    // Getteri
    public int getIdProdus() {
        return idProdus;
    }

    public int getPret() {
        return pret;
    }

    public String getDescriere() {
        return descriere;
    }

    public String getNumeProdus() {
        return numeProdus;
    }

    public static int getNrProduse() {
        return nrProduse;
    }

    // Setteri
    public void setDescriere(String descriere) {
        this.descriere = descriere;
    }

    public void setNumeProdus(String numeProdus) {
        this.numeProdus = numeProdus;
    }

    public void setPret(int pret) {
        this.pret = pret;
    }

    @Override
    public String toString() {
        return idProdus + ". " + numeProdus +
                ", Descriere: " + descriere +
                ", Pret: " + pret + " RON";
    }

    public void afiseazaDetalii() {
        System.out.println(toString());
    }

    public void modificaNume(String nume) {
        numeProdus = nume;
    }

    public void modificaPret(int pret) {
        this.pret = pret;
    }

    public void modificaDescriere(String descriere) {
        this.descriere = descriere;
    }

}
