package br.edu.unidavi.telemetria.domain.service;

import br.edu.unidavi.telemetria.domain.model.LeituraPonto;
import br.edu.unidavi.telemetria.domain.model.LeituraPontoSensor;
import br.edu.unidavi.telemetria.domain.model.SensorLeitura;
import br.edu.unidavi.telemetria.domain.repository.LeituraPontoRepository;
import br.edu.unidavi.telemetria.domain.repository.LeituraPontoSensorRepository;
import br.edu.unidavi.telemetria.domain.repository.SensorLeituraRepository;
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

    @Autowired
    private LeituraPontoSensorRepository repoPontoSensor;

    @Autowired
    private SensorLeituraRepository repoSensor;

    public List<LeituraPonto> findAll() {
        return repository.findAllByOrderByIdAsc();
    }

    public Optional<LeituraPonto> findOne(Long id) {
        return Optional.ofNullable(repository.findOne(id));
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public LeituraPonto save(LeituraPonto leituraPonto) {
        leituraPonto = repository.save(leituraPonto);

        List<SensorLeitura> sensores = repoSensor.findAll();
        for (SensorLeitura sensor : sensores) {
            LeituraPontoSensor pontoSensor = LeituraPontoSensor.of(false, false);
            pontoSensor.setSensorLeitura(sensor);
            pontoSensor.setLeituraPonto(leituraPonto);
            repoPontoSensor.save(pontoSensor);
        }

        return leituraPonto;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void delete(LeituraPonto leituraPonto) {
        repository.delete(leituraPonto);
    }
}
