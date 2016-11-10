package br.edu.unidavi.telemetria.view;

import br.edu.unidavi.telemetria.domain.exception.EntityAreadyExistException;
import br.edu.unidavi.telemetria.domain.model.AbrigoContato;
import br.edu.unidavi.telemetria.domain.service.AbrigoContatoService;
import br.edu.unidavi.telemetria.domain.vo.Phone;
import java.util.List;
import static java.util.Objects.nonNull;
import java.util.function.Consumer;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import org.springframework.http.ResponseEntity;
import static org.springframework.http.ResponseEntity.noContent;
import static org.springframework.http.ResponseEntity.ok;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.PATCH;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Bruno Pasqualini
 */
@RestController
@RequestMapping("api/abrigo/{id}/contato")
public class AbrigoContatoRestController {

    @Autowired
    private AbrigoContatoService service;

    @RequestMapping(method = GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public ResponseEntity<List<AbrigoContato>> findByAbrigoId(@PathVariable Long id) {
        return ok(service.findByAbrigoId(id));
    }

    @RequestMapping(method = POST, consumes = APPLICATION_JSON_VALUE)
    @CrossOrigin
    public ResponseEntity<Void> gravar(@Valid @RequestBody AbrigoContato abrigo) {
        service.save(abrigo);
        return noContent().build();
    }

    @RequestMapping(method = PATCH, value = "/{id}", consumes = APPLICATION_JSON_VALUE)
    @CrossOrigin
    public ResponseEntity<Void> edit(@PathVariable Long id,
            @Valid @RequestBody AbrigoContatoRestController.AbrigoContatoPatchInput input,
            HttpServletRequest request) {

        AbrigoContato contato = service.findOne(id)
                .orElseThrow(EntityAreadyExistException.entityAreadyExist("O contato não existe!"));

        input.accept(contato);
        service.save(contato);
        return noContent().build();
    }

    @RequestMapping(method = DELETE, value = "/{id}")
    @CrossOrigin
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        AbrigoContato abrigo = service.findOne(id)
                               .orElseThrow(EntityAreadyExistException.entityAreadyExist("O contato não existe!"));
        service.delete(abrigo);
        return noContent().build();
    }

    static @Data
    class AbrigoContatoPatchInput implements Consumer<AbrigoContato> {

        @NotNull
        @Size(min = 1, max = 100)
        private String responsavel;

        @NotNull
        private Phone telefone;

        @NotNull
        private boolean principal;

        @Override
        public void accept(AbrigoContato abrigo) {
            if (nonNull(responsavel)) {
                abrigo.setResponsavel(responsavel);
            }
            if (nonNull(telefone)) {
                abrigo.setTelefone(telefone);
            }
            if (nonNull(principal)) {
                abrigo.setPrincipal(principal);
            }
        }
    }
}
