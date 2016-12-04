package br.edu.unidavi.telemetria.domain.repository;

import br.edu.unidavi.telemetria.domain.model.BarragemHistorico;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * @author Bruno Pasqualini
 */
@RepositoryRestResource(collectionResourceRel = "barragenshistoricos", itemResourceRel = "barragemhistorico" , path = "barragemhistorico")
public interface BarragemHistoricoRepository extends JpaRepository<BarragemHistorico, Long> {

    public BarragemHistorico findFirstByBarragemIdOrderByIdDesc(Long id);
    
    @Query(value = "select * from tb_barragem_historico where "
         + "barragem_id = ?1 and "
         + "extract(epoch from data_hora) >= (extract(epoch from cast(timeofday() as timestamp)) - ?2) "
         + "order by data_hora desc", nativeQuery = true)
    public List<BarragemHistorico> findAllByBarragemIdAndFiltroData(Long id, int segundos);

}
