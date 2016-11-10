package br.edu.unidavi.telemetria.domain.service;

import br.edu.unidavi.telemetria.domain.model.Usuario;
import br.edu.unidavi.telemetria.domain.repository.UsuarioRepository;
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
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    public List<Usuario> findAll() {
        return repository.findAllByOrderByIdAsc();
    }

    public Optional<Usuario> findOne(Long id) {
        return Optional.ofNullable(repository.findOne(id));
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Usuario save(Usuario usuario) {
        return repository.save(usuario);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void delete(Usuario usuario) {
        repository.delete(usuario);
    }
}
