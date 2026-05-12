package br.com.kenzowebstudio.loja_virtual.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;

@Entity
@Schema(name = "ItemPedido", description = "Detalha cada item associado a um pedido, incluindo a quantidade")
public class PedidoProduto {

    @EmbeddedId
    @Schema(hidden = true) //esconde o id composto porque tem maps id
    private PedidoProdutoId id = new PedidoProdutoId();

    @ManyToOne
    @MapsId("pedidoId")
    @JoinColumn(name = "pedido_id", nullable = false)
    @JsonBackReference
    @Schema(description = "Pedido ao qual este item pertence", accessMode = Schema.AccessMode.READ_ONLY)
    private Pedido pedido; 

    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("produtoId")
    @JoinColumn(name = "produto_id", nullable = false)
    @Schema(description = "Produto que está sendo comprado")
    private Produto produto; 

    @Column(nullable = false)
    @Schema(description = "Quantidade comprada deste produto específico", example = "3", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer quantidade;

    //construtor padrao jpa 
    public PedidoProduto(){

    }

    //construtor auxiliar 
    //preenche  os atributos, sincoriniza com os ids na chave compoto automaticamente 
    public PedidoProduto(Pedido pedido, Produto produto, Integer quantidade) {
        this.pedido = pedido; 
        this.produto = produto; 
        this.quantidade = quantidade; 

        // sincronizando com seguranca pra evitar o erro de "pedido is null"
        if (pedido != null && pedido.getId() != null) {
            this.id.setPedidoId(pedido.getId());
        }
        
        if (produto != null && produto.getId() != null) {
            this.id.setProdutoId(produto.getId());
        }
    }

    //gets e sets
    public PedidoProdutoId getId() {
        return id;
    }

    public void setId(PedidoProdutoId id) {
        this.id = id;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    } 
}
