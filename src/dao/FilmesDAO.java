package dao;

import ATV_2_UC10_Aluno_Glauber.Filmes;
import conexao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

// Todo crud vai aqui
//        DEIXEI EM BRANCO USER E SENHA
public class FilmesDAO {

    private Conexao conexao;
    private Connection conn;

    public FilmesDAO() {
        this.conexao = new Conexao();
        this.conn = this.conexao.getConexao(); //ESTE CONSTRUTOR JA INSTACIA O CONN
    }

    public void salvarFilme(Filmes filme) {

        //ajustando formato Data
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

        ///Consultando  se id já existe
        try {
            PreparedStatement checkSt = conn.prepareStatement("SELECT COUNT(*) FROM filmes WHERE id = ?");
            checkSt.setInt(1, filme.getId());
            ResultSet rs = checkSt.executeQuery();
            rs.next();
            if (rs.getInt(1) > 0) {
                JOptionPane.showMessageDialog(null, "Número de identificador ja cadastrado");
            }
        } catch (SQLException sqle) {
            System.out.println("erro checar id " + sqle.getMessage());
        }

        //Insert 
        String sql = "INSERT INTO filmes  (id , nome, datalancamento , categoria ) values (?,?,?,?) ";
        try {

            PreparedStatement stmt = this.conn.prepareStatement(sql);

            stmt.setInt(1, filme.getId());
            stmt.setString(2, filme.getNome());
            stmt.setString(3, sdf.format(filme.getData()));
            stmt.setString(4, filme.getCategoria());

            stmt.execute();

        } catch (SQLException sqle) {
            System.out.println("erro ao concetar " + sqle.getMessage());

        }
    }

    public List<Filmes> getTodosFilmes(String nome) {
        //Lista para todos os filmes a partir de digitação no txtField
        List<Filmes> listafilmesFiltroNome = new ArrayList<>();
        String sql = "SELECT * FROM filmes WHERE nome LIKE ? ";

        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);

            stmt.setString(1, "%" + nome + "%");

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                Filmes filmes = new Filmes();

                filmes.setId(rs.getInt("id"));
                filmes.setNome(rs.getString("nome"));
                filmes.setData(rs.getDate("datalancamento"));
                filmes.setCategoria(rs.getString("categoria"));

                listafilmesFiltroNome.add(filmes);

            }

        } catch (SQLException sqle) {
            System.out.println("erro ao buscar dados DAO" + sqle.getMessage());
        }
        return listafilmesFiltroNome;

    }

    public List<Filmes> getTodosFilmesId(int id) {
        //Consulta tododos os filmes através do id
        List<Filmes> listafilmesFiltroNome = new ArrayList<>();
        String sql = "SELECT * FROM filmes WHERE id LIKE ?";

        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);

            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                Filmes filmes = new Filmes();

                filmes.setId(rs.getInt("id"));
                filmes.setNome(rs.getString("nome"));
                filmes.setData(rs.getDate("datalancamento"));
                filmes.setCategoria(rs.getString("categoria"));

                listafilmesFiltroNome.add(filmes);

            }

        } catch (SQLException sqle) {
            System.out.println("erro ao buscar dados DAO" + sqle.getMessage());
        }
        return listafilmesFiltroNome;

    }

    public List<Filmes> getFilmeCateroia(String categoria) {

        //Filtro Categoria
        List<Filmes> listafilmesFiltroNome = new ArrayList<>();
        String sql = "SELECT * FROM filmes WHERE categoria LIKE ?";

        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);

            stmt.setString(1, "%" + categoria + "%");

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                Filmes filmes = new Filmes();

                filmes.setId(rs.getInt("id"));
                filmes.setNome(rs.getString("nome"));
                filmes.setData(rs.getDate("datalancamento"));
                filmes.setCategoria(rs.getString("categoria"));

                listafilmesFiltroNome.add(filmes);

            }

        } catch (SQLException sqle) {
            System.out.println("erro ao buscar dados DAO" + sqle.getMessage());
        }
        return listafilmesFiltroNome;

    }

    public Filmes getFilmes(int id) {
//Filtro ID
        String sql = "SELECT * FROM filmes WHERE id = ?";

        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setInt(1, id); //setando id para busca

            ResultSet rs = stmt.executeQuery();

            Filmes filmes = new Filmes();

            rs.next();

            filmes.setId(id);
            filmes.setNome(rs.getString("nome"));
            filmes.setData(rs.getDate("datalancamento"));
            filmes.setCategoria(rs.getString("categoria"));

            return filmes;

        } catch (SQLException sqle) {
            System.out.println("Não foi possivel realizar a pesquisa " + sqle.getMessage());
            return null;
        }

    }

    public void editar(Filmes filmes) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

        //Update
        String sql = "UPDATE filmes set nome =? , datalancamento =? , categoria=?  WHERE id =?";

        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);

            stmt.setString(1, filmes.getNome());
            stmt.setString(2, sdf.format(filmes.getData()));
            stmt.setString(3, filmes.getCategoria());
            stmt.setInt(4, filmes.getId());

            stmt.execute();

        } catch (SQLException sqle) {
            System.out.println("erro ao editar no banco DAO " + sqle.getMessage());
        }
    }

    public void excluir(int id) {

        /**
         * Excluir
         */
        String sql = "DELETE FROM filmes WHERE id = ?";

        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);

            stmt.setInt(1, id);

            stmt.execute();

        } catch (SQLException sqle) {
            System.out.println("Erro ao excluir Filme: " + sqle.getMessage());
        }

    }

    public void desconectar() {
        try {
            conn.close();
        } catch (SQLException sqle) {
        }
    }

}
