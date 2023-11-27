package ifpr.paranavai.jogo.modelo;

import javax.persistence.Entity;
import javax.swing.ImageIcon;

@Entity
public class Inimigo extends ElementoGrafico {
    private static int VELOCIDADE = 2;

    public Inimigo(int xAleatorio, int yAleatorio) {
        this.carregar();
        super.setPosicaoEmX(xAleatorio);
        super.setPosicaoEmY(yAleatorio);
    }

    public void carregar() {
        ImageIcon carregando = new ImageIcon(getClass().getResource("/inimigo.png"));
        super.setImagem(carregando.getImage());
    }

    public void atualizar() {
        super.setPosicaoEmX(super.getPosicaoEmX() - VELOCIDADE);
    }
}
