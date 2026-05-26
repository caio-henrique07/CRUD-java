package com.template;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.logging.Level;

public class HeroisDAO {
    private static final Logger logger = Logger.getLogger(HeroisDAO.class.getName());
    // CREATE
    public void inserir(HeroisDTO h) {
        String sql = "INSERT INTO herois (nome, funcao, nacionalidade, vida) VALUES (?, ?, ?, ?)";

        try (Connection con = Conexao.conectaBD();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, h.getNome());
            ps.setString(2, h.getFuncao());
            ps.setString(3, h.getNacionalidade()    );
            ps.setInt(4, h.getVida());

            ps.executeUpdate();

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Erro ao listar", e);
        }
    }

    // READ (listar todos)
    public List<HeroisDTO> listar() {
        List<HeroisDTO> lista = new ArrayList<>();
        String sql = "SELECT * FROM herois";

        try (Connection con = Conexao.conectaBD();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                HeroisDTO h = new HeroisDTO();

                h.setId(rs.getInt("id"));
                h.setNome(rs.getString("nome"));
                h.setFuncao(rs.getString("funcao"));
                h.setNacionalidade(rs.getString("nacionalidade"));
                h.setVida(rs.getInt("vida"));

                lista.add(h);
            }

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Erro ao listar", e);
        }

        return lista;
    }

    // UPDATE
    public void atualizar(HeroisDTO h) {
        String sql = "UPDATE herois SET nome=?, funcao=?, nacionalidade=?, vida=? WHERE id=?";

        try (Connection con = Conexao.conectaBD();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, h.getNome());
            ps.setString(2, h.getFuncao());
            ps.setString(3, h.getNacionalidade());
            ps.setInt(4, h.getVida());
            ps.setInt(5, h.getId());

            ps.executeUpdate();

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Erro ao listar", e);
        }
    }

    // DELETE
    public void excluir(int id) {
        String sql = "DELETE FROM herois WHERE id=?";

        try (Connection con = Conexao.conectaBD();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Erro ao listar", e);
        }
    }
}
