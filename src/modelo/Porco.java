package modelo;

import java.util.Date;
import java.util.List;
import modelo.db.PorcoDbDAO;

/**
 *
 * @author arthur
 */
public class Porco {

    private int codigo;
    private String nome;
    private Date nascimento;
    private Date aquisicao;
    private Baia baia;

    private static PorcoDAO dao = new PorcoDbDAO();

    public Porco(int codigo, String nome, Date nascimento, Date aquisicao, Baia baia) {
        this.codigo = codigo;
        this.nome = nome;
        this.nascimento = nascimento;
        this.aquisicao = aquisicao;
        this.baia = baia;
    }

    public Porco(String nome, Date nascimento, Date aquisicao, Baia baia) {
        this.nome = nome;
        this.nascimento = nascimento;
        this.aquisicao = aquisicao;
        this.baia = baia;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getNascimento() {
        return nascimento;
    }

    public void setNascimento(Date nascimento) {
        this.nascimento = nascimento;
    }

    public Date getAquisicao() {
        return aquisicao;
    }

    public void setAquisicao(Date aquisicao) {
        this.aquisicao = aquisicao;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Baia getBaia() {
        return baia;
    }

    public void setBaia(Baia baia) {
        this.baia = baia;
    }

    public static List<Porco> all() {
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

}
