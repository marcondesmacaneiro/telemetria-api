package br.edu.unidavi.telemetria.domain.repository;

import br.edu.unidavi.telemetria.domain.model.Abrigo;
import br.edu.unidavi.telemetria.domain.model.SensorLeitura;
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
public class SensorLeituraService {

    @Autowired
    private SensorLeituraRepository repository;

    public List<SensorLeitura> findAll() {
        return repository.findAllByOrderByIdAsc();
    }

    public Optional<SensorLeitura> findOne(Long id) {
        return Optional.ofNullable(repository.findOne(id));
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public SensorLeitura save(SensorLeitura sensorLeitura) {
        return repository.save(sensorLeitura);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void delete(SensorLeitura sensorLeitura) {
        repository.delete(sensorLeitura);
    }
}
