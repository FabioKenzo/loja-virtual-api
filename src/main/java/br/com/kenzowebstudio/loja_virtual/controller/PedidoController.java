package br.com.kenzowebstudio.loja_virtual.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import br.com.kenzowebstudio.loja_virtual.model.Pedido;
import br.com.kenzowebstudio.loja_virtual.service.PedidoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/pedidos")
@Tag(name = "Pedidos", description = "Processamento de vendas, registro de ordens e métricas de faturamento")
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService){
        this.pedidoService = pedidoService;
    }


    @GetMapping
    @Operation(summary = "Lista todos os pedidos", description = "Retorna o histórico completo de vendas realizadas.")
    public List<Pedido> listarTodos(){
        return pedidoService.listarTodos();
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Salva um pedido bruto", description = "Cria um novo registro de pedido diretamente no sistema.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Pedido salvo com sucesso"), 
        @ApiResponse(responseCode = "400", description = "Dados do pedido inconsistentes")
    })
    public Pedido salvar(@RequestBody Pedido pedido){
        return pedidoService.salvar(pedido);
    }


    @GetMapping("/{id}")
    @Operation(summary = "Busca pedido por ID", description = "Retorna os detalhes de uma venda específica, incluindo itens e valores.")
    public ResponseEntity<Pedido> buscarPorId(@PathVariable Integer id){
        return ResponseEntity.ok(pedidoService.buscarPorId(id));
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Exclui um pedido", description = "Remove o registro de um pedido. Use com cautela por conta do histórico financeiro.")
    public void deletar(@PathVariable Integer id){
        pedidoService.deletar(id);
    }


    @PostMapping("/registrar")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Registra venda no sistema", description = "Processa o checkout e oficializa a compra.")
    public Pedido registrarPedido(@RequestBody Pedido pedido){
        return pedidoService.salvar(pedido);
    }


    @GetMapping("/total-vendas")
    @Operation(summary = "Faturamento total", description = "Calcula a soma do valor de todos os pedidos confirmados na base.")
    public ResponseEntity<Double> calcularTotalVendas(){
        Double total = pedidoService.listarTodos().stream().mapToDouble(Pedido::getValorTotal).sum();
        return ResponseEntity.ok(total);
    }
}
