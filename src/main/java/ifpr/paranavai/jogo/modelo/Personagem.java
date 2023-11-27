package ifpr.paranavai.jogo.modelo;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.swing.ImageIcon;


@Entity
public class Personagem extends ElementoGrafico {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private int idPersonagem;

    @Transient
    private static final int DESLOCAMENTO = 3;
    @Transient
    private static final int POSICAO_INICIAL_EM_X = 100;
    @Transient
    private static final int POSICAO_INICIAL_EM_Y = 100;
    
    private static final int VIDAS = 3;

    // @Id
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    // private int id;
    
    //@Column(name = "deslocamento_em_x")
    @Transient
    private int deslocamentoEmX;
    
    //@Column(name = "deslocamento_em_y")
    @Transient 
    private int deslocamentoEmY;
    //@Column(name = "pontuacao")
    //@Transient
    private int pontuacao;
    //@Column(name = "vidas")
    //@Transient
    private int vidas;

    //@OneToMany(mappedBy = "Personagem")
    @Transient
    private List<Tiro> tiros;

    //@OneToOne
    @Transient
    private TiroSuper tiroSuper;

    // Cria a relacao entre personagem e jogoSalvo
    //@OneToOne(mappedBy = "personagem", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    //private JogoSalvo jogoSalvo;

    public Personagem() {
        this.carregar();
        super.setPosicaoEmX(POSICAO_INICIAL_EM_X);
        super.setPosicaoEmY(POSICAO_INICIAL_EM_Y);
        this.vidas = VIDAS;
        this.tiros = new ArrayList<Tiro>();
        //this.tiroSuper = new TiroSuper(POSICAO_INICIAL_EM_X, POSICAO_INICIAL_EM_Y);
    }

    public void carregar() {
        ImageIcon carregando = new ImageIcon(getClass().getResource("/espaconave.png"));
        super.setImagem(carregando.getImage());
    }

    public void atualizar() {
        if (VerificaLocalVertical()) {
            super.setPosicaoEmY(super.getPosicaoEmY() + this.deslocamentoEmY);
        }
        if (VerificaLocalHorizontal()) {
            super.setPosicaoEmX(super.getPosicaoEmX() + this.deslocamentoEmX);
        }
    }

    private boolean VerificaLocalVertical (){
        int distanciaImagem = this.getAlturaImagem();
        int pontoSuperior = this.getPosicaoEmY() + deslocamentoEmY - distanciaImagem + 52;  //Ajustes finos
        int pontoInferior = this.getPosicaoEmY() + deslocamentoEmY + distanciaImagem +40;   //Ajustes finos

        if (pontoSuperior > 0 && pontoInferior < 728){
            return true;
        } else {
            return false;
        }
    }

    private boolean VerificaLocalHorizontal (){
        int distanciaImagem = this.getLarguraImagem();
        int pontoEsquerdo = this.getPosicaoEmX() + deslocamentoEmX - distanciaImagem + 150; //Ajustes finos
        int pontoDireito = this.getPosicaoEmX() + deslocamentoEmX + distanciaImagem +16;    //Ajustes finos

        if (pontoEsquerdo > 0 && pontoDireito < 1024){
            return true;
        } else {
            return false;
        }
    }


    public void atirar() {
        int frenteDaNave = super.getPosicaoEmX() + super.getLarguraImagem();
        int meioDaNave = super.getPosicaoEmY() + (super.getAlturaImagem() / 2);
        Tiro tiro = new Tiro(frenteDaNave, meioDaNave);
        this.tiros.add(tiro);
    }

    public void atirarSuper() {
        int frenteDaNave = super.getPosicaoEmX() + super.getLarguraImagem();
        int meioDaNave = super.getPosicaoEmY() + (super.getAlturaImagem() / 2);
        TiroSuper tiro = new TiroSuper(frenteDaNave, meioDaNave);
        this.tiroSuper = tiro;
    }

    public void mover(KeyEvent tecla) {
        int codigo = tecla.getKeyCode();
        switch (codigo) {

            case KeyEvent.VK_W:
            case KeyEvent.VK_UP:
                this.deslocamentoEmY = -DESLOCAMENTO;
                break;

            case KeyEvent.VK_S:
            case KeyEvent.VK_DOWN:
                this.deslocamentoEmY = DESLOCAMENTO;
                break;

            case KeyEvent.VK_A:
            case KeyEvent.VK_LEFT:
                this.deslocamentoEmX = -DESLOCAMENTO;
                break;

            case KeyEvent.VK_D:
            case KeyEvent.VK_RIGHT:
                this.deslocamentoEmX = DESLOCAMENTO;
                break;

            default:
                break;
        }
    }

    public void parar(KeyEvent tecla) {
        int codigo = tecla.getKeyCode();
        switch (codigo) {

            case KeyEvent.VK_W:
            case KeyEvent.VK_UP:
                this.deslocamentoEmY = 0;//-DESLOCAMENTO;
                break;

            case KeyEvent.VK_S:
            case KeyEvent.VK_DOWN:
                this.deslocamentoEmY = 0;//DESLOCAMENTO;
                break;

            case KeyEvent.VK_A:
            case KeyEvent.VK_LEFT:
                this.deslocamentoEmX = 0;//-DESLOCAMENTO;
                break;

            case KeyEvent.VK_D:
            case KeyEvent.VK_RIGHT:
                this.deslocamentoEmX = 0;//DESLOCAMENTO;
                break;

            default:
                break;
        }
    }

    // public void parar() {
    //     // deslocamentoEmY = 0;
    //     // deslocamentoEmX = 0;
    // }

    public TiroSuper getTiroSuper() {
        return this.tiroSuper;
    }

    public int getDeslocamentoEmY() {
        return this.deslocamentoEmY;
    }

    public int getDeslocamentoEmX() {
        return this.deslocamentoEmX;
    }

    public void setDeslocamentoEmX(int deslocamentoEmX) {
        this.deslocamentoEmX = deslocamentoEmX;
    }

    public List<Tiro> getTiros() {
        return this.tiros;
    }

    public void setSuper(TiroSuper atirar) {
            this.tiroSuper = atirar;
    }

    public void setTiros(ArrayList<Tiro> tiros) {
        this.tiros = tiros;
    }

    public void setDeslocamentoEmY(int deslocamentoEmY) {
        this.deslocamentoEmY = deslocamentoEmY;
    }

    public int getPontuacao() {
        return this.pontuacao;
    }

    public void setPontuacao(int pontuacao) {
        this.pontuacao += pontuacao;
    }

    public void setVidas(){
        this.vidas -= 1;
    }

    public int getVidas(){
        return this.vidas;
    }

}