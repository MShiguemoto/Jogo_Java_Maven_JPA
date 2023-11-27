package ifpr.paranavai.jogo.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import ifpr.paranavai.jogo.conexao.HibernateUtil;
import ifpr.paranavai.jogo.modelo.JogoSalvo;

public class JogoSalvoDaoImpl implements JogoSalvoDao{
    private Session sessao;
    
    public JogoSalvoDaoImpl(){
        this.sessao = HibernateUtil.GetSession();
    }

    @Override
    public void salvarJogo(JogoSalvo jogoSalvo){
        Transaction transaction = null;
        try {
            transaction = sessao.beginTransaction();
            sessao.saveOrUpdate(jogoSalvo);
            transaction.commit();
        } catch (HibernateException e) {
            if(transaction != null){
                transaction.rollback();
            }
            e.printStackTrace();
        } finally{
            sessao.close();
        }
    }

    @Override
    public JogoSalvo carregarJogoSalvo(Integer id){
        JogoSalvo jogoSalvoCarregado = sessao.get(JogoSalvo.class, id);
        sessao.close();
        return jogoSalvoCarregado;
    }
}
