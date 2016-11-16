package br.edu.unidavi.telemetria.domain.service;

import br.edu.unidavi.telemetria.domain.model.LeituraPontoSensor;
import br.edu.unidavi.telemetria.domain.model.LeituraSensor;
import br.edu.unidavi.telemetria.domain.repository.LeituraPontoSensorRepository;
import br.edu.unidavi.telemetria.domain.repository.LeituraSensorRepository;
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
    
    @Autowired
    private LeituraSensorRepository repoLeituraSensor;

    public List<LeituraPontoSensor> findAll() {
        List<LeituraPontoSensor> sensores = repository.findAllByOrderByIdAsc();
        for (LeituraPontoSensor sensor : sensores) {
            encontraUltimaLeituraSensor(sensor);
        }
        return sensores;
    }
    
    public List<LeituraPontoSensor> findAllSensoresAtivos(Long id) {
        List<LeituraPontoSensor> sensores = repository.findAllByLeituraPontoIdAndAtivoTrueOrderByIdAsc(id);
        for (LeituraPontoSensor sensor : sensores) {
            encontraUltimaLeituraSensor(sensor);
        }
        return sensores;
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
    
    private void encontraUltimaLeituraSensor(LeituraPontoSensor sensor){
        LeituraSensor leitura = repoLeituraSensor.findFirstByLeituraPontoSensorIdOrderByDataHoraDesc(sensor.getId());
        if(leitura != null){
            sensor.setUltimaLeitura(leitura.getLeituraFormatada() + " - " + leitura.getDataHoraFormatada());
        }
    }
}
