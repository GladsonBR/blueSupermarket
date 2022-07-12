package service;

import model.Produtos;

import java.util.LinkedList;
import java.util.List;

public class CarrinhoService {
    private List<Produtos> listaProdutos;

    public List<Produtos> getListaProdutos() {
        return listaProdutos;
    }

    public void addProdutoCarrinho(Long id) {
        Produtos produto = new ProdutoDAO().getBuscaPorId(id);
        this.listaProdutos.add(new Produtos(produto.getId(), produto.getNome(), produto.getDescrição(), produto.getPreco(), produto.getQuant()));

    }

    public List<Produtos> addProdutoCarrinho() {
        return listaProdutos;
    }

}
