package br.com.kenzowebstudio.loja_virtual.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.kenzowebstudio.loja_virtual.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    Optional<Cliente> findByEmail(String email);
}
