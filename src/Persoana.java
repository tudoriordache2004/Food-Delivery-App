public abstract class Persoana {
    protected String nume;
    protected String prenume;
    protected String adresa;
    protected int varsta;
    protected String email;

    // Constructor fara parametri
    public Persoana() {
        this.nume = "";
        this.prenume = "";
        this.adresa = "";
        this.varsta = 0;
        this.email = "";
    }

    // Constructor parametrizat
    public Persoana(String nume, String prenume, String adresa, int varsta, String email) {
        this.nume = nume;
        this.prenume = prenume;
        this.adresa = adresa;
        this.varsta = varsta;
        this.email = email;
    }

    // Getteri
    public String getNume() {
        return nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public String getAdresa() {
        return adresa;
    }

    public int getVarsta() {
        return varsta;
    }

    public String getEmail() {
        return email;
    }

    public abstract int getId();

    // Setteri
    public void setNume(String nume) {
        this.nume = nume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public void setVarsta(int varsta) {
        if (varsta < 0) {
            throw new IllegalArgumentException("Varsta nu poate fi negativa.");
        }
        this.varsta = varsta;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
