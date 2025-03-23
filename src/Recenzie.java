import java.util.concurrent.atomic.AtomicInteger;

public class Recenzie {
    private static int nrRecenzii;
    private int idRecenzie;
    private int rating;
    private String comentariu;
    private User user;
    private Restaurant restaurant;

    // Bloc static
    static {
        nrRecenzii = 0;
    }

    // Bloc nestatic
    {
        this.idRecenzie = ++nrRecenzii;
    }

    // Constructor fara parametri
    public Recenzie() {
        this.rating = 0;
        this.comentariu = "";
        this.user = null;
        this.restaurant = null;
    }

    // Constructor parametrizat
    public Recenzie(int rating, String comentariu, User user, Restaurant restaurant) {
        this.rating = rating;
        this.comentariu = comentariu;
        this.user = user;
        this.restaurant = restaurant;
    }

    // Constructor de copiere
    public Recenzie(Recenzie other) {
        this.rating = other.rating;
        this.comentariu = other.comentariu;
        this.user = other.user;
        this.restaurant = other.restaurant;
    }

    // Getteri
    public int getIdRecenzie() {
        return idRecenzie;
    }

    public static int getNrRecenzii() {
        return nrRecenzii;
    }

    public int getRating() {
        return rating;
    }

    public String getComentariu() {
        return comentariu;
    }

    public User getUser() {
        return user;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    // Setteri
    public void setRating(int rating) {
        this.rating = rating;
    }

    public void setComentariu(String comentariu) {
        this.comentariu = comentariu;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }


}
