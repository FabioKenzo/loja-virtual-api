package br.com.kenzowebstudio.loja_virtual.model;

import java.io.Serializable;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Embeddable;

@Embeddable
@Schema(name = "ChaveItemPedido", description = "Chave composta que vincula um Pedido a um Produto")
public class PedidoProdutoId implements Serializable {

    @Schema(description = "ID do Pedido vinculado", example = "10")
    private Integer pedidoId; 

    @Schema(description = "ID do Produto vinculado", example = "5")
    private Integer produtoId;

    //gets e sets
    public Integer getPedidoId() {
        return pedidoId;
    }
    public void setPedidoId(Integer pedidoId) {
        this.pedidoId = pedidoId;
    }
    public Integer getProdutoId() {
        return produtoId;
    }
    public void setProdutoId(Integer produtoId) {
        this.produtoId = produtoId;
    }

    //equals e hash
    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(!(o instanceof PedidoProdutoId)) return false; 
        PedidoProdutoId that = (PedidoProdutoId) o; 
         return pedidoId.equals(that.pedidoId) && produtoId.equals(that.produtoId);
    }


    @Override
    public int hashCode(){
        return pedidoId.hashCode() + produtoId.hashCode();
    }

}
