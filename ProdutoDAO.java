package dao;

import model.Produto;
import conexao.Conexao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {

    // Método para listar todos os produtos
    public List<Produto> listarTodos() {
        List<Produto> lista = new ArrayList<>();

        try {
            Connection conn = Conexao.conectar();
            String sql = "SELECT p.*, c.nome_categoria FROM produtos p " +
                    "INNER JOIN categorias c ON p.id_categoria = c.id_categoria";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Produto p = new Produto();
                p.setId(rs.getInt("id_produto"));
                p.setNome(rs.getString("nome"));
                p.setQuantidadeAtual(rs.getInt("quantidade_atual"));
                p.setQuantidadeMinima(rs.getInt("quantidade_minima"));
                p.setDataValidade(rs.getDate("data_validade").toLocalDate());
                p.setIdCategoria(rs.getInt("id_categoria"));
                p.setNomeCategoria(rs.getString("nome_categoria"));
                lista.add(p);
            }
            conn.close();
        } catch (Exception e) {
            System.out.println("Erro ao listar: " + e.getMessage());
        }
        return lista;
    }

    // Método para aumentar estoque
    public void aumentarEstoque(int id, int quantidade) {
        try {
            Connection conn = Conexao.conectar();
            String sql = "UPDATE produtos SET quantidade_atual = quantidade_atual + ? WHERE id_produto = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, quantidade);
            stmt.setInt(2, id);
            stmt.executeUpdate();
            conn.close();
            System.out.println("Estoque aumentado com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao aumentar: " + e.getMessage());
        }
    }

    // Método para diminuir estoque
    public void diminuirEstoque(int id, int quantidade) {
        try {
            Connection conn = Conexao.conectar();
            String sql = "UPDATE produtos SET quantidade_atual = quantidade_atual - ? WHERE id_produto = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, quantidade);
            stmt.setInt(2, id);
            stmt.executeUpdate();
            conn.close();
            System.out.println("Estoque diminuído com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao diminuir: " + e.getMessage());
        }
    }

    // Método para filtrar por categoria
    public List<Produto> filtrarPorCategoria(String categoria) {
        List<Produto> lista = new ArrayList<>();

        try {
            Connection conn = Conexao.conectar();
            String sql = "SELECT p.*, c.nome_categoria FROM produtos p " +
                    "INNER JOIN categorias c ON p.id_categoria = c.id_categoria " +
                    "WHERE c.nome_categoria = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, categoria);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Produto p = new Produto();
                p.setId(rs.getInt("id_produto"));
                p.setNome(rs.getString("nome"));
                p.setQuantidadeAtual(rs.getInt("quantidade_atual"));
                p.setQuantidadeMinima(rs.getInt("quantidade_minima"));
                p.setDataValidade(rs.getDate("data_validade").toLocalDate());
                p.setIdCategoria(rs.getInt("id_categoria"));
                p.setNomeCategoria(rs.getString("nome_categoria"));
                lista.add(p);
            }
            conn.close();
        } catch (Exception e) {
            System.out.println("Erro ao filtrar: " + e.getMessage());
        }
        return lista;
    }

    // Método para produtos vencidos
    public List<Produto> produtosVencidos() {
        List<Produto> lista = new ArrayList<>();

        try {
            Connection conn = Conexao.conectar();
            String sql = "SELECT p.*, c.nome_categoria FROM produtos p " +
                    "INNER JOIN categorias c ON p.id_categoria = c.id_categoria " +
                    "WHERE p.data_validade < CURDATE()";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Produto p = new Produto();
                p.setId(rs.getInt("id_produto"));
                p.setNome(rs.getString("nome"));
                p.setQuantidadeAtual(rs.getInt("quantidade_atual"));
                p.setQuantidadeMinima(rs.getInt("quantidade_minima"));
                p.setDataValidade(rs.getDate("data_validade").toLocalDate());
                p.setIdCategoria(rs.getInt("id_categoria"));
                p.setNomeCategoria(rs.getString("nome_categoria"));
                lista.add(p);
            }
            conn.close();
        } catch (Exception e) {
            System.out.println("Erro ao listar vencidos: " + e.getMessage());
        }
        return lista;
    }

    // Método para produtos próximos ao vencimento (7 dias)
    public List<Produto> produtosProximosVencimento() {
        List<Produto> lista = new ArrayList<>();

        try {
            Connection conn = Conexao.conectar();
            String sql = "SELECT p.*, c.nome_categoria FROM produtos p " +
                    "INNER JOIN categorias c ON p.id_categoria = c.id_categoria " +
                    "WHERE p.data_validade BETWEEN CURDATE() AND DATE_ADD(CURDATE(), INTERVAL 7 DAY)";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Produto p = new Produto();
                p.setId(rs.getInt("id_produto"));
                p.setNome(rs.getString("nome"));
                p.setQuantidadeAtual(rs.getInt("quantidade_atual"));
                p.setQuantidadeMinima(rs.getInt("quantidade_minima"));
                p.setDataValidade(rs.getDate("data_validade").toLocalDate());
                p.setIdCategoria(rs.getInt("id_categoria"));
                p.setNomeCategoria(rs.getString("nome_categoria"));
                lista.add(p);
            }
            conn.close();
        } catch (Exception e) {
            System.out.println("Erro ao listar próximos: " + e.getMessage());
        }
        return lista;
    }

    // Método para verificar estoque baixo
    public List<Produto> estoqueBaixo() {
        List<Produto> lista = new ArrayList<>();

        try {
            Connection conn = Conexao.conectar();
            String sql = "SELECT p.*, c.nome_categoria FROM produtos p " +
                    "INNER JOIN categorias c ON p.id_categoria = c.id_categoria " +
                    "WHERE p.quantidade_atual < p.quantidade_minima";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Produto p = new Produto();
                p.setId(rs.getInt("id_produto"));
                p.setNome(rs.getString("nome"));
                p.setQuantidadeAtual(rs.getInt("quantidade_atual"));
                p.setQuantidadeMinima(rs.getInt("quantidade_minima"));
                p.setDataValidade(rs.getDate("data_validade").toLocalDate());
                p.setIdCategoria(rs.getInt("id_categoria"));
                p.setNomeCategoria(rs.getString("nome_categoria"));
                lista.add(p);
            }
            conn.close();
        } catch (Exception e) {
            System.out.println("Erro ao verificar estoque baixo: " + e.getMessage());
        }
        return lista;
    }
}