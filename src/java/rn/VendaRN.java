package rn;

import entidade.Venda;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import util.JPAUtil;

/**
 *
 * @author cardoso
 */
public class VendaRN {
      public Venda inserir(Venda venda) {
        EntityManager manager = JPAUtil.getManager();
    
        manager.getTransaction().begin();
        manager.persist(venda);
        manager.getTransaction().commit();
        
        manager.close();
        return venda; 
    }
    
    public Venda buscarPorId(Long id) {
        EntityManager manager = JPAUtil.getManager();
            
        Venda venda = manager.find(Venda.class, id);
        
        manager.close();
        return venda;
    }
    
    public List<Venda> listar() {
        EntityManager manager = JPAUtil.getManager();
        
        Query query = manager.createQuery("select p from Venda p");
        List<Venda> listaVenda = query.getResultList();
        
        manager.close();        
        return listaVenda; 
    }    

    public Venda atualizar(Venda venda) {
        EntityManager manager = JPAUtil.getManager();
        
        manager.getTransaction().begin();
        manager.merge(venda);
        manager.getTransaction().commit();
        
        manager.close();
        return venda;      
    }

    public Venda deletar(Long id) {
         EntityManager manager = JPAUtil.getManager();
        
        manager.getTransaction().begin();
        Venda venda = manager.find(Venda.class, id);
        manager.remove(venda);
        manager.getTransaction().commit();
        
        manager.close();
        return venda;
    }
}
