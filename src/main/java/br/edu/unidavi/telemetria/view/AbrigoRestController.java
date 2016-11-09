package br.edu.unidavi.telemetria.view;

import br.edu.unidavi.telemetria.domain.exception.EntityAreadyExistException;
import br.edu.unidavi.telemetria.domain.model.Abrigo;
import br.edu.unidavi.telemetria.domain.service.AbrigoService;
import java.util.List;
import static java.util.Objects.nonNull;
import java.util.function.Consumer;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PagedResourcesAssembler;
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
 * @author marcondes
 */
@RestController
@RequestMapping("/api/abrigo")
public class AbrigoRestController {

    private static final Logger logger = LoggerFactory.getLogger(AbrigoRestController.class);

    @Autowired
    private AbrigoService service;

    @Autowired
    private PagedResourcesAssembler<Abrigo> pagedResourcesAssembler;

    @RequestMapping(method = GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public ResponseEntity<List<Abrigo>> findAll() {
        return ok(service.findAll());
    }

    @RequestMapping(method = GET, value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public ResponseEntity<Abrigo> findOne(@PathVariable Long id) {

        Abrigo abrigo = service.findOne(id)
                .orElseThrow(EntityAreadyExistException.entityAreadyExist("O Abrigo não existe!"));;

        return ok(abrigo);
    }

    @RequestMapping(method = POST, consumes = APPLICATION_JSON_VALUE)
    @CrossOrigin
    public ResponseEntity<Void> gravar(@Valid @RequestBody Abrigo abrigo) {

//        Pessoa existentPessoa = service.findByEmail(pessoa.getMail())
//                .orElseThrow(EntityAreadyExistException.entityAreadyExist("Pessoa já existe!"));
        abrigo = service.save(abrigo);

        return noContent().build();
    }

    @RequestMapping(method = PATCH, value = "/{id}", consumes = APPLICATION_JSON_VALUE)
    @CrossOrigin
    public ResponseEntity<Void> edit(@PathVariable Long id,
            @Valid @RequestBody AbrigoPatchInput input,
            HttpServletRequest request) {

        Abrigo abrigo = service.findOne(id)
                .orElseThrow(EntityAreadyExistException.entityAreadyExist("O abrigo não existe!"));

        input.accept(abrigo);

        service.save(abrigo);

        return noContent().build();
    }

    @RequestMapping(method = DELETE, value = "/{id}")
    @CrossOrigin
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        Abrigo abrigo = service.findOne(id)
                .orElseThrow(EntityAreadyExistException.entityAreadyExist("O abrigo não existe!"));

        service.delete(abrigo);

        return noContent().build();
    }

    static @Data
    class AbrigoPatchInput implements Consumer<Abrigo> {

        @NotNull
        @Size(min = 1, max = 100)
        private String nome;

        @NotNull
        @Size(min = 1, max = 100)
        private String responsavel;

        @NotNull
        @Size(min = 10, max = 300)
        private String imagem;

        @NotNull
        private Integer lotacaoMaxima;

        @NotNull
        private Integer lotacaoAtual;

        @NotNull
        @Size(min = 1, max = 100)
        private String localizacao;

        @Override
        public void accept(Abrigo abrigo) {
            if (nonNull(nome)) {
                abrigo.setNome(nome);
            }
            if (nonNull(responsavel)) {
                abrigo.setResponsavel(responsavel);
            }
            if (nonNull(imagem)) {
                abrigo.setImagem(imagem);
            }
            if (nonNull(lotacaoMaxima)) {
                abrigo.setLotacaoMaxima(lotacaoMaxima);
            }
            if (nonNull(lotacaoAtual)) {
                abrigo.setLotacaoAtual(lotacaoAtual);
            }
            if (nonNull(localizacao)) {
                abrigo.setLocalizacao(localizacao);
            }
        }
    }

}
