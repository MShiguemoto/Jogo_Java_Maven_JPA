package ifpr.paranavai.jogo.modelo;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

public class JogoSalvo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    private Personagem personagem;

    @OneToOne
    private Fase fase;



    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public Personagem getPersonagem(){
        return personagem;
    }

    public void setPersonagem(Personagem personagem, Fase fase){
        this.personagem = personagem;
    }

        public Fase getFase(){
        return fase;
    }

    public void setFase(Fase fase){
        this.fase = fase;
    }
}
