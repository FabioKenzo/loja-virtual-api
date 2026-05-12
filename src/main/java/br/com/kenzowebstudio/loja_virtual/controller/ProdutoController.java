package br.com.kenzowebstudio.loja_virtual.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import br.com.kenzowebstudio.loja_virtual.model.Avaliacao;
import br.com.kenzowebstudio.loja_virtual.model.Produto;
import br.com.kenzowebstudio.loja_virtual.service.ProdutoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/produtos")
@Tag(name = "Produtos", description = "Catálogo de itens, busca por nome e gestão de inventário")
public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }


    @GetMapping
    @Operation(summary = "Lista o catálogo completo", description = "Retorna todos os produtos disponíveis na loja.")
    public ResponseEntity<List<Produto>> listarTodos() {
        List<Produto> produtos = produtoService.listarTodos();
        return ResponseEntity.ok(produtos);
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Adiciona novo produto", description = "Cadastra um novo item no catálogo.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Produto cadastrado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Erro na validação dos dados do produto")
    })
    public Produto salvar(@RequestBody Produto produto) {
        return produtoService.salvar(produto);
    }


    @GetMapping("/{id}")
    @Operation(summary = "Detalhes do produto", description = "Busca informações completas de um produto através do seu ID.")
    public ResponseEntity<Produto> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(produtoService.buscarPorId(id));
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Remover produto", description = "Exclui um produto do catálogo permanentemente.")
    public void deletar(@PathVariable Integer id) {
        produtoService.deletar(id);
    }


    @GetMapping("/{id}/avaliacoes")
    @Operation(summary = "Ver avaliações do produto", description = "Lista todos os comentários e notas que os clientes deixaram para este item específico.")
    public ResponseEntity<List<Avaliacao>> listarAvaliacoesDoProduto(@PathVariable Integer id) {
        //busca o produto e retorna sua lista de avaliacoes
        List<Avaliacao> avaliacoes = produtoService.buscarPorId(id).getAvaliacoes();
        return ResponseEntity.ok(avaliacoes);
    }


    @GetMapping("/buscar")
    @Operation(summary = "Filtrar por nome", description = "Pesquisa produtos que contenham o termo enviado no nome.")
    public ResponseEntity<List<Produto>> buscarPorNome(@RequestParam String nome) {
        List<Produto> produtos = produtoService.buscarPorNome(nome);
        return ResponseEntity.ok(produtos);
    }
}
