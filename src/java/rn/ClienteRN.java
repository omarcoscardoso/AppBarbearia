package rn;

import entidade.Cliente;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import util.JPAUtil;

/**
 *
 * @author cardoso
 */
public class ClienteRN {
    
    public Cliente inserir(Cliente cliente){
        EntityManager manager = JPAUtil.getManager();
        
        manager.getTransaction().begin();
        manager.persist(cliente);
        manager.getTransaction().commit();
        
        manager.close();
        return cliente;
    }
    
      public Cliente buscarPorId(Long id){
        EntityManager manager = JPAUtil.getManager();
            
        Cliente cliente = manager.find(Cliente.class, id);
        
        manager.close();
        return cliente;
    }
    
    public List<Cliente> listar(){
        EntityManager manager = JPAUtil.getManager();
        
        Query query = manager.createQuery("select m from Cliente m");
        List<Cliente> listaClientes = query.getResultList();
        
        manager.close();        
        return listaClientes;
    }
    
    public Cliente atualizar(Cliente cliente){
        EntityManager manager = JPAUtil.getManager();
        
        manager.getTransaction().begin();
        manager.merge(cliente);
        manager.getTransaction().commit();
        
        manager.close();
        return cliente;        
    }
    
    public Cliente deletar(Long id){
        EntityManager manager = JPAUtil.getManager();
        
        manager.getTransaction().begin();
        Cliente cliente = manager.find(Cliente.class, id);
        manager.remove(cliente);
        manager.getTransaction().commit();
        
        manager.close();
        return cliente;
    }
   
}
