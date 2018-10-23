package modelo;

import java.util.List;
import modelo.db.BaiaDbDAO;

/**
 *
 * @author arthur
 */
public class Baia {

    int codigo;
    String tamanho;
    Boolean limpo;
    private static BaiaDAO dao = new BaiaDbDAO();

    public Baia() {
    }

    public Baia(String tamanho, Boolean limpo) {
        this.tamanho = tamanho;
        this.limpo = limpo;
    }

    public Baia(int codigo, String tamanho, boolean limpo) {
        this.codigo = codigo;
        this.tamanho = tamanho;
        this.limpo = limpo;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getTamanho() {
        return tamanho;
    }

    public void setTamanho(String tamanho) {
        this.tamanho = tamanho;
    }

    public Boolean getLimpo() {
        return limpo;
    }

    public void setLimpo(Boolean limpo) {
        this.limpo = limpo;
    }

    public static List<Baia> all() {
        return dao.all();
    }

    public void save() {
        if (codigo != 0) {
            dao.update(this);
        } else {
            dao.create(this);
        }
    }

    public void delete() {
        dao.delete(this);
    }
    
    public static Baia findById(int codigo) {
        return dao.findById(codigo);
    }

    @Override
    public String toString() {
        return "Baia: " + codigo;
    }
}
