package br.edu.unidavi.telemetria.domain.repository;

import br.edu.unidavi.telemetria.domain.model.LeituraPonto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by marcondesmacaneiro on 10/10/16.
 */
@RepositoryRestResource(collectionResourceRel = "leiturapontos", itemResourceRel = "leituraponto", path = "leituraponto")
public interface LeituraPontoRepository extends JpaRepository<LeituraPonto, Long> {

    public List<LeituraPonto> findAllByOrderByIdAsc();

}
