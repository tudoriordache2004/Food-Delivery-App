import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // Creare restaurante
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

        // Testare metoda creeazaComanda
        Scanner scanner = new Scanner(System.in);
        user1.creeazaComanda(restaurante, scanner);

        scanner.close();
    }
}
