package com.soulcode.goserviceapp.service;

import com.soulcode.goserviceapp.domain.Servico;
import com.soulcode.goserviceapp.repository.ServicoRepository;
import com.soulcode.goserviceapp.service.exceptions.ServicoNaoEncontradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServicoService {
    @Autowired
    private ServicoRepository servicoRepository;

    @Cacheable(cacheNames = "redisCache")
    public List<Servico> findAll(){
        System.err.println("BUSCANDO NO BANCO DE DADOS...");
        return servicoRepository.findAll();
    }

    public Servico createServico(Servico servico){
        servico.setId(null);
        return servicoRepository.save(servico);
    }
    public Servico updateServico(Servico servico){
        Servico updateServico = this.findById(servico.getId());
        updateServico.setNome(servico.getNome());
        updateServico.setDescricao(servico.getDescricao());
        updateServico.setCategoria(servico.getCategoria());
        return servicoRepository.save(servico);
    }
    public void removeServicoById(Long id){
        servicoRepository.deleteById(id);
    }

    public Servico findById(Long id){
        Optional<Servico> servico = servicoRepository.findById(id);
        if (servico.isPresent()){
            return servico.get();
        } else {
            throw new ServicoNaoEncontradoException("Seviço não encontrado");
        }
    }

    public List<Servico> findByPrestadorEmail(String email){
        return servicoRepository.findByPrestadorEmail(email);
    }
}
