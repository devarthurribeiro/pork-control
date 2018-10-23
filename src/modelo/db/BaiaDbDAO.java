package modelo.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.Baia;
import modelo.BaiaDAO;

/**
 *
 * @author arthur
 */
public class BaiaDbDAO extends Database implements BaiaDAO {

    @Override
    public void create(Baia baia) {
        open();
        String query = "INSERT INTO baia(tamanho, limpo) VALUES (?, ?);";
        try {
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setString(1, baia.getTamanho());
            pst.setBoolean(2, baia.getLimpo());
            pst.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao criar a Baia! " + e.getMessage());
        } finally {
            close();
        }
    }

    @Override
    public void delete(Baia baia) {
        open();
        String query = "DELETE FROM baia WHERE id = ?;";
        try {
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setInt(1, baia.getCodigo());
            pst.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao deletar a baia " + baia.getCodigo() + ":" + e.getMessage());
        } finally {
            close();
        }
    }

    @Override
    public void update(Baia baia) {
        open();
        String query = "UPDATE baia SET tamanho = ?, limpo = ? WHERE id = ?;";
        try {
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setString(1, baia.getTamanho());
            pst.setBoolean(2, baia.getLimpo());
            pst.setInt(3, baia.getCodigo());
            pst.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar a baia: " + baia.getCodigo() + e.getMessage());
        } finally {
            close();
        }
    }

    @Override
    public List<Baia> all() {
        open();
        ArrayList<Baia> baiaList = new ArrayList<>();
        String query = "SELECT * FROM baia;";
        try {
            PreparedStatement pst = connection.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int codigo = rs.getInt("id");
                String tamanho = rs.getString("tamanho").trim();
                boolean limpo = rs.getBoolean("limpo");
                Baia b = new Baia(codigo, tamanho, limpo);
                baiaList.add(b);
            }

        } catch (SQLException e) {
             System.err.println("Erro ao listar as baias:" + e.getMessage());
        } finally {
            close();
        }
        return baiaList;
    }

    @Override
    public Baia findById(int codigo) {
        open();
        Baia baia = new Baia();
        String query = "SELECT * FROM baia WHERE id = ?;";
        try {
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setInt(1, codigo);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                baia.setCodigo(rs.getInt("id"));
                baia.setTamanho(rs.getString("tamanho").trim());
                baia.setLimpo(rs.getBoolean("limpo"));
            }

        } catch (SQLException e) {
             System.err.println("Erro ao procurar a baia: " + codigo + e.getMessage());
        } finally {
            close();
        }
        return baia;
    }
}
