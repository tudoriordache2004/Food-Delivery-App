import java.sql.SQLException;
import java.util.List;

public interface GenericService<T> {
    void insert(T obiect) throws SQLException;
    T cautaDupaID(int id);
    List<T> cauta();
    void update(T obiect);
    void delete(int id);
}
