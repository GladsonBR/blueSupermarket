package DAO;

import factory.ConnectionFactory;
import model.Produtos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class ProdutoDAO {
    private Connection connection;

    public ProdutoDAO() throws SQLException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        this.connection = connectionFactory.recuperarConexao();
    }
    public Produtos getBuscaPorId(Long id) throws SQLException {
        Produtos produto = null;
        String sql="SELECT IDPRODUTO, NOMEPROD,CATEGORIA,DESCRICAO,VALORPRODUTO FROM produtos WHERE idProduto = ? ";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setLong(1,id);
        pstm.execute();
        ResultSet result = pstm.getResultSet();
        while(result.next()){
            long idProd = result.getLong("IDPRODUTO");
            String nome= result.getString("NOMEPROD");
            String categoria= result.getString("CATEGORIA");
            String descricao=result.getString("DESCRICAO");
            double valor= result.getDouble("VALORPRODUTO");

            produto = new Produtos(idProd,nome,categoria,descricao,valor);
        }
        return produto;
    }

    public List<Produtos> listaProdutos() throws SQLException {
        List<Produtos> listaProdutos = new LinkedList<>();
        String sql="SELECT IDPRODUTO, NOMEPROD,CATEGORIA,DESCRICAO,VALORPRODUTO FROM PRODUTOS";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.execute();
        ResultSet result = pstm.getResultSet();
        while(result.next()){
            long idProd = result.getLong("IDPRODUTO");
            String nome= result.getString("NOMEPROD");
            String categoria= result.getString("CATEGORIA");
            String descricao=result.getString("DESCRICAO");
            double valor= result.getDouble("VALORPRODUTO");

            listaProdutos.add(new Produtos(idProd,nome,categoria,descricao,valor));
        }
        return listaProdutos;

    }
}
