package br.com.kenzowebstudio.loja_virtual.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.kenzowebstudio.loja_virtual.model.PedidoProduto;
import br.com.kenzowebstudio.loja_virtual.model.PedidoProdutoId;

public interface PedidoProdutoRepository extends JpaRepository<PedidoProduto, PedidoProdutoId> {

}
