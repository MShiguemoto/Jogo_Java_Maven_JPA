package ifpr.paranavai.jogo.controle;

import ifpr.paranavai.jogo.modelo.JogoSalvo;
import ifpr.paranavai.jogo.servico.JogoSalvoServico;

public class JogoSalvoControle {
    private JogoSalvoServico jogoSalvoServico = new JogoSalvoServico();

    public void salvarJogo(JogoSalvo jogoSalvo){
        jogoSalvoServico.salvarJogo(jogoSalvo);
    }

    public JogoSalvo carregarJogoSalvo(Integer id){
        return jogoSalvoServico.carregarJogoSalvo(id);
    }
}
