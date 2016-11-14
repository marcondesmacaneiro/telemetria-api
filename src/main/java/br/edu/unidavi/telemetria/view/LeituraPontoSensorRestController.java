package br.edu.unidavi.telemetria.view;

import br.edu.unidavi.telemetria.domain.model.LeituraPontoSensor;
import br.edu.unidavi.telemetria.domain.service.LeituraPontoSensorService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import static org.springframework.http.ResponseEntity.ok;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Bruno Pasqualini
 */
@RestController
@RequestMapping("leituraponto/{id}/sensoresativos")
public class LeituraPontoSensorRestController {

    @Autowired
    private LeituraPontoSensorService service;
    
    @RequestMapping(method = GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public ResponseEntity<List<LeituraPontoSensor>> findAllSensoresAtivos(@PathVariable Long id) {
        return ok(service.findAllSensoresAtivos(id));
    }

}
