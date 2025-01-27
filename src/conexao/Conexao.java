package conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    //Conexao mysql

    public Connection getConexao() {
        try {
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost/atividade1_uc10",
                    "root",
                    "285072Gaba#"
            );
            System.out.println("Conexao realizada");
            return conn;

        } catch (Exception e) {
            System.out.println("Erro ao conectar " + e.getMessage());
            return null;
        }
    }

    public void desconectar(Connection conn) {
        try {
            conn.close();
            System.out.println("desconectado");
        } catch (SQLException e) {

        }
    }

}
