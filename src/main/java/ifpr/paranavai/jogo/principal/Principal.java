package ifpr.paranavai.jogo.principal;

import javax.swing.*;

import ifpr.paranavai.jogo.modelo.FaseUm;

public class Principal extends JFrame {
    public static final int LARGURA_DA_JANELA = 1024;
    public static final int ALTURA_DA_JANELA = 728;
    public static FaseUm fase;

    public Principal() {

        super.setTitle("Jogo do IFPR - Campus Paranavaí - Shiguemoto");
        super.setSize(LARGURA_DA_JANELA, ALTURA_DA_JANELA);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setLocationRelativeTo(null);
        super.setResizable(false);

         
        // JPopupMenu j = new JPopupMenu();
        // j.setName("TESTE TESTE TESTE");
        // j.setBackground(Color.BLACK);
        // //j.
        // //j.setLocation(LARGURA_DA_JANELA/2, LARGURA_DA_JANELA/2);
        // //j.setBackground(Color.BLACK);
        // //j.setSize(20,10);
        // JMenuItem item = new JMenuItem("teste");
        // item.setSize(50, 100);
        // j.add(item);
        // j.setEnabled(true);
        // j.setBounds(100, 100, 100, 100);
        // //super.add(j);
        // j.setLabel("TESTESTESTE");
        // //j.insert(item, LARGURA_DA_JANELA);
        // super.add(j);
        //  //Frame teste = new Frame();
        // // teste.setSize(100, 200);
        // // teste.setLocationRelativeTo(null); 
        //  //teste.add(j);
        //  //super.add(j);
        // // teste.setBackground(Color.black);
        // // teste.setVisible(true);

        fase = new FaseUm();
        super.add(fase);

        super.setVisible(true);
    }

    public static void main(String[] args) {
        new Principal();
        // Session s = HibernateUtil.GetSession();
        // s.persist(fase);
    }

}