package servlet;

import model.Produtos;
import service.CarrinhoService;

import javax.servlet.annotation.WebServlet;
import java.util.List;

@WebServlet("/carrinho/")
public class CarrinhoServlet extends HttpS{

    public List<Produtos> getListaProdutos(){
        List<Produtos> listProdutos = new CarrinhoService().getListaProdutos();
        return listProdutos;
    }
}
