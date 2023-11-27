package ifpr.paranavai.jogo.conexao;

import java.util.List;
import java.util.Properties;

import javax.persistence.criteria.CriteriaDelete;

import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.boot.registry.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import ifpr.paranavai.jogo.modelo.ElementoGrafico;
import ifpr.paranavai.jogo.modelo.Inimigo;
import ifpr.paranavai.jogo.modelo.Jogador;
import ifpr.paranavai.jogo.modelo.Personagem;
import ifpr.paranavai.jogo.modelo.Tiro;
import ifpr.paranavai.jogo.modelo.TiroSuper;

public class HibernateUtil {
    private static SessionFactory SESSION_FACTORY;

    static {
        try {
            //Criação da SessionFactory a partir do hibernate.cfg.xml
            //SESSION_FACTORY = new Configuration().configure().buildSessionFactory();
            
            Configuration config = new Configuration();
            Properties p = new Properties();
            p.setProperty(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
            p.setProperty(Environment.URL, "jdbc:mysql://localhost:3306/bd_jogo");
            p.setProperty(Environment.USER, "root");
            p.setProperty(Environment.PASS, "shig");
            p.setProperty(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
            p.setProperty(Environment.SHOW_SQL, "true");
            p.setProperty(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
            p.setProperty(Environment.HBM2DDL_AUTO, "update");

            config.setProperties(p);

            config.addAnnotatedClass(Jogador.class);
            config.addAnnotatedClass(Personagem.class);
            //config.addAnnotatedClass(Tiro.class);
            //config.addAnnotatedClass(ElementoGrafico.class);
            
            //config.addAnnotatedClass(teste.class);
            //config.addAnnotatedClass(testedoteste.class);
            // config.addAnnotatedClass(TiroSuper.class);


            StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
            .applySettings(config.getProperties()).build();
        
            SESSION_FACTORY = config.buildSessionFactory(serviceRegistry);
        
        } catch (Throwable ex){
            System.err.print("\n---------------------------\nCriação inicial da SessionFactory Falhou  \n---------------------------\n");
            try {
                throw new Exception(ex.getCause());
            } catch (Exception e) {

                System.err.print("\n\n ------------ \n\n");

                e.printStackTrace();

                System.err.print("\n\n ------------ \n\n");

                System.err.print("\n\n "+ e +"\n\n");

                System.err.print("\n\n ------------ \n\n");

                throw new ExceptionInInitializerError(e.toString());
            }
            
            //throw new ExceptionInInitializerError();
        }
    }

    public static org.hibernate.Session GetSession(){
        return SESSION_FACTORY.openSession();
    }

    public static void encerraSessao(){
        SESSION_FACTORY.close();
        if (SESSION_FACTORY.isClosed()) {
            return;
        }
    }

    // private static void VerificaExistencia () {
    //     Session session = GetSession();
    //     session.delete(o);
    // }
    public static void SalvarObjeto(List<Object> o){
        Session session = GetSession();
        //session.delete(o);
        session.beginTransaction();
        for (Object object : o) {
        session.save(object);    
        }
        session.getTransaction().commit();
        session.close();
        //encerraSessao();
    }

    public static void SalvarObjeto(Object o){
        Session session = GetSession();
        //session.delete(o);
        session.beginTransaction();
        session.save(o);    
        session.getTransaction().commit();
        session.close();
        //encerraSessao();
    }
}
