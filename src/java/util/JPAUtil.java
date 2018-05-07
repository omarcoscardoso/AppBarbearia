
package util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author cardoso
 */
public class JPAUtil {
     
    private static EntityManagerFactory factory = 
            Persistence.createEntityManagerFactory("AppBarbeariaPU");
     
    public static EntityManager getManager(){
        return factory.createEntityManager();
    }
}
