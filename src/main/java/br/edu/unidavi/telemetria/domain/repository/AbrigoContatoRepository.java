package br.edu.unidavi.telemetria.domain.repository;

import br.edu.unidavi.telemetria.domain.model.AbrigoContato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by marcondesmacaneiro on 10/10/16.
 */
@RepositoryRestResource(collectionResourceRel = "abrigocontatos", itemResourceRel = "abrigocontato" , path = "abrigocontato")
interface AbrigoContatoRepository extends JpaRepository<AbrigoContato, Long> {
}
