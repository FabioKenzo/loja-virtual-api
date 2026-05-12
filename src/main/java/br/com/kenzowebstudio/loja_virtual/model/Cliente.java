package br.com.kenzowebstudio.loja_virtual.model;

import java.util.ArrayList;
import java.util.List;


import com.fasterxml.jackson.annotation.JsonManagedReference;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;


@Entity
@Schema(name = "Cliente", description = "Representa o perfil do usuário na plataforma")
public class Cliente {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador único do cliente", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Integer id; 

    @Column(nullable = false, length = 100)
    @Schema(description = "Nome completo do cliente", example = "Jeremias Santos", requiredMode = Schema.RequiredMode.REQUIRED)
    private String nome; 

    @Column(nullable = false, length = 100, unique = true)
    @Schema(description = "Endereço de e-mail (usado para login e notificações)", example = "jeremias@mail.com", requiredMode = Schema.RequiredMode.REQUIRED)
    private String email; 

    //relacao com pedidos 
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    @JsonManagedReference
    @Schema(description = "Histórico de compras do cliente", accessMode = Schema.AccessMode.READ_ONLY)
    private List<Pedido> pedidos = new ArrayList<>();

    //relacao com avaliacao
    @JsonManagedReference(value = "cliente-avaliacao")
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    @Schema(description = "Lista de comentários e notas deixadas pelo cliente", accessMode = Schema.AccessMode.READ_ONLY)
    private List<Avaliacao> avaliacoes;


    //gets e sets
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public List<Avaliacao> getAvaliacoes() {
        return avaliacoes;
    }

    public void setAvaliacoes(List<Avaliacao> avaliacoes) {
        this.avaliacoes = avaliacoes;
    }

}
