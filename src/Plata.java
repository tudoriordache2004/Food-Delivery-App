public class Plata {
    private static int nrPlati;
    private int idPlata;
    private double sumaDePlata;
    private String metodaPlata;

    // Bloc static pentru inițializarea numărului de plăți
    static {
        nrPlati = 0;
    }

    // Bloc nestatic pentru generarea ID-ului plății
    {
        this.idPlata = ++nrPlati;
    }

    // Constructor fără parametri
    public Plata() {
        this.sumaDePlata = 0.0;
        this.metodaPlata = "Necunoscut";
    }

    // Constructor cu parametri
    public Plata(double sumaDePlata, String metodaPlata, String statusPlata) {
        this.sumaDePlata = sumaDePlata;
        this.metodaPlata = metodaPlata;
    }

    // Constructor de copiere
    public Plata(Plata other) {
        this.sumaDePlata = other.sumaDePlata;
        this.metodaPlata = other.metodaPlata;
    }

    // Getteri
    public int getIdPlata() {
        return idPlata;
    }

    public double getSumaDePlata() {
        return sumaDePlata;
    }

    public String getMetodaPlata() {
        return metodaPlata;
    }


    public static int getNrPlati() {
        return nrPlati;
    }

    // Setteri
    public void setSumaDePlata(double sumaDePlata) {
        this.sumaDePlata = sumaDePlata;
    }

    public void setMetodaPlata(String metodaPlata) {
        this.metodaPlata = metodaPlata;
    }

}
