package br.edu.unidavi.telemetria.domain.repository;

import br.edu.unidavi.telemetria.domain.model.Abrigo;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by marcondesmacaneiro on 10/10/16.
 */
@RepositoryRestResource(collectionResourceRel = "abrigos", itemResourceRel = "abrigo" , path = "abrigo")
interface AbrigoRepository extends JpaRepository<Abrigo, Long> {

    public List<Abrigo> findAllByOrderByIdAsc();

}
