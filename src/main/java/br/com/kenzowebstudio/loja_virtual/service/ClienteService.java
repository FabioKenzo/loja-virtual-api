package br.com.kenzowebstudio.loja_virtual.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.kenzowebstudio.loja_virtual.model.Cliente;
import br.com.kenzowebstudio.loja_virtual.repository.ClienteRepository;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository; 

    public ClienteService(ClienteRepository clienteRepository){
        this.clienteRepository = clienteRepository;
    }


    @Transactional(readOnly = true)
    public List<Cliente> listarTodos(){
        return clienteRepository.findAll();
    }


    @Transactional
    public Cliente salvar(Cliente cliente){
        //verificao de regra de negocio antes de salvar 
        //se o email ja exisit e nao for do mesmo cliente barra
        clienteRepository.findByEmail(cliente.getEmail()).ifPresent(c -> {
            if(!c.getId().equals(cliente.getId())){
                throw new RuntimeException("Este e-mail já está cadastrado no sistema!");
                }
            });
        return clienteRepository.save(cliente);
    }


    @Transactional(readOnly = true)
    public Cliente buscarPorId(Integer id){
        //Lançar exceção clara em vez de retornar null
        return clienteRepository.findById(id).orElseThrow(() -> new RuntimeException("Cliente não encontrado com o ID: " + id));
    }


    @Transactional
    public void deletar(Integer id){
        //valida antes de tentar deletar
        Cliente cliente = buscarPorId(id);
        clienteRepository.delete(cliente);
    }
    

    @Transactional(readOnly = true)
    public Cliente buscarPorEmail(String email){
        return clienteRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("Cliente não encontrado com o e-mail: " + email));
    }
}
