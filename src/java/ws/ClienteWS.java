package ws;

import entidade.Cliente;
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
import rn.ClienteRN;

/**
 * REST Web Service
 *
 * @author cardoso
 */
@Path("cliente")
public class ClienteWS {
    private ClienteRN clienteRN;
    
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of Cliente
     */
    public ClienteWS() {
        clienteRN = new ClienteRN();
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Cliente> getListaClientes() {
        return clienteRN.listar();
    }   
    
    //@GET //para buscar o objeto pelo id
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Cliente getClientePorId(@PathParam("id") Long id) {
        return clienteRN.buscarPorId(id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Cliente insereCliente(Cliente cliente, 
            @Context HttpServletResponse response){
        cliente = clienteRN.inserir(cliente);
        
        
        response.setStatus(HttpServletResponse.SC_CREATED); 
        try {
            response.flushBuffer();
        } catch (IOException ex) {
            throw new InternalServerErrorException();
        }
        return(cliente);
    }  
    
        //@PUT - atualizar
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Cliente atualizaCliente(@PathParam("id") Long id, Cliente cliente){
        cliente.setId(id);
        return clienteRN.atualizar(cliente);
    }
    
    //@DELETE - remover
    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Cliente removeCliente(@PathParam("id") Long id){
        return clienteRN.deletar(id);
    }
}
