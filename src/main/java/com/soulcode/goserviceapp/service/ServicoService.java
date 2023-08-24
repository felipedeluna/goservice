package com.soulcode.goserviceapp.service;

import com.soulcode.goserviceapp.domain.Servico;
import com.soulcode.goserviceapp.repository.ServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServicoService {
    @Autowired
    private ServicoRepository servicoRepository;

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
    public void removeServico(Long id){
        servicoRepository.deleteById(id);
    }

    public List<Servico> findAll(){
        return servicoRepository.findAll();
    }
    public Servico findById(Long id){
        Optional<Servico> servico = servicoRepository.findById(id);
        if (servico.isPresent()){
            return servico.get();
        } else {
            throw new RuntimeException("Seviço não encontrado");
        }
    }
}
