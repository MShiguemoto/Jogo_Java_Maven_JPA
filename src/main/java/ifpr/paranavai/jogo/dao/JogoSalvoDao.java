package ifpr.paranavai.jogo.dao;

import ifpr.paranavai.jogo.modelo.JogoSalvo;

public interface JogoSalvoDao {
    public void salvarJogo(JogoSalvo jogo);
    public JogoSalvo carregarJogoSalvo(Integer id);
}
