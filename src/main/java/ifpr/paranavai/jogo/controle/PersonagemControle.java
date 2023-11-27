package ifpr.paranavai.jogo.controle;

import ifpr.paranavai.jogo.modelo.Personagem;
import ifpr.paranavai.jogo.servico.PersonagemServico;

public class PersonagemControle {
    // Cria a instacia do servico de personagem
    private PersonagemServico personagemServico = new PersonagemServico();

    // Salva o personagem utilizando o servico
    public void salvarPersonagem(Personagem personagem){
        personagemServico.salvarPersonagem(personagem);
    }

    // Carrega o personagem utilizando o servico
    public Personagem carregarPersonagem(Integer id){
        return personagemServico.carregarPersonagem(id);
    }
}
