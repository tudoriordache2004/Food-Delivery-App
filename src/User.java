import java.lang.reflect.Array;
import java.util.*;

public class User extends Persoana {
    private static int nrUseri;
    private int idUser;
    private ArrayList<Comanda> comenzi;

    // Bloc static
    static {
        nrUseri = 0;
        System.out.println("Initializare, numar de useri = 0");
    }

    // Bloc nestatic
    {
        idUser = ++nrUseri;
    }

    // Constructor fara parametri
    User() {
        super();
        comenzi = new ArrayList<Comanda>();
    }

    // Constructor parametrizat
    User(String nume, String prenume, String adresa, int varsta, String email) {
        super(nume, prenume, adresa, varsta, email);
        comenzi = new ArrayList<Comanda>();
    }

    // Constructor de copiere
    User(User other) {
        super(other.nume, other.prenume, other.adresa, other.varsta, other.email);
        this.idUser = other.idUser;

        this.comenzi = new ArrayList<Comanda>();
        this.comenzi.addAll(other.comenzi);
    }


    // Getteri
    @Override
    public int getId() {
        return idUser;
    }

    // Alte metode
    public void creeazaComanda(ArrayList<Restaurant> restaurante, Scanner scanner) {
        while (true) {
            System.out.println("Selectati restaurantul dorit. In caz de anulare, selectati 0:");

            for (Restaurant res : restaurante) {
                System.out.println(res.toString());
            }

            int inputRestaurant = scanner.nextInt();
            if (inputRestaurant == 0) return;

            Restaurant alegereRestaurant = null;
            for (Restaurant res : restaurante) {
                if (inputRestaurant == res.getIdRestaurant()) {
                    alegereRestaurant = res;
                    break;
                }
            }

            if (alegereRestaurant == null) {
                System.out.println("Nu exista restaurantul cu acest ID, incercati din nou.");
                continue;
            }

            System.out.println("Ati ales: " + alegereRestaurant.getNume());
            Comanda comanda = new Comanda(this, alegereRestaurant, new Date().toString());

            while (true) {
                System.out.println("Alegeti produsele. In caz de anulare, selectati 0:");
                alegereRestaurant.afiseazaMeniu();

                int inputProdus = scanner.nextInt();
                if (inputProdus == 0) break;

                Produs produsAles = null;
                for (Produs prod : alegereRestaurant.getMeniu()) {
                    if (inputProdus == prod.getIdProdus()) {
                        produsAles = prod;
                        break;
                    }
                }

                if (produsAles == null) {
                    System.out.println("Nu exista produsul cu acest ID, incercati din nou.");
                    continue;
                }

                System.out.println("Introduceti cantitatea:");
                int cantitate = scanner.nextInt();

                for (int i = 0; i < cantitate; i++) {
                    comanda.adaugaProdus(produsAles);
                }

                System.out.println("Ati adaugat " + cantitate + " x " + produsAles.getNumeProdus());

                System.out.println("Doriti sa mai adaugati alte produse? \n1. DA \n2. NU, finalizeaza comanda");
                int inputAlteProduse = scanner.nextInt();

                if (inputAlteProduse == 2) {
                    this.comenzi.add(comanda);
                    System.out.println("Comanda a fost plasata cu succes!");
                    System.out.println("Detalii comanda: \n" + comanda);
                    System.out.println("Pret total: " + comanda.calculeazaCostTotal());
                    return;
                }
            }
        }
    }

    public void istoricComenzi() {
        for (Comanda comanda : comenzi) {
            System.out.println(comanda.toString());
        }
    }

}

