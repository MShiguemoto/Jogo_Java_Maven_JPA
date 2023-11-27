package ifpr.paranavai.jogo.servico;

import ifpr.paranavai.jogo.dao.PersonagemDao;
import ifpr.paranavai.jogo.dao.PersonagemDaoImpl;
import ifpr.paranavai.jogo.modelo.Personagem;

public class PersonagemServico {
    // Cria a instancia do DAO chamando o implementacao concreta
    private PersonagemDao personagemDao = new PersonagemDaoImpl();

    // Salva o personagem utilizando o Dao
    public void salvarPersonagem(Personagem personagem){
        personagemDao.salvarPersonagem(personagem);
    }

    // Carrega o personagem utilizando o Dao
    public Personagem carregarPersonagem(Integer id){
        return personagemDao.carregarPersonagem(id);
    }
}
