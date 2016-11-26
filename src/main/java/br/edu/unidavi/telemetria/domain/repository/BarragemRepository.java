package br.edu.unidavi.telemetria.domain.repository;

import br.edu.unidavi.telemetria.domain.model.Barragem;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * @author Bruno Pasqualini
 */
@RepositoryRestResource(collectionResourceRel = "barragens", itemResourceRel = "barragem" , path = "barragem")
public interface BarragemRepository extends JpaRepository<Barragem, Long> {

    public List<Barragem> findAllByOrderByIdAsc();

}
