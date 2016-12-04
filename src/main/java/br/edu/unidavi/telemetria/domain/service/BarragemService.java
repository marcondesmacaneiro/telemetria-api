package br.edu.unidavi.telemetria.domain.service;

import br.edu.unidavi.telemetria.domain.model.Barragem;
import br.edu.unidavi.telemetria.domain.model.BarragemHistorico;
import br.edu.unidavi.telemetria.domain.repository.BarragemHistoricoRepository;
import br.edu.unidavi.telemetria.domain.repository.BarragemRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Bruno Pasqualini
 */
@Service
@Transactional(readOnly = true)
public class BarragemService {

    @Autowired
    private BarragemRepository repository;

    @Autowired
    private BarragemHistoricoRepository repoHistorico;

    public List<Barragem> findAll() {
        List<Barragem> barragens = repository.findAllByOrderByIdAsc();
        for (Barragem barragem : barragens) {
            encontraUltimoHistorico(barragem);
        }
        return barragens;
    }

    public Optional<Barragem> findOne(Long id) {
        Barragem barragem = repository.findOne(id);
        encontraUltimoHistorico(barragem);
        return Optional.ofNullable(barragem);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Barragem save(Barragem barragem) {
        return repository.save(barragem);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void delete(Barragem barragem) {
        repository.delete(barragem);
    }

    private void encontraUltimoHistorico(Barragem barragem) {
        if (barragem != null) {
            BarragemHistorico historico = repoHistorico.findFirstByBarragemIdOrderByIdDesc(barragem.getId());
            if (historico != null) {
                barragem.setUltimoHistorico(historico);
            }
        }
    }
}
