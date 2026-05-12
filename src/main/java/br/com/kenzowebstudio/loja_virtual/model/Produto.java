package br.com.kenzowebstudio.loja_virtual.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
@Schema(name = "Produto", description = "Representa um item do catálogo da loja")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador único do produto", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Integer id;
    
    @Column(nullable = false, length = 100)
    @Schema(description = "Nome do produto", example = "Teclado Mecânico RGB", requiredMode = Schema.RequiredMode.REQUIRED)
    private String nome; 

    @Column(length = 1000)
    @Schema(description = "Descrição detalhada sobre o produto", example = "Teclado 60% switch red...")
    private String descricao; 

    @Column(nullable = false)
    @Schema(description = "Preço de venda unitário", example = "299.90", requiredMode = Schema.RequiredMode.REQUIRED)
    private Double preco; 

    @Column(nullable = false)
    @Schema(description = "Quantidade disponível em estoque", example = "50", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer estoque; 


    //relacao avalicao 
    @OneToMany(mappedBy = "produto", cascade = CascadeType.ALL)
    @JsonIgnore
    @Schema(hidden = true) //esconde do schema no swagger para nao confundir o user
    private List<PedidoProduto> pedidos = new ArrayList<>();


    @OneToMany(mappedBy = "produto", cascade = CascadeType.ALL)
    @com.fasterxml.jackson.annotation.JsonManagedReference(value = "produto-avaliacao") // lado de cima da relacao
    @Schema(description = "Lista de avaliações que o produto recebeu")
    private List<Avaliacao> avaliacoes = new ArrayList<>();


    //construtor padrao 
    public Produto(){
        
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Integer getEstoque() {
        return estoque;
    }

    public void setEstoque(Integer estoque) {
        this.estoque = estoque;
    }

    public List<PedidoProduto> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<PedidoProduto> pedidos) {
        this.pedidos = pedidos;
    }

    public List<Avaliacao> getAvaliacoes() {
        return avaliacoes;
    }

    public void setAvaliacoes(List<Avaliacao> avaliacoes) {
        this.avaliacoes = avaliacoes;
    }

}