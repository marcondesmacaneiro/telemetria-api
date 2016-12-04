package br.edu.unidavi.telemetria.view;

import br.edu.unidavi.telemetria.domain.exception.EntityAreadyExistException;
import br.edu.unidavi.telemetria.domain.model.Barragem;
import br.edu.unidavi.telemetria.domain.model.BarragemHistorico;
import br.edu.unidavi.telemetria.domain.repository.BarragemHistoricoRepository;
import br.edu.unidavi.telemetria.domain.service.BarragemService;
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
@RequestMapping("api/barragem")
public class BarragemRestController {

    @Autowired
    private BarragemService service;
    
    @Autowired
    private BarragemHistoricoRepository repoHistorico;
    
    @RequestMapping(method = GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public ResponseEntity<List<Barragem>> findAllBarragens() {
        return ok(service.findAll());
    }
    
    @RequestMapping(method = GET, value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public ResponseEntity<Barragem> findAllSensores(@PathVariable Long id) {
        
        Barragem barragem = service.findOne(id)
                .orElseThrow(EntityAreadyExistException.entityAreadyExist("Barragem não existe!"));
                
        return ok(barragem);
    }
    
    @RequestMapping(method = GET, value = "/{id}/historico/filtrodata/{segundos}", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public ResponseEntity<List<BarragemHistorico>> findAllHistorico(@PathVariable Long id, @PathVariable Long segundos) {
        return ok(repoHistorico.findAllByBarragemIdAndFiltroData(id, segundos.intValue()));
    }

}
