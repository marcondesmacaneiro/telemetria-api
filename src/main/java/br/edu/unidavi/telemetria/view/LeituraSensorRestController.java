package br.edu.unidavi.telemetria.view;

import br.edu.unidavi.telemetria.domain.model.LeituraSensor;
import br.edu.unidavi.telemetria.domain.repository.LeituraSensorRepository;
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
@RequestMapping("leiturasensor/{id}")
public class LeituraSensorRestController {

    @Autowired
    private LeituraSensorRepository repository;
    
    @RequestMapping(method = GET, value = "/filtrodata/{segundos}", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public ResponseEntity<List<LeituraSensor>> findAllSensoresAtivos(@PathVariable Long id, @PathVariable Long segundos) {
        return ok(repository.findAllBySensorAndFiltroData(id, segundos.intValue()));
    }

}
