package br.com.kenzowebstudio.loja_virtual.model;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;

@Entity
@Schema(name = "Avaliação", description = "Representa o feedback de um cliente sobre um produto")
public class Avaliacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID único da avaliação", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Integer id; 


    @Column(nullable = false)
    @Schema(description = "Nota de 1 a 5 dada ao produto", example = "5", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer nota; 


    //definido um tamnho maximo para o comentario para evitar que reserve espaço infinito 
    @Column(length = 500)
    @Schema(description = "Comentário opcional do cliente", example = "Produto Incrível recomento muito1")
    private String comentario; 


    @Column(nullable = false, updatable = false) // false garantindo que a data de criacao nao possa ser mutavel 
    @Schema(description = "Data em que a avaliação foi feita", example = "2024-05-15T10:00:00", accessMode = Schema.AccessMode.READ_ONLY)
    private LocalDateTime data = LocalDateTime.now();


    //relacao com cliente
    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    @com.fasterxml.jackson.annotation.JsonBackReference(value = "cliente-avaliacao")
    @Schema(description = "Dados do cliente que avaliou (Apenas leitura aqui)", accessMode = Schema.AccessMode.READ_ONLY)
    private Cliente cliente; 


    //relacao com produto
    @ManyToOne
    @JoinColumn(name = "produto_id", nullable = false)
    @com.fasterxml.jackson.annotation.JsonBackReference(value = "produto-avaliacao") //lado de tras interrompe loop
    @Schema(description = "Produto que recebeu a avaliação (Apenas leitura aqui)", accessMode = Schema.AccessMode.READ_ONLY)
    private Produto produto;

    @PrePersist
    protected void onCreate(){
        this.data = LocalDateTime.now();
    }

    //gets e sets
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getNota() {
        return nota;
    }
    public void setNota(Integer nota) {
        this.nota = nota;
    }
    public String getComentario() {
        return comentario;
    }
    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
    public LocalDateTime getData() {
        return data;
    }
    public void setData(LocalDateTime data) {
        this.data = data;
    }
    public Cliente getCliente() {
        return cliente;
    }
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    public Produto getProduto() {
        return produto;
    }
    public void setProduto(Produto produto) {
        this.produto = produto;
    }

}
