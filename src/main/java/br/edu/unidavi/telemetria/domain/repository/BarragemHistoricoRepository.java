package br.edu.unidavi.telemetria.domain.repository;

import br.edu.unidavi.telemetria.domain.model.BarragemHistorico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * @author Bruno Pasqualini
 */
@RepositoryRestResource(collectionResourceRel = "barragenshistoricos", itemResourceRel = "barragemhistorico" , path = "barragemhistorico")
public interface BarragemHistoricoRepository extends JpaRepository<BarragemHistorico, Long> {

    public BarragemHistorico findFirstByBarragemIdOrderByIdDesc(Long id);

}
