package br.com.kenzowebstudio.loja_virtual.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.kenzowebstudio.loja_virtual.model.Avaliacao;
import br.com.kenzowebstudio.loja_virtual.repository.AvaliacaoRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AvaliacaoService {

    private final AvaliacaoRepository avaliacaoRepository; 

    public AvaliacaoService(AvaliacaoRepository avaliacaoRepository){
         this.avaliacaoRepository = avaliacaoRepository;
    }

    @Transactional(readOnly = true)
    public List<Avaliacao> listarTodas(){
        return avaliacaoRepository.findAll();
    }   

    @Transactional
    public Avaliacao salvar(Avaliacao avaliacao){
        if(avaliacao.getNota() < 1 || avaliacao.getNota() > 5){
            throw new RuntimeException("A nota deve ser entre 1 e 5");
        }
        return avaliacaoRepository.save(avaliacao);
    }


    @Transactional(readOnly = true)
    //Em vez de retornar null lançamos uma exceção clara
    public Avaliacao buscarPorId(Integer id){
        return avaliacaoRepository.findById(id).orElseThrow(() -> new RuntimeException("Avaliação não encontrada com o ID: " + id));
    }


    @Transactional
    public void deletar(Integer id){
        //Antes de deletar verificamos se existe para não dar erro interno no Hibernate
        Avaliacao avaliacao = buscarPorId(id);
        avaliacaoRepository.delete(avaliacao);
    }

}
