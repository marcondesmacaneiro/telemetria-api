    package br.edu.unidavi.telemetria.domain.repository;

import br.edu.unidavi.telemetria.domain.model.ContatoGeralTelefone;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by marcondesmacaneiro on 10/10/16.
 */
@RepositoryRestResource(collectionResourceRel = "contatosgeraistelefones", itemResourceRel = "contatogeraltelefone" , path = "contatogeraltelefone")
public interface ContatoGeralTelefoneRepository extends JpaRepository<ContatoGeralTelefone, Long> {

    public List<ContatoGeralTelefone> findAllByOrderByIdAsc();

}
