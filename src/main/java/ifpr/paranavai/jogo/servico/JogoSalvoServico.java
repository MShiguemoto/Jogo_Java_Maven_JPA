package ifpr.paranavai.jogo.servico;

import ifpr.paranavai.jogo.dao.JogoSalvoDao;
import ifpr.paranavai.jogo.dao.JogoSalvoDaoImpl;
import ifpr.paranavai.jogo.modelo.JogoSalvo;

public class JogoSalvoServico {
    private JogoSalvoDao jogoSalvoDao = new JogoSalvoDaoImpl();

    public void salvarJogo(JogoSalvo jogoSalvo){
        jogoSalvoDao.salvarJogo(jogoSalvo);
    }

    public JogoSalvo carregarJogoSalvo(Integer id){
        return jogoSalvoDao.carregarJogoSalvo(id);
    }
}
