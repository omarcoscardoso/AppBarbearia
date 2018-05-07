package rn;

import entidade.Promocao;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import util.JPAUtil;

/**
 *
 * @author cardoso
 */
public class PromocaoRN {
       public Promocao inserir(Promocao promocao){
        EntityManager manager = JPAUtil.getManager();
        
        manager.getTransaction().begin();
        manager.persist(promocao);
        manager.getTransaction().commit();
        
        manager.close();
        return promocao;
    }
    
      public Promocao buscarPorId(Long id){
        EntityManager manager = JPAUtil.getManager();
            
        Promocao promocao = manager.find(Promocao.class, id);
        
        manager.close();
        return promocao;
    }
    
    public List<Promocao> listar(){
        EntityManager manager = JPAUtil.getManager();
        
        Query query = manager.createQuery("select p from Promocao p");
        List<Promocao> listaPromocao = query.getResultList();
        
        manager.close();        
        return listaPromocao;
    }
    
    public Promocao atualizar(Promocao promocao){
        EntityManager manager = JPAUtil.getManager();
        
        manager.getTransaction().begin();
        manager.merge(promocao);
        manager.getTransaction().commit();
        
        manager.close();
        return promocao;        
    }
    
    public Promocao deletar(Long id){
        EntityManager manager = JPAUtil.getManager();
        
        manager.getTransaction().begin();
        Promocao promocao = manager.find(Promocao.class, id);
        manager.remove(promocao);
        manager.getTransaction().commit();
        
        manager.close();
        return promocao;
    }
}
