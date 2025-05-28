import java.sql.SQLException;
import java.util.*;


public class Menu {
    private UserService userService;
    private LoginService loginService;

    public Menu() {
        this.userService = new UserService();
        this.loginService = new LoginService(userService.getUtilizatori());
    }

    public void afiseazaMeniuPrincipal() {
        System.out.println("1. Creare comanda");
        System.out.println("2. Vizualizare comenzi active");
        System.out.println("3. Create");
        System.out.println("4. Read");
        System.out.println("5. Update");
        System.out.println("6. Delete");
        System.out.println("7. Iesire");
    }

    public void initializare() throws InterruptedException, InputMismatchException, SQLException {
        ArrayList<Restaurant> restaurante = initializeRestaurants();
        ArrayList<Rider> riders = initializeRiders();

        User user1 = new User("Popescu", "Ion", "Bucuresti", 25, "ion.popescu@gmail.com", "parola123");
        User user2 = new User("Ionescu", "Maria", "Cluj", 30, "maria.ionescu@gmail.com", "parola456");
        userService.adaugaUser(user1);
        userService.adaugaUser(user2);

        loginService = new LoginService(userService.getUtilizatori());

        Scanner scanner = new Scanner(System.in);
        User userLogat = null;

        while (userLogat == null) {
            System.out.println("Bine ai venit! Te rugăm să te autentifici.");
            System.out.print("Email: ");
            String email = scanner.nextLine();
            System.out.print("Parola: ");
            String parola = scanner.nextLine();

            userLogat = loginService.login(email, parola);
        }

        System.out.println("Autentificare reușită! Bine ai venit, " + userLogat.getNume() + " " + userLogat.getPrenume() + "!");

        while (true) {
            afiseazaMeniuPrincipal();
            try {
                int optiune = scanner.nextInt();
                switch (optiune) {
                    case 1:
                        creeazaComanda(userLogat, restaurante, riders, scanner);
                        break;
                    case 2:
                        userService.comenziActive(userLogat);
                        break;
                    case 3:
                        create(scanner);
                        break;
                    case 4:
                        read(scanner);
                        break;
                    case 5:
                        update(scanner);
                        break;
                    case 6:
                        delete(scanner);
                        break;
                    case 7:
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

    private static ArrayList<Restaurant> initializeRestaurants() {
        ArrayList<Restaurant> restaurante = new ArrayList<>();

        try {
            Restaurant restaurant1 = new Restaurant("Pizzeria Napoli", "Strada Florilor 12", "Italiana");
            Restaurant restaurant2 = new Restaurant("Sushi Bar", "Bd. Unirii 20", "Japoneza");
            Restaurant restaurant3 = new Restaurant("Bistro Francez", "Calea Victoriei 155", "Franceza");
            Restaurant restaurant4 = new Restaurant("Taverna Greceasca", "Strada Lipscani 30", "Greaca");
            Restaurant restaurant5 = new Restaurant("Casa Romaneasca", "Soseaua Kiseleff 5", "Romaneasca");
            Restaurant restaurant6 = new Restaurant("Mexican Grill", "Bulevardul Magheru 42", "Mexicana");

            restaurant1.insert(restaurant1);
            restaurant2.insert(restaurant2);
            restaurant3.insert(restaurant3);
            restaurant4.insert(restaurant4);
            restaurant5.insert(restaurant5);
            restaurant6.insert(restaurant6);

            restaurante.add(restaurant1);
            restaurante.add(restaurant2);
            restaurante.add(restaurant3);
            restaurante.add(restaurant4);
            restaurante.add(restaurant5);
            restaurante.add(restaurant6);

            Produs produs1 = new Produs("Pizza Margherita", "Pizza cu mozzarella si rosii", 30, restaurant1.getIdRestaurant());
            Produs produs2 = new Produs("Burger Classic", "Burger cu vita si cartofi", 25, restaurant1.getIdRestaurant());
            Produs produs3 = new Produs("Paste Carbonara", "Paste cu ou, bacon si parmezan", 35, restaurant1.getIdRestaurant());
            produs1.insert(produs1);
            produs2.insert(produs2);
            produs3.insert(produs3);
            restaurant1.adaugaProdusInMeniu(produs1);
            restaurant1.adaugaProdusInMeniu(produs2);
            restaurant1.adaugaProdusInMeniu(produs3);

            Produs produs4 = new Produs("Sushi Set", "Sushi variat", 50, restaurant2.getIdRestaurant());
            Produs produs5 = new Produs("Sashimi Mix", "Selectie de sashimi proaspat", 60, restaurant2.getIdRestaurant());
            produs4.insert(produs4);
            produs5.insert(produs5);
            restaurant2.adaugaProdusInMeniu(produs4);
            restaurant2.adaugaProdusInMeniu(produs5);

            Produs produs6 = new Produs("Supa de ceapa gratinata", "Supa de ceapa cu crutoane si branza", 18, restaurant3.getIdRestaurant());
            Produs produs7 = new Produs("Confit de rata", "Rata confiata cu cartofi sotati", 45, restaurant3.getIdRestaurant());
            Produs produs8 = new Produs("Creme brulee", "Desert clasic cu vanilie si caramel crocant", 15, restaurant3.getIdRestaurant());
            produs6.insert(produs6);
            produs7.insert(produs7);
            produs8.insert(produs8);
            restaurant3.adaugaProdusInMeniu(produs6);
            restaurant3.adaugaProdusInMeniu(produs7);
            restaurant3.adaugaProdusInMeniu(produs8);

            Produs produs9 = new Produs("Salata greceasca", "Salata cu rosii, castraveti, masline si feta", 22, restaurant4.getIdRestaurant());
            Produs produs10 = new Produs("Souvlaki de pui", "Frigarui de pui marinate, servite cu tzatziki", 35, restaurant4.getIdRestaurant());
            Produs produs11 = new Produs("Baklava", "Desert dulce cu nuci si miere", 16, restaurant4.getIdRestaurant());
            produs9.insert(produs9);
            produs10.insert(produs10);
            produs11.insert(produs11);
            restaurant4.adaugaProdusInMeniu(produs9);
            restaurant4.adaugaProdusInMeniu(produs10);
            restaurant4.adaugaProdusInMeniu(produs11);

            Produs produs12 = new Produs("Ciorba de burta", "Supa acra de burta de vita", 20, restaurant5.getIdRestaurant());
            Produs produs13 = new Produs("Mici", "Carnati mici din carne tocata, la gratar (4 buc)", 18, restaurant5.getIdRestaurant());
            Produs produs14 = new Produs("Papanasi", "Gogosi cu branza dulce, smantana si dulceata", 17, restaurant5.getIdRestaurant());
            produs12.insert(produs12);
            produs13.insert(produs13);
            produs14.insert(produs14);
            restaurant5.adaugaProdusInMeniu(produs12);
            restaurant5.adaugaProdusInMeniu(produs13);
            restaurant5.adaugaProdusInMeniu(produs14);

            Produs produs15 = new Produs("Tacos cu carne", "Trei tacos cu carne de vita sau pui, salsa si guacamole", 28, restaurant6.getIdRestaurant());
            Produs produs16 = new Produs("Quesadilla cu branza", "Tortilla umpluta cu branza si legume", 24, restaurant6.getIdRestaurant());
            Produs produs17 = new Produs("Churros cu ciocolata", "Patiserie prajita cu zahar si sos de ciocolata", 14, restaurant6.getIdRestaurant());
            produs15.insert(produs15);
            produs16.insert(produs16);
            produs17.insert(produs17);
            restaurant6.adaugaProdusInMeniu(produs15);
            restaurant6.adaugaProdusInMeniu(produs16);
            restaurant6.adaugaProdusInMeniu(produs17);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return restaurante;
    }

    private static ArrayList<Rider> initializeRiders() {
        ArrayList<Rider> riders = new ArrayList<>();

        Rider rider1 = new Rider("Al Shabab", "Makun", "Bucuresti", 39, "makunals@gmail.com");
        Rider rider2 = new Rider("Ahmed", "Khan", "Bucuresti", 31, "ahmed.khan@gmail.com");
        Rider rider3 = new Rider("Bilal", "Hussain", "Bucuresti", 29, "bilal.hussain@gmail.com");
        Rider rider4 = new Rider("Zubair", "Malik", "Bucuresti", 33, "zubair.malik@gmail.com");
        Rider rider5 = new Rider("Sajid", "Ali", "Bucuresti", 27, "sajid.ali@gmail.com");
        Rider rider6 = new Rider("Imran", "Qureshi", "Bucuresti", 35, "imran.qureshi@gmail.com");

        riders.add(rider1);
        riders.add(rider2);
        riders.add(rider3);
        riders.add(rider4);
        riders.add(rider5);
        riders.add(rider6);
        return riders;
    }

    public void creeazaComanda(User user, ArrayList<Restaurant> restaurante, ArrayList<Rider> riders, Scanner scanner) throws InterruptedException, InputMismatchException {
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

            userService.adaugaComanda(user, comanda);
            System.out.println("Comanda a fost plasata cu succes!");
            System.out.println("Detalii comanda: \n" + comanda);
            System.out.println("Pret total: " + comanda.calculeazaCostTotal());
            Thread.sleep(2000);
            System.out.println("Comanda dumneavoastra a fost preluata.");
            alegereRestaurant.preiaComanda(comanda);
            Thread.sleep(2000);
            System.out.println("Comanda dumneavoastra este in preparare.");
            alegereRestaurant.preparaComanda(comanda);
            Thread.sleep(2000);
            System.out.println("Comanda dumneavoastra este in livrare.");
            int randomRider = (int)(Math.random() * riders.size());
            Rider riderComanda = riders.get(randomRider);
            riderComanda.adaugaComanda(comanda);
            System.out.println("Livrator: " + riderComanda);
            Thread.sleep(2000);
            System.out.println("Comanda dumneavoastra a fost livrata.");
            riderComanda.livreazaComanda(comanda);

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

                            userService.adaugaRecenzie(user, alegereRestaurant, rating, text);
                            System.out.println("Recenzia a fost adaugata cu succes!");
                            alegereRestaurant.actualizeazaMedieRating();
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

    public void create(Scanner scanner) throws SQLException {
        System.out.println("Ce doriti sa creati?");
        System.out.println("1. User");
        System.out.println("2. Restaurant");
        System.out.println("3. Rider");
        int optiune = scanner.nextInt();
        scanner.nextLine();

        switch (optiune) {
            case 1:
                System.out.println("Introduceti numele:");
                String nume = scanner.nextLine();
                System.out.println("Introduceti prenumele:");
                String prenume = scanner.nextLine();
                System.out.println("Introduceti adresa:");
                String adresa = scanner.nextLine();
                System.out.println("Introduceti varsta:");
                int varsta = scanner.nextInt();
                scanner.nextLine();
                System.out.println("Introduceti email-ul:");
                String email = scanner.nextLine();
                System.out.println("Introduceti parola:");
                String parola = scanner.nextLine();

                User user = new User(nume, prenume, adresa, varsta, email, parola);
                userService.adaugaUser(user);
                System.out.println("User creat cu succes!");
                break;

            case 2:
                System.out.println("Introduceti numele restaurantului:");
                String numeRestaurant = scanner.nextLine();
                System.out.println("Introduceti adresa restaurantului:");
                String adresaRestaurant = scanner.nextLine();
                System.out.println("Introduceti tipul de mancare:");
                String tipMancare = scanner.nextLine();

                Restaurant restaurant = new Restaurant(numeRestaurant, adresaRestaurant, tipMancare);
                restaurant.insert(restaurant);
                System.out.println("Restaurant creat cu succes!");
                break;

            case 3:
                System.out.println("Introduceti numele riderului:");
                String numeRider = scanner.nextLine();
                System.out.println("Introduceti prenumele riderului:");
                String prenumeRider = scanner.nextLine();
                System.out.println("Introduceti adresa riderului:");
                String adresaRider = scanner.nextLine();
                System.out.println("Introduceti varsta riderului:");
                int varstaRider = scanner.nextInt();
                scanner.nextLine();
                System.out.println("Introduceti email-ul riderului:");
                String emailRider = scanner.nextLine();

                Rider rider = new Rider(numeRider, prenumeRider, adresaRider, varstaRider, emailRider);
                rider.insert(rider);
                System.out.println("Rider creat cu succes!");
                break;

            default:
                System.out.println("Optiune invalida.");
        }
    }

    public void read(Scanner scanner) throws SQLException {
        System.out.println("Ce doriti sa cititi?");
        System.out.println("1. Useri");
        System.out.println("2. Restaurante");
        System.out.println("3. Rideri");
        int optiune = scanner.nextInt();

        switch (optiune) {
            case 1:
                for (User user : userService.getUtilizatori()) {
                    System.out.println(user);
                }
                break;

            case 2:
                for (Restaurant restaurant : initializeRestaurants()) {
                    System.out.println(restaurant);
                }
                break;

            case 3:
                for (Rider rider : initializeRiders()) {
                    System.out.println(rider);
                }
                break;

            default:
                System.out.println("Optiune invalida.");
        }
    }

    public void update(Scanner scanner) throws SQLException {
        System.out.println("Ce doriti sa actualizati?");
        System.out.println("1. User");
        System.out.println("2. Restaurant");
        System.out.println("3. Rider");
        int optiune = scanner.nextInt();
        scanner.nextLine(); // Consumăm newline-ul

        switch (optiune) {
            case 1:
                System.out.println("Introduceti ID-ul utilizatorului:");
                int idUser = scanner.nextInt();
                scanner.nextLine(); // Consumăm newline-ul
                System.out.println("Introduceti noul nume:");
                String nume = scanner.nextLine();
                System.out.println("Introduceti noul prenume:");
                String prenume = scanner.nextLine();

                User user = userService.cautaDupaID(idUser);
                if (user != null) {
                    user.setNume(nume);
                    user.setPrenume(prenume);
                    userService.update(user);
                    System.out.println("User actualizat cu succes!");
                } else {
                    System.out.println("Userul nu a fost gasit.");
                }
                break;

            case 2:
                System.out.println("Introduceti ID-ul restaurantului:");
                int idRestaurant = scanner.nextInt();
                scanner.nextLine(); // Consumăm newline-ul
                System.out.println("Introduceti noul nume:");
                String numeRestaurant = scanner.nextLine();

                Restaurant restaurant = new Restaurant(); // Exemplu
                restaurant.setIdRestaurant(idRestaurant);
                restaurant.setNume(numeRestaurant);
                restaurant.update(restaurant);
                System.out.println("Restaurant actualizat cu succes!");
                break;

            case 3:
                System.out.println("Introduceti ID-ul riderului:");
                int idRider = scanner.nextInt();
                scanner.nextLine(); // Consumăm newline-ul
                System.out.println("Introduceti noul nume:");
                String numeRider = scanner.nextLine();

                Rider rider = new Rider(); // Exemplu
                rider.setIdRider(idRider);
                rider.setNume(numeRider);
                rider.update(rider);
                System.out.println("Rider actualizat cu succes!");
                break;

            default:
                System.out.println("Optiune invalida.");
        }
    }

    public void delete(Scanner scanner) throws SQLException {
        System.out.println("Ce doriti sa stergeti?");
        System.out.println("1. User");
        System.out.println("2. Restaurant");
        System.out.println("3. Rider");
        int optiune = scanner.nextInt();

        switch (optiune) {
            case 1:
                System.out.println("Introduceti ID-ul utilizatorului:");
                int idUser = scanner.nextInt();
                userService.delete(idUser);
                System.out.println("User sters cu succes!");
                break;

            case 2:
                System.out.println("Introduceti ID-ul restaurantului:");
                int idRestaurant = scanner.nextInt();
                Restaurant restaurant = new Restaurant();
                restaurant.delete(idRestaurant);
                System.out.println("Restaurant sters cu succes!");
                break;

            case 3:
                System.out.println("Introduceti ID-ul riderului:");
                int idRider = scanner.nextInt();
                Rider rider = new Rider();
                rider.delete(idRider);
                System.out.println("Rider sters cu succes!");
                break;

            default:
                System.out.println("Optiune invalida.");
        }
    }

}
