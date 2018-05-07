package rn;

import entidade.Produto;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import util.JPAUtil;

/**
 *
 * @author cardoso
 */
public class ProdutoRN {
    public Produto inserir(Produto produto) {
        EntityManager manager = JPAUtil.getManager();
    
        manager.getTransaction().begin();
        manager.persist(produto);
        manager.getTransaction().commit();
        
        manager.close();
        return produto; 
    }
    
    public Produto buscarPorId(Long id) {
        EntityManager manager = JPAUtil.getManager();
            
        Produto produto = manager.find(Produto.class, id);
        
        manager.close();
        return produto;
    }
    
    public List<Produto> listar() {
        EntityManager manager = JPAUtil.getManager();
        
        Query query = manager.createQuery("select p from Produto p");
        List<Produto> listaProduto = query.getResultList();
        
        manager.close();        
        return listaProduto; 
    }    

    public Produto atualizar(Produto produto) {
        EntityManager manager = JPAUtil.getManager();
        
        manager.getTransaction().begin();
        manager.merge(produto);
        manager.getTransaction().commit();
        
        manager.close();
        return produto;      }

    public Produto deletar(Long id) {
         EntityManager manager = JPAUtil.getManager();
        
        manager.getTransaction().begin();
        Produto produto = manager.find(Produto.class, id);
        manager.remove(produto);
        manager.getTransaction().commit();
        
        manager.close();
        return produto;
    }
    
}
