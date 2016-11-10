package br.edu.unidavi.telemetria.domain.repository;

import br.edu.unidavi.telemetria.domain.model.Doacao;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by marcondesmacaneiro on 10/10/16.
 */
@RepositoryRestResource(collectionResourceRel = "doacoes", itemResourceRel = "doacao", path = "doacao")
public interface DoacaoRepository extends JpaRepository<Doacao, Long> {

    public List<Doacao> findAllByOrderByIdAsc();

}
