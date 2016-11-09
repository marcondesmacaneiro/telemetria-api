package br.edu.unidavi.telemetria.domain.service;

import br.edu.unidavi.telemetria.domain.model.Abrigo;
import br.edu.unidavi.telemetria.domain.model.AbrigoContato;
import br.edu.unidavi.telemetria.domain.repository.AbrigoContatoRepository;
import br.edu.unidavi.telemetria.domain.repository.AbrigoRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author marcondes 01/11/2016
 */
@Service
@Transactional(readOnly = true)
public class AbrigoService {

    @Autowired
    private AbrigoRepository repository;

    @Autowired
    private AbrigoContatoRepository repositoryContato;

    public List<Abrigo> findAll() {
        List<Abrigo> abrigos = repository.findAllByOrderByIdAsc();
        for (Abrigo abrigo : abrigos) {
            encontraContatoPrincipal(abrigo);
        }
        return abrigos;
    }

    public Optional<Abrigo> findOne(Long id) {
        Abrigo abrigo = repository.findOne(id);
        encontraContatoPrincipal(abrigo);
        return Optional.ofNullable(abrigo);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Abrigo save(Abrigo abrigo) {
        return repository.save(abrigo);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void delete(Abrigo abrigo) {
        repository.delete(abrigo);
    }

    private void encontraContatoPrincipal(Abrigo abrigo) {
        if (abrigo != null) {
            System.out.println(abrigo.getId());
            AbrigoContato contato = repositoryContato.findFirstByAbrigoIdAndPrincipalTrue(abrigo.getId());
            if(contato != null){
                abrigo.setContatoPrincipal(contato.getTelefone().toString());
            }
        }
    }
}
