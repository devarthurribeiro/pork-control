package modelo;

import java.util.List;

/**
 *
 * @author arthur
 */
public interface PorcoDAO {
    void create(Porco porco);
    void delete(Porco porco);
    void update(Porco porco);
    List<Porco> all();
}
