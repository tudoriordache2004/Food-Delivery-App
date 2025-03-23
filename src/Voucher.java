import java.time.LocalDate;

public class Voucher {

    // Atribute
    private String codVoucher;
    private double valoareReducere;
    private LocalDate dataExpirare;
    private static int numarVouchere;
    private int idVoucher;

    // Bloc static
    static {
        numarVouchere = 0;
        System.out.println("Initializare, numar de vouchere = 0");
    }

    // Bloc nestatic
    {
        idVoucher = ++numarVouchere;
    }

    // Constructor fara parametri
    public Voucher() {
        this.codVoucher = "";
        this.valoareReducere = 0.0;
        this.dataExpirare = LocalDate.now();
    }

    // Constructor parametrizat
    public Voucher(String codVoucher, double valoareReducere, LocalDate dataExpirare) {
        this.codVoucher = codVoucher;
        this.valoareReducere = valoareReducere;
        this.dataExpirare = dataExpirare;
    }

    // Constructor de copiere
    public Voucher(Voucher other) {
        this.codVoucher = other.codVoucher;
        this.valoareReducere = other.valoareReducere;
        this.dataExpirare = other.dataExpirare;
        this.idVoucher = other.idVoucher;
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

    public int getIdVoucher() {
        return idVoucher;
    }

    // Setteri
    public void setCodVoucher(String codVoucher) {
        this.codVoucher = codVoucher;
    }

    public void setValoareReducere(double valoareReducere) {
        this.valoareReducere = valoareReducere;
    }

    public void setDataExpirare(LocalDate dataExpirare) {
        this.dataExpirare = dataExpirare;
    }

}