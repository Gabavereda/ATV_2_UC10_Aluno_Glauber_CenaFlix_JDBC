package ATV_2_UC10_Aluno_Glauber;

import dao.FilmesDAO;
import view.TelaCadastro;

public class ATV_2_UC10_Aluno_Glauber {

    public static void main(String[] args) {
        FilmesDAO DAO = new FilmesDAO();

        TelaCadastro tc = new TelaCadastro();
        tc.setVisible(true);
        tc.setLocationRelativeTo(null);

    }

}
