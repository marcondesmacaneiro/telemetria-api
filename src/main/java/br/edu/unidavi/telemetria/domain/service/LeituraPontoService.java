package br.edu.unidavi.telemetria.domain.service;

import br.edu.unidavi.telemetria.domain.model.LeituraPonto;
import br.edu.unidavi.telemetria.domain.repository.LeituraPontoRepository;
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
public class LeituraPontoService {

    @Autowired
    private LeituraPontoRepository repository;

    public List<LeituraPonto> findAll() {
        return repository.findAllByOrderByIdAsc();
    }

    public Optional<LeituraPonto> findOne(Long id) {
        return Optional.ofNullable(repository.findOne(id));
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public LeituraPonto save(LeituraPonto leituraPonto) {
        return repository.save(leituraPonto);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void delete(LeituraPonto leituraPonto) {
        repository.delete(leituraPonto);
    }
}
