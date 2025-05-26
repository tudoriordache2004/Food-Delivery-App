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
    public Recenzie(User user, Restaurant restaurant, int rating, String comentariu) {
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
        if (rating < 1 || rating > 5) {
            throw new IllegalArgumentException("Ratingul trebuie să fie între 1 și 5.");
        }
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Recenzie recenzie = (Recenzie) o;
        return idRecenzie == recenzie.idRecenzie;
    }

}
