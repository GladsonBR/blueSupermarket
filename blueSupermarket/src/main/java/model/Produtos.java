package model;

public class Produtos {
    private long id;
    private String nome;
    private String categoria;
    private String descrição;
    private Double preco;
//    private int quant;

    public Produtos() {
    }

    public Produtos(long id, String nome, String categoria,String descrição, Double preco) {
        this.id = id;
        this.nome = nome;
        this.categoria = categoria;
        this.descrição = descrição;
        this.preco = preco;

    }

    public long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescrição() {
        return descrição;
    }

    public void setDescrição(String descrição) {
        this.descrição = descrição;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}
