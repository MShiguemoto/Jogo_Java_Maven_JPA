package ifpr.paranavai.jogo.conexao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

//import ifpr.paranavai.jogo.controle.PersonagemControle;
import ifpr.paranavai.jogo.modelo.Jogador;
import ifpr.paranavai.jogo.modelo.Personagem;


public class TesteHibernate {
    public static void main (String[] args) {
        try{
            //Session s2  =HibernateUtil.GetSession();
            Jogador j = new Jogador("Shigs [01:38]11323");
            j.personagem = new Personagem();
            //Personagem p = new Personagem();
             List<Object> objetos = new ArrayList();
            objetos.add(j);

            //PersonagemControle controle = new PersonagemControle();
            //controle.salvarPersonagem(p);
            //HibernateUtil.SalvarObjeto(j.personagem);    
            HibernateUtil.SalvarObjeto(objetos);    
            


        } catch (Exception ex) {
            throw ex;
        }



    }
}