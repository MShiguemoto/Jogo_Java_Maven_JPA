package ifpr.paranavai.jogo.modelo;


import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.swing.ImageIcon;

@Entity
@DiscriminatorValue("S")
public class TiroSuper extends Tiro{

    public TiroSuper(int posicaoPersonagemEmX, int posicaoPersonagemEmY){
        super(posicaoPersonagemEmX, posicaoPersonagemEmY);
        this.carregar();
    }

    @Override
    public void carregar() {
        ImageIcon carregando = new ImageIcon(getClass().getResource("/supertiro.png"));
        super.setImagem(carregando.getImage());
    }

    @Override
    public void atualizar() {
        super.setPosicaoEmX(super.getPosicaoEmX() + VELOCIDADE + 10);
    }
    
}
