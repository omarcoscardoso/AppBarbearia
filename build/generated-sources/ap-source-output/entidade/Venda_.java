package entidade;

import entidade.Cliente;
import entidade.Produto;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-05-07T20:02:34")
@StaticMetamodel(Venda.class)
public class Venda_ { 

    public static volatile SingularAttribute<Venda, Cliente> cliente;
    public static volatile CollectionAttribute<Venda, Produto> produto;
    public static volatile SingularAttribute<Venda, String> data_venda;
    public static volatile SingularAttribute<Venda, Long> id;
    public static volatile SingularAttribute<Venda, Integer> quantidade;

}