package ifpr.paranavai.jogo.modelo;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.swing.ImageIcon;

@Entity
public class Tiro extends ElementoGrafico {


    // @Id
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    // private int id;

    // @ManyToOne
    // @JoinColumn(name = "Personagem")
    // protected static Personagem personagem;
    @Column(name = "jogador")
    private Jogador jogador;

    @Column(name = "tiro")
    private String tiro;

    @Column(insertable = false)
    protected static int VELOCIDADE = 4;

    public Tiro(int posicaoPersonagemEmX, int posicaoPersonagemEmY) {
        this.carregar();
        super.setPosicaoEmX(posicaoPersonagemEmX);
        super.setPosicaoEmY(posicaoPersonagemEmY - (this.getAlturaImagem() / 2));
    }

    @Override
    public void carregar() {
        ImageIcon carregando = new ImageIcon(getClass().getResource("/tiro.png"));
        super.setImagem(carregando.getImage());
    }

    @Override
    public void atualizar() {
        super.setPosicaoEmX(super.getPosicaoEmX() + VELOCIDADE);
    }
}