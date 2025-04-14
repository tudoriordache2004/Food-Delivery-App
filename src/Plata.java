public class Plata {
    private double sumaDePlata;
    private MetodaPlata metodaPlata;

    // Constructor fără parametri
    public Plata() {
        this.sumaDePlata = 0.0;
        this.metodaPlata = null;
    }

    // Constructor cu parametri
    public Plata(double sumaDePlata, MetodaPlata metodaPlata) {
        this.sumaDePlata = sumaDePlata;
        this.metodaPlata = metodaPlata;
    }

    // Constructor de copiere
    public Plata(Plata other) {
        this.sumaDePlata = other.sumaDePlata;
        this.metodaPlata = other.metodaPlata;
    }

    // Getteri
    public double getSumaDePlata() {
        return sumaDePlata;
    }

    public MetodaPlata getMetodaPlata() {
        return metodaPlata;
    }

    // Setteri
    public void setSumaDePlata(double sumaDePlata) {
        this.sumaDePlata = sumaDePlata;
    }

    public void setMetodaPlata(MetodaPlata metodaPlata) {
        this.metodaPlata = metodaPlata;
    }


}
