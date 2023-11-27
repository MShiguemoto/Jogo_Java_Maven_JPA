package ifpr.paranavai.jogo.modelo;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.swing.ImageIcon;
import javax.swing.Timer;

import org.hibernate.Session;

import ifpr.paranavai.jogo.conexao.HibernateUtil;
import ifpr.paranavai.jogo.principal.Principal;

@Entity
public class FaseUm extends Fase {

    private static final int DELAY = 5;
    private static final int QTDE_DE_INIMIGOS = 40;
    private static final int QTDE_DE_ASTEROIDES = 50;
    private static final int PONTOS_POR_INIMIGO = 10;

    public FaseUm() { // Linha adicionada (+)
        super(); // Chamada do construtor da classe super
        this.emJogo = true;
        ImageIcon carregando = new ImageIcon(getClass().getResource("/fundo.jpg"));
        this.fundo = carregando.getImage();

        this.personagem = new Personagem(); // + Criação do objeto Personagem

        this.inicializaElementosGraficosAdicionais();

        this.inicializaInimigos();
  
         this.timer = new Timer(DELAY, this); // + Criação do objeto Timer
         this.timer.start(); // + Iniciando o nosso jogo
        // this.timer.setDelay(1);
    }

    @Override
    public void inicializaInimigos() {
        inimigos = new ArrayList<Inimigo>();

        for (int i = 0; i < QTDE_DE_INIMIGOS; i++) {
            int x = (int) ((Math.random() * 8000) + Principal.LARGURA_DA_JANELA);
            int y = (int) (Math.random() * Principal.ALTURA_DA_JANELA);
            Inimigo inimigo = new Inimigo(x, y);
            inimigos.add(inimigo);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        //Troquei para switch -> implementando o "Pause" 
        switch (e.getKeyCode()) {
            case KeyEvent.VK_SPACE:
                personagem.atirar();
                break;
            case KeyEvent.VK_ENTER:
                personagem.atirarSuper();
                break;
            case KeyEvent.VK_ESCAPE:
                if (timer.isRunning()) {
                    timer.stop();
                } else {
                    timer.start();
                }
                break;

                case KeyEvent.VK_1:
                    emJogo = true;
                    personagem = new Personagem();
                    inicializaElementosGraficosAdicionais();
                    inicializaInimigos();
                    timer.start();
                break;

                case KeyEvent.VK_L:
                    HibernateUtil.SalvarObjeto(personagem);
                break;
            default:
                personagem.mover(e);
                break;
        }
        // if (e.getKeyCode() == KeyEvent.VK_SPACE)
        //     personagem.atirar();
        // else
        //     personagem.mover(e);

    }

    // @Override
    // public void paint(Graphics g){
    //     super.paint(g);
    // }

    @Override
    public void paint (Graphics g){
        Graphics2D graficos = (Graphics2D) g;
        if (emJogo) {
            graficos.drawImage(fundo, 0, 0, null);

            // Criando um laço de repetição (foreach). Iremos percorrer toda a lista.
            for (Asteroide asteroide : asteroides) {
                // Desenhar o asteroide na nossa tela.
                graficos.drawImage(asteroide.getImagem(), asteroide.getPosicaoEmX(), asteroide.getPosicaoEmY(), this);
            }

            graficos.drawImage(personagem.getImagem(), personagem.getPosicaoEmX(), personagem.getPosicaoEmY(), this);

            // Recuperar a nossa lista de tiros (getTiros) e atribuímos para uma variável
            // local chamada tiros.
            List<Tiro> tiros = personagem.getTiros();

            // Criando um laço de repetição (foreach). Iremos percorrer toda a lista.
            for (Tiro tiro : tiros) {
                // Desenhar o tiro na nossa tela.
                graficos.drawImage(tiro.getImagem(), tiro.getPosicaoEmX(), tiro.getPosicaoEmY(), this);
            }

            //Criei com if para possibilitar apenas um superTiro por vez
            if (personagem.getTiroSuper() != null) {
                TiroSuper tiroSuper = personagem.getTiroSuper();
                graficos.drawImage(tiroSuper.getImagem(),tiroSuper.getPosicaoEmX(), tiroSuper.getPosicaoEmY(), this);
            }

            // Criando um laço de repetição (foreach). Iremos percorrer toda a lista.
            for (Inimigo inimigo : inimigos) {
                // Desenhar o inimigo na nossa tela.
                graficos.drawImage(inimigo.getImagem(), inimigo.getPosicaoEmX(), inimigo.getPosicaoEmY(), this);
            }
            desenhaPontuacao(graficos);
            desenhaVidas(graficos);
            desenhaHitBox(graficos);
        } else {
            ImageIcon fimDeJogo = new ImageIcon(getClass().getResource("/fimdejogo.jpg"));
            graficos.drawImage(fimDeJogo.getImage(), 0, 0, this);
        }
        g.dispose();
    }
  
    @Override
    public void keyReleased(KeyEvent e) {
        personagem.parar(e);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        personagem.atualizar();

        // Criando um laço de repetição (foreach). Iremos percorrer toda a lista.
        for (Asteroide asteroide : this.asteroides) {
            asteroide.atualizar();
        }

        // Recuperar a nossa lista de tiros (getTiros) e atribuímos para uma variável
        // local chamada tiros.
        List<Tiro> tiros = personagem.getTiros();

        // Criando um laço de repetição (for). Iremos percorrer toda a lista.
        for (int i = 0; i < tiros.size(); i++) {
            // Obter o objeto tiro da posicao i do ArrayList
            Tiro tiro = tiros.get(i);
            // Verificar se (if) a posição do x (tiro.getPosicaoEmX()) é maior do que a
            // largura da nossa janela
            if (tiro.getPosicaoEmX() > Principal.LARGURA_DA_JANELA || !tiro.getEhVisivel())
                // Remover da lista se estiver fora do campo de visão (LARGURA_DA_JANELA)
                tiros.remove(tiro);
            else
                // Atualizar a posição do tiro.
                tiro.atualizar();
        }

        if (personagem.getTiroSuper() != null) {
                TiroSuper tiroSuper = personagem.getTiroSuper();
                tiroSuper.atualizar();

            }

        // Criando um laço de repetição (for). Iremos percorrer toda a lista.
        for (int i = 0; i < this.inimigos.size(); i++) {
            // Obter o objeto inimigo da posicao i do ArrayList
            Inimigo inimigo = this.inimigos.get(i);
            // Verificar se (if) a posição do x (inimigo.getPosicaoEmX()) é maior do que a
            // largura da nossa janela
            if (inimigo.getPosicaoEmX() < 0 || !inimigo.getEhVisivel())
                // Remover da lista se estiver fora do campo de visão (0)
                inimigos.remove(inimigo);
            else
                // Atualizar a posição do inimigo.
                inimigo.atualizar();
        }
        this.verificarColisoes();
        repaint();
    }

    public void verificarColisoes() {   
        //O retangulo retornado pela função da classe ElementoGráfico tá gerando uma hitbox ridícula
        //muito maior que a nave visível. Confira mais habilitando a linha 82 (método super.desenhaHitBox()) 
        // e habilitando também o formaPersonagem direto do personagem.getRectangle() (LOGO ABAIXO)
        //Rectangle formaPersonagem = this.personagem.getRectangle(); 

        Rectangle formaPersonagem = new Rectangle(personagem.getPosicaoEmX(), personagem.getPosicaoEmY(), personagem.getLarguraImagem(), personagem.getAlturaImagem());
        
        TiroSuper tiroSuper = this.personagem.getTiroSuper();

        for (int i = 0; i < this.inimigos.size(); i++) {
            Inimigo inimigo = inimigos.get(i);
            Rectangle formaInimigo = inimigo.getRectangle();
            //if (formaInimigo.intersects(tiroSuper.getRectangle()))
            
            if (formaInimigo.intersects(formaPersonagem)) {
                personagem.setVidas();
                inimigo.setEhVisivel(false);
                if (personagem.getVidas() == 0){
                this.personagem.setEhVisivel(false);
                inimigo.setEhVisivel(false);
                emJogo = false;
                }

            }
            List<Tiro> tiros = this.personagem.getTiros();
            for (int j = 0; j < tiros.size(); j++) {
                Tiro tiro = tiros.get(j);
                Rectangle formaTiro = tiro.getRectangle();
                if (formaInimigo.intersects(formaTiro)) {
                    inimigo.setEhVisivel(false);
                    tiro.setEhVisivel(false);
                    personagem.setPontuacao(PONTOS_POR_INIMIGO);
                    //TODO: A conexão com o banco de dados está com problema
                    //HibernateUtil.GetSession().persist(personagem);
                    // HibernateUtil.GetSession().persist(inimigo);
                    // HibernateUtil.GetSession().persist(tiros);
                }
            }
        }
    }

    @Override
    public void inicializaElementosGraficosAdicionais() {
        super.asteroides = new ArrayList<Asteroide>();

        for (int i = 0; i < QTDE_DE_ASTEROIDES; i++) {
            int x = (int) (Math.random() * Principal.LARGURA_DA_JANELA);
            int y = (int) (Math.random() * Principal.ALTURA_DA_JANELA);
            Asteroide asteroide = new Asteroide(x, y);
            super.asteroides.add(asteroide);
        }
    }
}
