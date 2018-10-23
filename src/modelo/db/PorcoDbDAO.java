package modelo.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import modelo.Baia;
import modelo.Porco;
import modelo.PorcoDAO;

/**
 *
 * @author arthur
 */
public class PorcoDbDAO extends Database implements PorcoDAO {

    @Override
    public void create(Porco porco) {
        open();
        String query = "INSERT INTO porco(nome, nascimento, aquisicao, baia) VALUES (?, ?, ?, ?);";
        try {
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setString(1, porco.getNome());
            pst.setDate(2, new java.sql.Date(porco.getNascimento().getTime()));
            pst.setDate(3, new java.sql.Date(porco.getAquisicao().getTime()));
            pst.setInt(4, porco.getBaia().getCodigo());
            pst.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao criar o porco! " + e.getMessage());
        } finally {
            close();
        }
    }

    @Override
    public void delete(Porco porco) {
        open();
        String query = "DELETE FROM porco WHERE id = ?;";
        try {
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setInt(1, porco.getCodigo());
            pst.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao deletar a baia " + porco.getCodigo() + ":" + e.getMessage());
        } finally {
            close();
        }
    }

    @Override
    public void update(Porco porco) {
        open();
        String query = "UPDATE porco SET nome = ?, nascimento = ?, aquisicao = ?, baia = ? WHERE id = ?;";
        try {
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setString(1, porco.getNome());
            pst.setDate(2, new java.sql.Date(porco.getNascimento().getTime()));
            pst.setDate(3, new java.sql.Date(porco.getAquisicao().getTime()));
            pst.setInt(4, porco.getBaia().getCodigo());
            pst.setInt(5, porco.getCodigo());
            pst.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar o porco: " + porco.getCodigo() + e.getMessage());
        } finally {
            close();
        }
    }

    @Override
    public List<Porco> all() {
        open();
        ArrayList<Porco> porcoList = new ArrayList<>();
        String query = "SELECT * FROM porco;";
        try {
            PreparedStatement pst = connection.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int codigo = rs.getInt("id");
                String nome = rs.getString("nome").trim();
                Date nascimento = rs.getDate("nascimento");
                Date aquisicao = rs.getDate("aquisicao");
                Baia baia = Baia.findById(rs.getInt("baia"));
                Porco p = new Porco(codigo, nome, nascimento, aquisicao, baia);
                porcoList.add(p);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao listar os porcos:" + e.getMessage());
        } finally {
            close();
        }
        return porcoList;
    }

}
