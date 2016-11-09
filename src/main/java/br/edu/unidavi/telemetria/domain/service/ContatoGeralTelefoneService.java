package br.edu.unidavi.telemetria.domain.service;

import br.edu.unidavi.telemetria.domain.model.Abrigo;
import br.edu.unidavi.telemetria.domain.model.ContatoGeral;
import br.edu.unidavi.telemetria.domain.model.ContatoGeralTelefone;
import br.edu.unidavi.telemetria.domain.repository.ContatoGeralTelefoneRepository;
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
public class ContatoGeralTelefoneService {

    @Autowired
    private ContatoGeralTelefoneRepository repository;

    public List<ContatoGeralTelefone> findAll() {
        return repository.findAllByOrderByIdAsc();
    }

    public Optional<ContatoGeralTelefone> findOne(Long id) {
        return Optional.ofNullable(repository.findOne(id));
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public ContatoGeralTelefone save(ContatoGeralTelefone contatoGeralTelefone) {
        return repository.save(contatoGeralTelefone);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void delete(ContatoGeralTelefone contatoGeralTelefone) {
        repository.delete(contatoGeralTelefone);
    }
}
