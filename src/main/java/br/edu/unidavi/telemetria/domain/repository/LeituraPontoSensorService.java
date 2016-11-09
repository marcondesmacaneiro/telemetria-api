package br.edu.unidavi.telemetria.domain.repository;

import br.edu.unidavi.telemetria.domain.model.Abrigo;
import br.edu.unidavi.telemetria.domain.model.LeituraPontoSensor;
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
public class LeituraPontoSensorService {

    @Autowired
    private LeituraPontoSensorRepository repository;

    public List<LeituraPontoSensor> findAll() {
        return repository.findAllByOrderByIdAsc();
    }

    public Optional<LeituraPontoSensor> findOne(Long id) {
        return Optional.ofNullable(repository.findOne(id));
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public LeituraPontoSensor save(LeituraPontoSensor leituraPontoSensor) {
        return repository.save(leituraPontoSensor);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void delete(LeituraPontoSensor leituraPontoSensor) {
        repository.delete(leituraPontoSensor);
    }
}
