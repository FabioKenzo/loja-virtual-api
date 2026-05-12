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
import org.springframework.web.bind.annotation.ResponseStatus; 
import org.springframework.web.bind.annotation.RestController;
import br.com.kenzowebstudio.loja_virtual.model.Avaliacao;
import br.com.kenzowebstudio.loja_virtual.service.AvaliacaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/avaliacoes")
@Tag(name = "Avaliações", description = "Endpoints para gerenciar as notas dos produtos") // isso muda o nome lano swagger
public class AvaliacaoController {

    private final AvaliacaoService avaliacaoService; 

    public AvaliacaoController(AvaliacaoService avaliacaoService){
        this.avaliacaoService = avaliacaoService;
    }

    
    @GetMapping
    @Operation(summary = "Lista todas as avaliações", description = "Retorna uma lista completa de todas as avaliações registradas no sistema.")
    public List<Avaliacao> listarTodas(){
        return avaliacaoService.listarTodas();
    }


    @PostMapping
    //obs:
    //retorna 201 created em vez de 200 OK para indicar que um recurso foi criado
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Registra uma nova avaliação", description = "Cria uma nova avaliação para um produto. Retorna 201 em caso de sucesso.")
    @ApiResponses(value ={
        @ApiResponse(responseCode = "201", description = "Avaliação criada com sucesso"), 
        @ApiResponse(responseCode = "400", description = "Dados da avaliação inválidos")
    })
    public Avaliacao salvar(@RequestBody Avaliacao avaliacao){
        return avaliacaoService.salvar(avaliacao);
    }


    @GetMapping("/{id}")
    //obs:
    //usaa ResponseEntity permite que o spring lide melhor com o objeto retornado

    public ResponseEntity<Avaliacao> buscarPorId(@PathVariable Integer id){
        return ResponseEntity.ok(avaliacaoService.buscarPorId(id));
    }


    @DeleteMapping("/{id}")
    //retorna 204 no content
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Remove uma avaliação", description = "Exclui permanentemente uma avaliação do sistema.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "2024", description = "Avaliação excluída com sucesso"), 
        @ApiResponse(responseCode = "404", description = "Id informado não existe")
    })
    public void deletar(@PathVariable Integer id){
         avaliacaoService.deletar(id);
    }
}
