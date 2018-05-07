package ws;

import entidade.Promocao;
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
import rn.PromocaoRN;

/**
 * REST Web Service
 *
 * @author cardoso
 */
@Path("promocao")
public class PromocaoWS {

    private PromocaoRN promocaoRN;

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of PromocaoWS
     */
    public PromocaoWS() {
        promocaoRN = new PromocaoRN();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Promocao> getListaPromocao() {
        return promocaoRN.listar();
    }   
    
    //@GET //para buscar o objeto pelo id
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Promocao getPromocaoPorId(@PathParam("id") Long id) {
        return promocaoRN.buscarPorId(id);
    }

    //@POST //para inserir objeto
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Promocao inserePromocao(Promocao promocao, 
            @Context HttpServletResponse response){
        promocao = promocaoRN.inserir(promocao);
        
        response.setStatus(HttpServletResponse.SC_CREATED); 
        try {
            response.flushBuffer();
        } catch (IOException ex) {
            throw new InternalServerErrorException();
        }
        return(promocao);
    }  
 
    //@PUT //para alterar objeto por id
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Promocao atualizaPromocao(@PathParam("id") Long id, Promocao promocao){
        promocao.setId(id);
        return promocaoRN.atualizar(promocao);
    }
    
    //@DELETE - remover
    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Promocao removePromocao(@PathParam("id") Long id){
        return promocaoRN.deletar(id);
    }
}
