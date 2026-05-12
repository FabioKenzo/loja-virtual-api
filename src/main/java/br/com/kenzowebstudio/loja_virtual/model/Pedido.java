package br.com.kenzowebstudio.loja_virtual.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;

@Entity
@Schema(name = "Pedido", description = "Representa uma venda realizada no sistema")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador único do pedido", example = "10", accessMode = Schema.AccessMode.READ_ONLY)
    private Integer id; 

    //relacao com cliente
    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    @JsonBackReference
    @Schema(description = "Cliente que realizou a compra")
    private Cliente cliente; 

    @Column(nullable = false, updatable = false)
    @Schema(description = "Data e hora em que o pedido foi gerado", example = "2024-05-15T14:30:00", accessMode = Schema.AccessMode.READ_ONLY)
    private LocalDateTime data = LocalDateTime.now(); 

    @Column(nullable = false)
    @Schema(description = "Soma total dos valores dos produtos", example = "599.80", requiredMode = Schema.RequiredMode.REQUIRED)
    private Double valorTotal; 

    //relacao com PedidoProduto
    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    @Schema(description = "Lista de produtos incluídos neste pedido")
    private List<PedidoProduto> itens = new ArrayList<>();

    //Método de ciclo de vida do JPA para definir a data no momento em que salvar no banco
    @PrePersist
    protected void onCreate(){
        this.data = LocalDateTime.now();
    }   

    //construtor padrao jpa
    public Pedido(){
        
    }

    //construtor auxiliar para facilitar o service 
    public Pedido(Cliente cliente, Double valorTotal){
        this.cliente = cliente; 
        this.valorTotal = valorTotal;
    }



    //gets e sets
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public List<PedidoProduto> getItens() {
        return itens;
    }

    public void setItens(List<PedidoProduto> itens) {
        this.itens = itens;
    } 

}
