package ifpr.paranavai.jogo.modelo;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.swing.JPanel;
import javax.swing.Timer;

@Entity
@Table(name = "save")
public abstract class Fase extends JPanel implements ActionListener, KeyListener {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column (name = "id")
    private long id;

    protected Image fundo;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "save", targetEntity = Personagem.class)
    protected Personagem personagem;

    protected List<Inimigo> inimigos;
    protected List<Asteroide> asteroides;
    protected Timer timer;
    protected boolean emJogo = true;

    public Fase() {
        setFocusable(true); // + define o foco inicial do jogo
        setDoubleBuffered(true); // + Otimização computacional
        addKeyListener(this); // + Definindo que a própria classe irá controlar os eventos do teclado
    
    }

    public abstract void inicializaElementosGraficosAdicionais();

    public abstract void inicializaInimigos();

    public abstract void verificarColisoes();

    @Override
    public void keyTyped(KeyEvent e) {
    }
    
    @Override
    public abstract void keyPressed(KeyEvent e);

    @Override
    public abstract void keyReleased(KeyEvent e);

    @Override
    public abstract void actionPerformed(ActionEvent e);

    public void desenhaPontuacao(Graphics2D graficos) {
        String textoPontuacao = "PONTOS: " + personagem.getPontuacao();
        graficos.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 22));
        graficos.setColor(new java.awt.Color(255, 255, 255));
        graficos.drawString(textoPontuacao, 20, 25);
    }

    public void desenhaVidas(Graphics2D graficos) {
        String textoVidas = "VIDAS: " + personagem.getVidas();
        graficos.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 22));
        graficos.setColor(new java.awt.Color(255, 255, 255));
        graficos.drawString(textoVidas, 180, 25);
    }

    public void desenhaHitBox(Graphics2D graficos) {
        //Shape circulo = new Ellipse2D.Float(0,0,100,100);
        //graficos.draw(circulo);
        graficos.drawRect(personagem.getPosicaoEmX(), personagem.getPosicaoEmY(), personagem.getLarguraImagem(), personagem.getAlturaImagem());
    }

}
