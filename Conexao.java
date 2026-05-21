package conexao;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexao {

    public static Connection conectar() {
        try {
            String url = "jdbc:mysql://localhost:3306/padaria_estoque";
            String usuario = "root";
            String senha = "";

            Connection conn = DriverManager.getConnection(url, usuario, senha);
            return conn;
        } catch (Exception e) {
            System.out.println("Erro ao conectar: " + e.getMessage());
            return null;
        }
    }
}