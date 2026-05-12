package br.com.kenzowebstudio.loja_virtual.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.kenzowebstudio.loja_virtual.model.Cliente;
import br.com.kenzowebstudio.loja_virtual.model.Pedido;
import br.com.kenzowebstudio.loja_virtual.model.PedidoProduto;
import br.com.kenzowebstudio.loja_virtual.model.Produto;
import br.com.kenzowebstudio.loja_virtual.repository.ClienteRepository;
import br.com.kenzowebstudio.loja_virtual.repository.PedidoRepository;
import br.com.kenzowebstudio.loja_virtual.repository.ProdutoRepository;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ClienteRepository clienteRepository;
    private final ProdutoRepository produtoRepository;

    public PedidoService(PedidoRepository pedidoRepository, ClienteRepository clienteRepository, ProdutoRepository produtoRepository) {
        this.pedidoRepository = pedidoRepository;
        this.clienteRepository = clienteRepository;
        this.produtoRepository = produtoRepository;
    }

    @Transactional(readOnly = true)
    public List<Pedido> listarTodos() {
        return pedidoRepository.findAll();
    }
    
    //obs: 
    // @Transactional garante que se o estoque acabar no meio do processo, 
    //nada será salvo da Rollback.
     
    @Transactional
    public Pedido salvar(Pedido pedido) {
        //Validacao do cliente
        Cliente cliente = clienteRepository.findById(pedido.getCliente().getId()).orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        pedido.setCliente(cliente);


        //Processamento de itens e estoque
        for (PedidoProduto item : pedido.getItens()) {
            Produto produto = produtoRepository.findById(item.getProduto().getId()).orElseThrow(() -> new RuntimeException("Produto não encontrado"));

            //Verifica se tem estoque disponivel
            if (produto.getEstoque() < item.getQuantidade()) {
                throw new RuntimeException("Estoque insuficiente para o produto: " + produto.getNome());
            }

            //Baixa automática do estoque
            produto.setEstoque(produto.getEstoque() - item.getQuantidade());
            produtoRepository.save(produto); // Atualiza o produto com o novo estoque

            item.setProduto(produto);
            item.setPedido(pedido);
        }

        // Cálculo do total (Preço do banco para não tomar golpe)
        Double total = pedido.getItens().stream().mapToDouble(item -> item.getProduto().getPreco() * item.getQuantidade()).sum();
        pedido.setValorTotal(total);

        return pedidoRepository.save(pedido);
    }

    @Transactional(readOnly = true)
    public Pedido buscarPorId(Integer id) {
        return pedidoRepository.findById(id).orElseThrow(() -> new RuntimeException("Pedido não encontrado com o ID: " + id));
    }

    @Transactional
    public void deletar(Integer id) {
        Pedido pedido = buscarPorId(id);
        pedidoRepository.delete(pedido);
    }
}
