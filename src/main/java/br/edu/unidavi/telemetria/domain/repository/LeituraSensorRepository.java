package br.edu.unidavi.telemetria.domain.repository;

import br.edu.unidavi.telemetria.domain.model.LeituraSensor;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * @author Bruno Pasqualini
 */
@RepositoryRestResource(collectionResourceRel = "leiturassensores", itemResourceRel = "leiturasensor", path = "leiturasensor")
public interface LeituraSensorRepository extends JpaRepository<LeituraSensor, Long> {

    public List<LeituraSensor> findAllByOrderByIdAsc();
    
    public LeituraSensor findFirstByLeituraPontoSensorIdOrderByDataHoraDesc(Long id);
    
    @Query(value = "select * from tb_leitura_sensor sensor where "
         + "leitura_ponto_sensor_id = ?1 and "
         + "extract(epoch from data_hora) >= (extract(epoch from cast(timeofday() as timestamp)) - ?2) "
         + "order by data_hora desc", nativeQuery = true)
    public List<LeituraSensor> findAllBySensorAndFiltroData(Long id, int segundos);

}
