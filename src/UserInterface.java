import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class UserInterface {

    public void afiseazaMeniuPrincipal() {
        System.out.println("Bine ai venit!");
        System.out.println("1. Creare comanda");
        System.out.println("2. Vizualizare comenzi active");
        System.out.println("3. Iesire");
    }

    public void initializare() {
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

        // Creare utilizator
        User user1 = new User("Popescu", "Ion", "Bucuresti", 25, "ion.popescu@email.com");

        // Creare voucher
        Voucher voucher1 = new Voucher("X123F5G", 25, LocalDate.of(2025, 10, 30));
        user1.adaugaVoucher(voucher1);


        // Testare metoda creeazaComanda
        Scanner scanner = new Scanner(System.in);
        while (true) {
            afiseazaMeniuPrincipal();
            int optiune = scanner.nextInt();
            switch (optiune) {
                case 1:
                    user1.creeazaComanda(restaurante, scanner);
                case 2:
                    user1.comenziActive();
                case 3:
                    System.out.println("La revedere!");
                    return;
                default:
                    System.out.println("Optiune invalida.");
            }
            user1.creeazaComanda(restaurante, scanner);
        }
    }
}
