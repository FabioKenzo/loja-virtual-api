package br.com.kenzowebstudio.loja_virtual.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.kenzowebstudio.loja_virtual.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
    List<Produto>findByNomeContainingIgnoreCase(String nome);
}   
