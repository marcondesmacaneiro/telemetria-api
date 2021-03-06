package br.edu.unidavi.telemetria.domain.repository;

import br.edu.unidavi.telemetria.domain.model.ContatoGeral;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by marcondesmacaneiro on 10/10/16.
 */
@RepositoryRestResource(collectionResourceRel = "contatosgerais", itemResourceRel = "contatogeral" , path = "contatogeral")
public interface ContatoGeralRepository extends JpaRepository<ContatoGeral, Long> {

    public List<ContatoGeral> findAllByOrderByIdAsc();

}
