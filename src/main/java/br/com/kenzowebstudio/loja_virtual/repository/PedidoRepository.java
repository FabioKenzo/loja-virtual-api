package br.com.kenzowebstudio.loja_virtual.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.kenzowebstudio.loja_virtual.model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

}
