package br.edu.unidavi.telemetria.domain.model;

import br.edu.unidavi.telemetria.domain.vo.Phone;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.Persistable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.hateoas.Identifiable;
import org.springframework.hateoas.core.Relation;

/**
 * @author marcondes 01/11/2016
 */
@Entity
@Table(name = "tb_abrigo_contato")
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Relation(value = "abrigo_contato", collectionRelation = "abrigo_contatos")
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@ToString(of = {"id", "responsavel"})
public class AbrigoContato implements Serializable, Persistable<Long>, Identifiable<Long> {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "gen_cidade_contato_id", sequenceName = "seq_cidade_contato_id", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen_cidade_contato_id")
    private Long id;

    @NotNull
    @Size(min = 1, max = 100)
    @Column(nullable = false, length = 100)
    private String responsavel;

    @NotNull
    @Column(nullable = false, unique = false, length = Phone.MAX_LENGHT)
    private Phone telefone;

    @NotNull
    private boolean principal;

    @ManyToOne(optional = false)
    private Abrigo abrigo;

    @JsonIgnore
    @CreatedDate
    @Column(nullable = false)
    private LocalDateTime createdTime;

    @JsonIgnore
    @LastModifiedDate
    private LocalDateTime updatedTime;

    private AbrigoContato(String responsavel, Phone telefone, boolean principal, Abrigo abrigo) {
        this.responsavel = responsavel;
        this.telefone = telefone;
        this.principal = principal;
        this.abrigo = abrigo;
    }

    @Override
    public Long getId() {
        return id;
    }

    @JsonIgnore
    @Override
    public boolean isNew() {
        return Objects.isNull(id);
    }

    public static AbrigoContato of(String responsavel, Phone telefone, boolean principal, Abrigo abrigo) {
        return new AbrigoContato(responsavel, telefone, principal, abrigo);
    }
}
