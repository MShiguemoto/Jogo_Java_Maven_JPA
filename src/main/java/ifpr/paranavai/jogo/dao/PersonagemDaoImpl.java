package ifpr.paranavai.jogo.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import ifpr.paranavai.jogo.conexao.HibernateUtil;
import ifpr.paranavai.jogo.modelo.Personagem;

public class PersonagemDaoImpl implements PersonagemDao {
    private Session sessao;

    public PersonagemDaoImpl(){
        // Cria a sessao do hibernate
        // Garante que a sessao esteja disponível ao criar uma instancia do DAO
        this.sessao = HibernateUtil.GetSession();
    }

    @Override
    public void salvarPersonagem(Personagem personagem){
        Transaction transaction = null;
        try {
            // Garante consistencia antes de executar as operacoes do banco
            transaction = sessao.beginTransaction();

            // Salva ou atualiza o objeto personagem no banco
            sessao.saveOrUpdate(personagem);

            // Confirma a transacao
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null){
                // Caso ocorra uma excecao, eh realizado o rollback para desfazer as alteracoes pendentes
                transaction.rollback();
            }
            e.printStackTrace();
        } finally{
            // Garante que a sessao foi fechada
            sessao.close();
        }
    }

    @Override
    public Personagem carregarPersonagem(Integer id){
        // Carrega o personagem com id fornecido 
        Personagem personagemCarregado = sessao.get(Personagem.class, id);

        // Garante que a sessao foi fechada
        sessao.close();

        // Retorna o personagem
        return personagemCarregado; 
    }
}
