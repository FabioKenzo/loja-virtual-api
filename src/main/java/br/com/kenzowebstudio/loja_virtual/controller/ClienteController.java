package br.com.kenzowebstudio.loja_virtual.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import br.com.kenzowebstudio.loja_virtual.model.Cliente;
import br.com.kenzowebstudio.loja_virtual.model.Pedido;
import br.com.kenzowebstudio.loja_virtual.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/clientes")
@Tag(name = "Clientes", description = "Gerenciamento de clientes, histórico de pedidos e métricas financeiras")
public class ClienteController {

    private final ClienteService clienteService; 

    public ClienteController(ClienteService clienteService){
        this.clienteService = clienteService;
    }


    @GetMapping
    @Operation(summary = "Lista todos os clientes", description = "Retorna uma lista de todos os clientes cadastrados.")
    public List<Cliente> listarTodos(){
         return clienteService.listarTodos();
    }


    @PostMapping
    //retorna 201 created
    @ResponseStatus(HttpStatus.CREATED) 
    @Operation(summary = "Cadastra um novo cliente", description = "Cria um registro de cliente. O e-mail deve ser único.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Cliente criado com sucesso"), 
        @ApiResponse(responseCode = "400", description = "Dados inválidos ou e-mail já cadastrado")
    })
    public Cliente salvar(@RequestBody Cliente cliente){
         return clienteService.salvar(cliente);
    }

    
    @GetMapping("/{id}")
    @Operation(summary = "Busca cliente por Id", description = "Retorna os detalhes de um cliente específico.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Cliente encontrado"),
        @ApiResponse(responseCode = "404", description = "Cliente não encontrado")
    })
    public ResponseEntity<Cliente> buscarPorId(@PathVariable Integer id){
        //envolve em ResponseEntity para padronização
        return ResponseEntity.ok(clienteService.buscarPorId(id));
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Exclui um cliente", description = "Remove o cliente e seus vínculos (dependendo das regras de cascade).")
    // retorna 204 co content
    public void deletar(@PathVariable Integer id){
        clienteService.deletar(id);
    }


    @GetMapping("/{id}/pedidos")
    @Operation(summary = "Lista pedidos do cliente", description = "Retorna todo o histórico de compras de um cliente específico.")
    public List<Pedido> listarPedidosDoCliente(@PathVariable Integer id){
        return clienteService.buscarPorId(id).getPedidos();
    }


    @GetMapping("/buscar")
    @Operation(summary = "Busca cliente por e-mail", description = "Localiza um cliente através do endereço de e-mail exato.")
    public ResponseEntity<Cliente> buscarPorEmail(@RequestParam String email){
         return ResponseEntity.ok(clienteService.buscarPorEmail(email));
    }


    @GetMapping("/{id}/total-gasto")
    @Operation(summary = "Calcula total gasto", description = "Soma o valor de todos os pedidos realizados por este cliente.")
    public Double calcularTotalGasto(@PathVariable Integer id){
        //se o id for invalido o service barra
        return clienteService.buscarPorId(id).getPedidos().stream().mapToDouble(Pedido::getValorTotal).sum();
    }
}