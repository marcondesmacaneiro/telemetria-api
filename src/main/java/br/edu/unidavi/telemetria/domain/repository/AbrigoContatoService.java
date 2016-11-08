package br.edu.unidavi.telemetria.domain.repository;

import br.edu.unidavi.telemetria.domain.model.AbrigoContato;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Bruno Pasqualini
 */
@Service
@Transactional(readOnly = true)
public class AbrigoContatoService {

    @Autowired
    private AbrigoContatoRepository repository;

    public List<AbrigoContato> findByAbrigoId(Long id){
        return repository.findByAbrigoIdOrderByIdAsc(id);
    }

    public Optional<AbrigoContato> findOne(Long id) {
        return Optional.ofNullable(repository.findOne(id));
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public AbrigoContato save(AbrigoContato contato) {
        return repository.save(contato);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void delete(AbrigoContato contato) {
        repository.delete(contato);
    }

}
