package model;

public class Produtos {
    private long id;
    private String nome;
    private String descrição;
    private Double preco;
    private int quant;

    public Produtos() {
    }

    public Produtos(long id, String nome, String descrição, Double preco, int quant) {
        this.id = id;
        this.nome = nome;
        this.descrição = descrição;
        this.preco = preco;
        this.quant = quant;
    }

    public long getId() {
        return idd;
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

    public int getQuant() {
        return quant;
    }

    public void setQuant(int quant) {
        this.quant = quant;
    }
}
