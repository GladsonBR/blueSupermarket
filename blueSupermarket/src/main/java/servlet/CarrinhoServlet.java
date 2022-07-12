package servlet;

import DAO.ProdutoDAO;
import model.Produtos;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

@WebServlet("/carrinho/lista")
public class CarrinhoServlet extends HttpServlet {   
	private static final long serialVersionUID = 1L;
	List<Produtos> listProdutos= new LinkedList<>();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            listProdutos.addAll(new ProdutoDAO().listaProdutos());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        for (Produtos produt:listProdutos ) {
            System.out.println(produt.getNome());
        }
        request.setAttribute("listaProdutos", listProdutos);
        request.getRequestDispatcher("/WEB-INF/views/listaProdutos.jsp").forward(request,response);

    }
}
