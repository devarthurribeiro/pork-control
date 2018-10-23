package modelo;

import java.util.List;

/**
 *
 * @author arthur
 */
public interface BaiaDAO {
    void create(Baia baia);
    void delete(Baia baia);
    void update(Baia baia);
    List<Baia> all();
    Baia findById(int codigo);
}
