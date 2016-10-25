package br.com.marcondesmacaneiro.domain.repository.novo;

import br.com.marcondesmacaneiro.domain.model.novo.Municipio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

/**
 * Created by marcondesmacaneiro on 18/10/16.
 */
@RepositoryRestResource(collectionResourceRel = "municipios", itemResourceRel = "municipio" , path = "municipio")
interface MunicipioRepository extends JpaRepository<Municipio, Long> {
}
