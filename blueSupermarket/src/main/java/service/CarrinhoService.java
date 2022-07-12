package service;

import DAO.ProdutoDAO;
import model.Produtos;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class CarrinhoService {
    private List<Produtos> listaProdutos;

    public List<Produtos> getListaProdutos() {
        return listaProdutos;
    }

    public void addProdutoCarrinho(Long id) throws SQLException {
        Produtos produto = new ProdutoDAO().getBuscaPorId(id);
        this.listaProdutos.add(new Produtos(produto.getId(), produto.getNome(), produto.getCategoria(), produto.getDescrição(), produto.getPreco()));

    }

    public List<Produtos> addProdutoCarrinho() {
        return listaProdutos;
    }

}
