package ifpr.paranavai.jogo.dao;

import ifpr.paranavai.jogo.modelo.Personagem;

public interface PersonagemDao {
    // Metodos que serao utilizados por PersonagemDaoImpl
    public void salvarPersonagem(Personagem personagem);
    public Personagem carregarPersonagem(Integer id);
}
