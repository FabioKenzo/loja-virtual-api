package br.com.kenzowebstudio.loja_virtual.service;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional; 
import br.com.kenzowebstudio.loja_virtual.model.Produto;
import br.com.kenzowebstudio.loja_virtual.repository.ProdutoRepository;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository; 

    public ProdutoService(ProdutoRepository produtoRepository){
        this.produtoRepository = produtoRepository;
    }

    
    //readOnly = true para otimizar a performance de listagem
    @Transactional(readOnly = true)
    public List<Produto> listarTodos(){
        return produtoRepository.findAll();
    }


    //@Transactional garante que a alteração no banco seja atômica
    @Transactional
    public Produto salvar(Produto produto){
        if (produto.getPreco() < 0) {
            throw new RuntimeException("O preço do produto não pode ser negativo.");
        }
        return produtoRepository.save(produto);
    }


    @Transactional(readOnly = true)
    public Produto buscarPorId(Integer id){
        //evita que o controller receba null e cause um NullPointerException
        return produtoRepository.findById(id).orElseThrow(() -> new RuntimeException("Produto não encontrado com o ID: " + id));
    }

    @Transactional
    public void deletar(Integer id){
        //garante que o produto existe antes de tentar deletar
        Produto produto = buscarPorId(id);
        produtoRepository.delete(produto);
    }


    @Transactional(readOnly = true)
    public List<Produto> buscarPorNome(String nome){
        return produtoRepository.findByNomeContainingIgnoreCase(nome);
    }
}
