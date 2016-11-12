package br.edu.unidavi.telemetria.view;

import br.edu.unidavi.telemetria.domain.model.LeituraPonto;
import br.edu.unidavi.telemetria.domain.service.LeituraPontoService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import org.springframework.http.ResponseEntity;
import static org.springframework.http.ResponseEntity.noContent;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Bruno Pasqualini
 */
@RestController
@RequestMapping("leituraponto")
public class LeituraPontoRestController {

    @Autowired
    private LeituraPontoService service;

    @RequestMapping(method = POST, consumes = APPLICATION_JSON_VALUE)
    @CrossOrigin
    public ResponseEntity<Void> gravar(@Valid @RequestBody LeituraPonto leituraPonto) {
        service.save(leituraPonto);
        return noContent().build();
    }

}
