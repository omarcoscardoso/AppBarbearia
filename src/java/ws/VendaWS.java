/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import entidade.Venda;
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
import rn.VendaRN;

/**
 * REST Web Service
 *
 * @author cardoso
 */
@Path("venda")
public class VendaWS {
private VendaRN vendaRN;

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of ProdutoWS
     */
    public VendaWS() {
        vendaRN = new VendaRN();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Venda> getListaVendas() {
        return vendaRN.listar();
    }   
    
    //@GET //para buscar o objeto pelo id
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Venda getVendaPorId(@PathParam("id") Long id) {
        return vendaRN.buscarPorId(id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Venda insereVenda(Venda venda, 
            @Context HttpServletResponse response){
        venda = vendaRN.inserir(venda);
        
        response.setStatus(HttpServletResponse.SC_CREATED); 
        try {
            response.flushBuffer();
        } catch (IOException ex) {
            throw new InternalServerErrorException();
        }
        return(venda);
    }  
    
        //@PUT - atualizar
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Venda atualizaVenda(@PathParam("id") Long id, Venda venda){
        venda.setId(id);
        return vendaRN.atualizar(venda);
    }
    
    //@DELETE - remover
    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Venda removeVenda(@PathParam("id") Long id){
        return vendaRN.deletar(id);
    }
}
