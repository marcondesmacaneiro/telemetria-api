package br.edu.unidavi.telemetria.domain.repository;

import br.edu.unidavi.telemetria.domain.model.Abrigo;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author marcondes 01/11/2016
 */
@Service
@Transactional(readOnly = true)
public class AbrigoService {

    @Autowired
    private AbrigoRepository repository;

    public List<Abrigo> findAll() {
        return repository.findAll();
    }

    public Optional<Abrigo> findOne(Long id) {
        return Optional.ofNullable(repository.findOne(id));
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Abrigo save(Abrigo abrigo) {
        return repository.save(abrigo);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void delete(Abrigo abrigo) {
        repository.delete(abrigo);
    }
}
