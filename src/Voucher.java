import java.time.LocalDate;

public class Voucher {

    // Atribute
    private String codVoucher;
    private double valoareReducere;
    private LocalDate dataExpirare;
    private boolean folosit;

    // Constructor fara parametri
    public Voucher() {
        this.codVoucher = "";
        this.valoareReducere = 0.0;
        this.dataExpirare = LocalDate.now();
        this.folosit = false;
    }

    // Constructor parametrizat
    public Voucher(String codVoucher, double valoareReducere, LocalDate dataExpirare) {
        this.codVoucher = codVoucher;
        this.valoareReducere = valoareReducere;
        this.dataExpirare = dataExpirare;
        this.folosit = false;
    }

    // Constructor de copiere
    public Voucher(Voucher other) {
        this.codVoucher = other.codVoucher;
        this.valoareReducere = other.valoareReducere;
        this.dataExpirare = other.dataExpirare;
    }

    // Getteri
    public String getCodVoucher() {
        return codVoucher;
    }

    public double getValoareReducere() {
        return valoareReducere;
    }

    public LocalDate getDataExpirare() {
        return dataExpirare;
    }

    public boolean getFolosit() {
        return folosit;
    }
    
    // Setteri
    public void setCodVoucher(String codVoucher) {
        this.codVoucher = codVoucher;
    }

    public void setValoareReducere(double valoareReducere) {
        if (valoareReducere < 0) {
            throw new IllegalArgumentException("Valoarea reducerii nu poate fi negativa.");
        }
        this.valoareReducere = valoareReducere;
    }

    public void setFolosit(boolean folosit) {
        this.folosit = folosit;
    }

    public void setDataExpirare(LocalDate dataExpirare) {
        this.dataExpirare = dataExpirare;
    }

    public boolean esteValid() {
        return LocalDate.now().isBefore(dataExpirare);
    }


    @Override
    public String toString() {
        return "Voucher " + codVoucher +
                ", valoareReducere = " + valoareReducere +
                ", dataExpirare = " + dataExpirare;
    }


}