import java.util.ArrayList;

public class LoginService {
    private ArrayList<User> utilizatori;

    public LoginService(ArrayList<User> utilizatori) {
        this.utilizatori = utilizatori;
    }

    public User login(String email, String parola) {
        for (User user : utilizatori) {
            if (user.getEmail().equals(email) && user.getParola().equals(parola)) {
                System.out.println("Autentificare reușită! Bine ai venit, " + user.getNume() + "!");
                return user;
            }
        }
        System.out.println("Autentificare eșuată! Email sau parolă incorectă.");
        System.out.println(email);
        System.out.println(parola);
        return null;
    }

    public void register(User user) {
        utilizatori.add(user);
        System.out.println("Utilizator înregistrat cu succes!");
    }
}