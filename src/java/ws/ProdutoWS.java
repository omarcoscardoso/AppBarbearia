package ws;

import entidade.Produto;
import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import rn.ProdutoRN;

/**
 * REST Web Service
 *
 * @author cardoso
 */
@Path("produto")
public class ProdutoWS {
    private ProdutoRN produtoRN;

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of ProdutoWS
     */
    public ProdutoWS() {
        produtoRN = new ProdutoRN();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Produto> getListaProdutos() {
        return produtoRN.listar();
    }   
    
    //@GET //para buscar o objeto pelo id
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Produto getProdutoPorId(@PathParam("id") Long id) {
        return produtoRN.buscarPorId(id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Produto insereProduto(Produto produto, 
            @Context HttpServletResponse response){
        produto = produtoRN.inserir(produto);
        
        
        response.setStatus(HttpServletResponse.SC_CREATED); 
        try {
            response.flushBuffer();
        } catch (IOException ex) {
            throw new InternalServerErrorException();
        }
        return(produto);
    }  
    
        //@PUT - atualizar
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Produto atualizaProduto(@PathParam("id") Long id, Produto produto){
        produto.setId(id);
        return produtoRN.atualizar(produto);
    }
    
    //@DELETE - remover
    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Produto removeProduto(@PathParam("id") Long id){
        return produtoRN.deletar(id);
    }
}
