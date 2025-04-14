import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Service {

    public void creeazaComanda(User user, ArrayList<Restaurant> restaurante, Scanner scanner) throws InterruptedException {
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
            Comanda comanda = new Comanda(user, alegereRestaurant, new Date().toString());

            productLoop:
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

                while (true) {
                    System.out.println("Doriti sa mai adaugati alte produse? \n1. DA \n2. NU, finalizeaza comanda");
                    int inputAlteProduse = scanner.nextInt();

                    switch (inputAlteProduse) {
                        case 1:
                            break;
                        case 2:
                            break productLoop;
                        default:
                            System.out.println("Optiune invalida. Reincercati.");
                            continue;
                    }
                    break;
                }
            }

            while (true) {
                System.out.println("Doriti sa adaugati un voucher? \n1. DA \n2. NU");
                int inputVoucher = scanner.nextInt();

                switch (inputVoucher) {
                    case 1:
                        if (!user.getVouchere().isEmpty()) {
                            System.out.println("Vouchere disponibile:");
                            for (int i = 0; i < user.getVouchere().size(); i++) {
                                if (user.getVouchere().get(i).esteValid() && !user.getVouchere().get(i).getFolosit()) {
                                    System.out.println(i + 1 + ". " + user.getVouchere().get(i));
                                }
                            }
                            System.out.println("Selectati voucherul dorit (sau -1 pentru a nu aplica niciun voucher):");
                            int voucherIndex = scanner.nextInt();
                            if (voucherIndex >= 1 && voucherIndex <= user.getVouchere().size()) {
                                comanda.aplicaVoucher(user.getVouchere().get(voucherIndex - 1));
                                System.out.println("Ati selectat " + user.getVouchere().get(voucherIndex - 1));
                            }
                        } else {
                            System.out.println("Nu aveti vouchere disponibile.");
                        }
                        break;
                    case 2:
                        break;
                    default:
                        System.out.println("Optiune invalida.");
                        continue;
                }

                break;
            }

            user.adaugaComanda(comanda);
            System.out.println("Comanda a fost plasata cu succes!");
            System.out.println("Detalii comanda: \n" + comanda);
            System.out.println("Pret total: " + comanda.calculeazaCostTotal());
            Thread.sleep(2000);
            System.out.println("Comanda dumneavoastra a fost preluata.");
            comanda.setStatusComanda(StatusComanda.PRELUATA);
            Thread.sleep(2000);
            System.out.println("Comanda dumneavoastra este in preparare.");
            comanda.setStatusComanda(StatusComanda.IN_PREPARARE);
            Thread.sleep(2000);
            System.out.println("Comanda dumneavoastra este in livrare.");
            comanda.setStatusComanda(StatusComanda.IN_LIVRARE);
            Thread.sleep(2000);
            System.out.println("Comanda dumneavoastra a fost livrata.");
            comanda.setStatusComanda(StatusComanda.LIVRATA);

            boolean recenzieFinalizata = false;

            while (!recenzieFinalizata) {
                System.out.println("Doriti sa adaugati o recenzie?\n1. DA\n2. NU");
                int inputRecenzie = scanner.nextInt();

                switch (inputRecenzie) {
                    case 1:
                        while (true) {
                            System.out.println("Adaugati rating-ul: ");
                            int rating = scanner.nextInt();

                            if (rating < 1 || rating > 5) {
                                System.out.println("Rating invalid. Introduceti un numar intre 1 si 5.");
                                continue;
                            }

                            System.out.println("Adaugati recenzia (text): ");
                            scanner.nextLine();
                            String text = scanner.nextLine();

                            user.adaugaRecenzie(alegereRestaurant, rating, text);
                            System.out.println("Recenzia a fost adaugata cu succes!");
                            recenzieFinalizata = true;
                            break;
                        }
                        break;
                    case 2:
                        recenzieFinalizata = true;
                        break;
                    default:
                        System.out.println("Optiune invalida.");
                }
            }
        }
    }


    public void afiseazaMeniuPrincipal() {
        System.out.println("1. Creare comanda");
        System.out.println("2. Vizualizare comenzi active");
        System.out.println("3. Iesire");
    }

    private static ArrayList<Restaurant> initializeRestaurants() {
        Restaurant restaurant1 = new Restaurant("Pizzeria Napoli", "Strada Florilor 12", "Italiana");
        Restaurant restaurant2 = new Restaurant("Sushi Bar", "Bd. Unirii 20", "Japoneza");

        // Creare produse
        Produs produs1 = new Produs("Pizza Margherita", "Pizza cu mozzarella si rosii", 30);
        Produs produs2 = new Produs("Burger Classic", "Burger cu vita si cartofi", 25);
        Produs produs3 = new Produs("Sushi Set", "Sushi variat", 50);

        restaurant1.adaugaProdusInMeniu(produs1);
        restaurant1.adaugaProdusInMeniu(produs2);

        restaurant2.adaugaProdusInMeniu(produs3);

        // Lista restaurante
        ArrayList<Restaurant> restaurante = new ArrayList<>();
        restaurante.add(restaurant1);
        restaurante.add(restaurant2);
        return restaurante;
    }

    public void initializare() throws InterruptedException {
        ArrayList<Restaurant> restaurante = initializeRestaurants();

        // Creare utilizator
        User user1 = new User("Popescu", "Ion", "Bucuresti", 25, "ion.popescu@email.com");

        // Creare voucher
        Voucher voucher1 = new Voucher("X123F5G", 25, LocalDate.of(2025, 10, 30));
        user1.adaugaVoucher(voucher1);

        System.out.println("Bine ai venit!");
        
        // Testare metoda creeazaComanda
        Scanner scanner = new Scanner(System.in);
        while (true) {
            afiseazaMeniuPrincipal();
            int optiune = scanner.nextInt();
            switch (optiune) {
                case 1:
                    creeazaComanda(user1, restaurante, scanner);
                    break;
                case 2:
                    user1.comenziActive();
                    break;
                case 3:
                    System.out.println("La revedere!");
                    return;
                default:
                    System.out.println("Optiune invalida.");
                    break;
            }
        }
    }

}
