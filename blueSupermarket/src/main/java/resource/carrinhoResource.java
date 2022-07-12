package resource;

import DAO.CarrinhoDao;
import model.Carrinho;
import model.Produtos;
import service.CarrinhoService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;
import java.util.List;

@Path("/carrinho")
@Produces(MediaType.APPLICATION_JSON)
public class carrinhoResource {

    @GET
    @Path("/{id}")
    public void getProdutoId(@PathParam("id") long id){
        new CarrinhoService().addProdutoCarrinho(id);
    }


    @GET
    @Path("/delete/{id}")
    public void delProdutoId(@PathParam("id") long idDel){
        List<Produtos> listProdutos = new CarrinhoService().getListaProdutos();
        listProdutos.remove(idDel);
    }

    @POST
    @Path("/salvarCarrinho")
    @Consumes(MediaType.APPLICATION_JSON)
    public void salvarCarrinho(@FormParam("carrinho")Carrinho carrinho) throws SQLException {
        new CarrinhoDao().inserirCompra(carrinho);

    }
}
