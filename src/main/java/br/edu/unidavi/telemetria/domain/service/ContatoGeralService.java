package br.edu.unidavi.telemetria.domain.service;

import br.edu.unidavi.telemetria.domain.model.ContatoGeral;
import br.edu.unidavi.telemetria.domain.repository.ContatoGeralRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author marcondes 01/11/2016
 */
@Service
@Transactional(readOnly = true)
public class ContatoGeralService {

    @Autowired
    private ContatoGeralRepository repository;

    public List<ContatoGeral> findAll() {
        return repository.findAllByOrderByIdAsc();
    }

    public Optional<ContatoGeral> findOne(Long id) {
        return Optional.ofNullable(repository.findOne(id));
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public ContatoGeral save(ContatoGeral contatoGeral) {
        return repository.save(contatoGeral);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void delete(ContatoGeral contatoGeral) {
        repository.delete(contatoGeral);
    }
}
