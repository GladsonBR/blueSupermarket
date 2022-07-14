package DAO;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

import factory.Factory;
import model.Produto;

public class ProdutosDao {
	private Statement stm;
	private Factory f;
	
	public ProdutosDao() {
		this.f = new Factory();
		f.setConnection("jdbc:mysql://localhost:3306/bluesupermarket?useTimezone=true&serverTimezone=UTC&useSSL=false");
		this.stm = f.getStatement();
		updateProdutos();
	}
	
	public void close() {
		f.closeConnection();
	}
	
	public void updateProdutos() {
		//1 Checa a existencia da tabela na database
		boolean cont = checkProdutos();
		if(cont==false) {
			return;
		}
		//2 Obtem os dados atraves do arquivo csv e atualiza os valores dentro da tabela
		
		String line = "";
		try {
			BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\User\\Meus Documentos\\Produtos.csv",Charset.forName("ISO-8859-1")));
			while((line = br.readLine())!=null) {
				String[] produto = line.split(",");
				try {
					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
					java.util.Date data = sdf.parse(produto[5]);
					insertProduto(Integer.parseInt(produto[0]),produto[1],produto[2],Double.parseDouble(produto[3]),Integer.parseInt(produto[4]),data);
				}catch(ArrayIndexOutOfBoundsException e) {
					insertProduto(Integer.parseInt(produto[0]),produto[1],produto[2],Double.parseDouble(produto[3]),Integer.parseInt(produto[4]),null);
				}
			}
			br.close();
		}catch(ParseException e) {
			System.out.println("Erro na conversão de datas! (method updateProdutos())");
			System.out.println(e.getMessage());
			return;
			
		}catch(IOException e) {
			System.out.println("Erro na leitura do arquivo CSV! (method updateProdutos())");
			System.out.println(e.getMessage());
			return;
		}
		System.out.println("ATUALIZAÇÃO CONCLUIDA!!");
	}
	
	private boolean checkProdutos() {
		try {
			String sql = "CREATE TABLE produtos(ID int,NOME varchar(150),DESCRICAO varchar(300),PRECO double,QUANTIDADE int, VALIDADE date,PRIMARY KEY(ID))";
			this.stm.execute(sql);
		}catch (SQLException e) {
			String msg = e.getMessage();
			if(msg.equals("Table 'produtos' already exists")) {
				System.out.println("Tabela 'produtos' ja existe, atualizando os itens \n ");
			}else {
				System.out.println("ERRO AO CHECAR EXISTENCIA DA TABELA produtos ! (method checkProdutos())");
				System.out.println(msg);
				return false;
			}
		}
		return true;
	}
	
	public void insertProduto(int ID, String nome, String desc, double preco, int quant, java.util.Date validade) {
		java.sql.Date data = null;
		if(validade!=null) {
			data = converteData(validade);
		}
		String sql = "INSERT INTO produtos(ID,NOME,DESCRICAO,PRECO,QUANTIDADE,VALIDADE) VALUES(?,?,?,?,?,?)";
		try {
			PreparedStatement ps = (PreparedStatement) f.getStatement();
			ps.setInt(1, ID);
			ps.setString(2, nome);
			ps.setString(3, desc);
			ps.setDouble(4, preco);
			ps.setInt(5, quant);
			ps.setDate(6, data);
			ps.execute();
		}catch(SQLException e) {
			if(!(e.getMessage().contains("Duplicate"))){
				System.out.println("ERRO AO INSERIR PRODUTO "+nome+"! (method insertProduto())");
				System.out.println(e.getMessage());
			}
		}
	}
	
	private java.sql.Date converteData(java.util.Date data){
		return new java.sql.Date(data.getTime());
	}
	
	
	public void updateQuant(int ID, int quant){
		String sql = "UPDATE produtos SET QUANTIDADE = "+quant;
		try {
			PreparedStatement ps = (PreparedStatement) f.getStatement();
			ps.execute();
		}catch(SQLException e) {
			System.out.println("ERRO AO ATUALIZAR A TABELA! (method updateQuant())");
			System.out.println(e.getMessage());
		}
	}
	
	public ArrayList<Produto> getProdutos(){
		ArrayList<Produto> produtos = new ArrayList<Produto>();
		String sql = "SELECT * FROM produtos";
		
		try {
			PreparedStatement ps = (PreparedStatement) this.f.getStatement();
			ps.execute();
			ResultSet rs = ps.getResultSet();
			while(rs.next()) {
				Produto produto = new Produto(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getDouble(4),rs.getInt(5),rs.getDate(6));
				produtos.add(produto);
			}
			return produtos;
		}catch(SQLException e) {
			System.out.println("ERRO AO OBTER PRODUTO! (method getProdutos())");
			System.out.println(e.getMessage());
			return null;
		}
	}
}
