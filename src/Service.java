import java.time.LocalDate;
import java.util.*;

public class Service {

    public void afiseazaMeniuPrincipal() {
        System.out.println("1. Creare comanda");
        System.out.println("2. Vizualizare comenzi active");
        System.out.println("3. Iesire");
    }

    private static ArrayList<Restaurant> initializeRestaurants() {
        Restaurant restaurant1 = new Restaurant("Pizzeria Napoli", "Strada Florilor 12", "Italiana");
        Restaurant restaurant2 = new Restaurant("Sushi Bar", "Bd. Unirii 20", "Japoneza");
        Restaurant restaurant3 = new Restaurant("Bistro Francez", "Calea Victoriei 155", "Franceza");
        Restaurant restaurant4 = new Restaurant("Taverna Greceasca", "Strada Lipscani 30", "Greaca");
        Restaurant restaurant5 = new Restaurant("Casa Romaneasca", "Soseaua Kiseleff 5", "Romaneasca");
        Restaurant restaurant6 = new Restaurant("Mexican Grill", "Bulevardul Magheru 42", "Mexicana");

        Produs produs1 = new Produs("Pizza Margherita", "Pizza cu mozzarella si rosii", 30);
        Produs produs2 = new Produs("Burger Classic", "Burger cu vita si cartofi", 25);
        Produs produs3 = new Produs("Sushi Set", "Sushi variat", 50);
        Produs produs4 = new Produs("Supa de ceapa gratinata", "Supa de ceapa cu crutoane si branza", 18);
        Produs produs5 = new Produs("Confit de rata", "Rata confiata cu cartofi sotati", 45);
        Produs produs6 = new Produs("Creme brulee", "Desert clasic cu vanilie si caramel crocant", 15);
        Produs produs7 = new Produs("Salata greceasca", "Salata cu rosii, castraveti, masline si feta", 22);
        Produs produs8 = new Produs("Souvlaki de pui", "Frigarui de pui marinate, servite cu tzatziki", 35);
        Produs produs9 = new Produs("Baklava", "Desert dulce cu nuci si miere", 16);
        Produs produs10 = new Produs("Ciorba de burta", "Supa acra de burta de vita", 20);
        Produs produs11 = new Produs("Mici", "Carnati mici din carne tocata, la gratar (4 buc)", 18);
        Produs produs12 = new Produs("Papanasi", "Gogosi cu branza dulce, smantana si dulceata", 17);
        Produs produs13 = new Produs("Tacos cu carne", "Trei tacos cu carne de vita sau pui, salsa si guacamole", 28);
        Produs produs14 = new Produs("Quesadilla cu branza", "Tortilla umpluta cu branza si legume", 24);
        Produs produs15 = new Produs("Churros cu ciocolata", "Patiserie prajita cu zahar si sos de ciocolata", 14);
        Produs produs16 = new Produs("Paste Carbonara", "Paste cu ou, bacon si parmezan", 35);
        Produs produs17 = new Produs("Sashimi Mix", "Selectie de sashimi proaspat", 60);

        restaurant1.adaugaProdusInMeniu(produs1);
        restaurant1.adaugaProdusInMeniu(produs2);
        restaurant1.adaugaProdusInMeniu(produs16);

        restaurant2.adaugaProdusInMeniu(produs3);
        restaurant2.adaugaProdusInMeniu(produs17);

        restaurant3.adaugaProdusInMeniu(produs4);
        restaurant3.adaugaProdusInMeniu(produs5);
        restaurant3.adaugaProdusInMeniu(produs6);

        restaurant4.adaugaProdusInMeniu(produs7);
        restaurant4.adaugaProdusInMeniu(produs8);
        restaurant4.adaugaProdusInMeniu(produs9);

        restaurant5.adaugaProdusInMeniu(produs10);
        restaurant5.adaugaProdusInMeniu(produs11);
        restaurant5.adaugaProdusInMeniu(produs12);

        restaurant6.adaugaProdusInMeniu(produs13);
        restaurant6.adaugaProdusInMeniu(produs14);
        restaurant6.adaugaProdusInMeniu(produs15);

        ArrayList<Restaurant> restaurante = new ArrayList<>();
        restaurante.add(restaurant1);
        restaurante.add(restaurant2);
        restaurante.add(restaurant3);
        restaurante.add(restaurant4);
        restaurante.add(restaurant5);
        restaurante.add(restaurant6);
        return restaurante;
    }

    public void initializare() throws InterruptedException, InputMismatchException {
        ArrayList<Restaurant> restaurante = initializeRestaurants();

        User user1 = new User("Popescu", "Ion", "Bucuresti", 25, "ion.popescu@gmail.com");

        Voucher voucher1 = new Voucher("X123F5G", 25, LocalDate.of(2025, 10, 30));
        Voucher voucher2 = new Voucher("Y334FVG", 30, LocalDate.of(2025, 9, 22));


        user1.adaugaVoucher(voucher1);
        user1.adaugaVoucher(voucher2);

        Set<Rider> riders = new HashSet<>();

        Rider rider1 = new Rider("Al Shabab", "Makun", "Bucuresti", 39, "makunals@gmail.com");
        Rider rider2 = new Rider("Ahmed", "Khan", "Bucuresti", 31, "ahmed.khan@gmail.com");
        Rider rider3 = new Rider("Bilal", "Hussain", "Bucuresti", 29, "bilal.hussain@gmail.com");
        Rider rider4 = new Rider("Zubair", "Malik", "Bucuresti", 33, "zubair.malik@gmail.com");
        Rider rider5 = new Rider("Sajid", "Ali", "Bucuresti", 27, "sajid.ali@gmail.com");
        Rider rider6 = new Rider("Imran", "Qureshi", "Bucuresti", 35, "imran.qureshi@gmail.com");


        System.out.println("Bine ai venit!");
        
        Scanner scanner = new Scanner(System.in);
        while (true) {
            afiseazaMeniuPrincipal();
            try {
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
            } catch (InputMismatchException e) {
                System.out.println("Introduceti un numar valid.");
                scanner.nextLine();
            }
        }
    }

    public void creeazaComanda(User user, ArrayList<Restaurant> restaurante, Scanner scanner) throws InterruptedException, InputMismatchException {
        while (true) {
            System.out.println("Selectati restaurantul dorit. In caz de anulare, selectati 0:");

            for (Restaurant res : restaurante) {
                System.out.println(res.toString());
            }

            int inputRestaurant = -1;
            while (true) {
                try {
                    inputRestaurant = scanner.nextInt();
                    break;
                } catch (InputMismatchException e) {
                    System.out.println("Introduceti un numar valid.");
                    scanner.nextLine();
                }
            }

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

                int inputProdus = -1;
                while (true) {
                    try {
                        inputProdus = scanner.nextInt();
                        break;
                    } catch (InputMismatchException e) {
                        System.out.println("Introduceti un numar valid.");
                        scanner.nextLine();
                    }
                }

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
                int cantitate = -1;
                while (true) {
                    try {
                        cantitate = scanner.nextInt();
                        break;
                    } catch (InputMismatchException e) {
                        System.out.println("Introduceti un numar valid.");
                        scanner.nextLine();
                    }
                }

                for (int i = 0; i < cantitate; i++) {
                    comanda.adaugaProdus(produsAles);
                }

                System.out.println("Ati adaugat " + cantitate + " x " + produsAles.getNumeProdus());

                while (true) {
                    System.out.println("Doriti sa mai adaugati alte produse? \n1. DA \n2. NU, finalizeaza comanda");
                    int inputAlteProduse = -1;
                    while (true) {
                        try {
                            inputAlteProduse = scanner.nextInt();
                            break;
                        } catch (InputMismatchException e) {
                            System.out.println("Introduceti un numar valid.");
                            scanner.nextLine();
                        }
                    }

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
                int inputVoucher = -1;
                while (true) {
                    try {
                        inputVoucher = scanner.nextInt();
                        break;
                    } catch (InputMismatchException e) {
                        System.out.println("Introduceti un numar valid.");
                        scanner.nextLine();
                    }
                }

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
                            int voucherIndex = -1;
                            while (true) {
                                try {
                                    voucherIndex = scanner.nextInt();
                                    break;
                                } catch (InputMismatchException e) {
                                    System.out.println("Introduceti un numar valid.");
                                    scanner.nextLine();
                                }
                            }

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
                int inputRecenzie = -1;
                while (true) {
                    try {
                        inputRecenzie = scanner.nextInt();
                        break;
                    } catch (InputMismatchException e) {
                        System.out.println("Input invalid. Introduceti un numar valid.");
                        scanner.nextLine();
                    }
                }

                switch (inputRecenzie) {
                    case 1:
                        while (true) {
                            System.out.println("Adaugati rating-ul: ");
                            int rating = -1;
                            while (true) {
                                try {
                                    rating = scanner.nextInt();
                                    break;
                                } catch (InputMismatchException e) {
                                    System.out.println("Input invalid. Introduceti un numar valid.");
                                    scanner.nextLine();
                                }
                            }

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

}
