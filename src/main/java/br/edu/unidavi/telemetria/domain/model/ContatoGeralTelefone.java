package br.edu.unidavi.telemetria.domain.model;

import br.edu.unidavi.telemetria.domain.vo.Phone;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
 *
 * @author marcondes 09/11/2016
 */
@Entity
@Table(name = "tb_contato_geral_telefone")
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Relation(value = "contato_geral_telefone", collectionRelation = "contatos_gerais_telefones")
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@ToString(of = {"id", "numero"})
public class ContatoGeralTelefone implements Serializable, Persistable<Long>, Identifiable<Long> {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "gen_contato_geral_telefone_id", sequenceName = "seq_contato_geral_telefone_id", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen_contato_geral_telefone_id")
    private Long id;

    @NotNull
    @Size(min = 1, max = 200)
    @Column(nullable = false, length = 200)
    private Phone numero;

    @JsonIgnore
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private ContatoGeral contatoGeral;

    @JsonIgnore
    @CreatedDate
    @Column(nullable = false)
    private LocalDateTime createdTime;

    @JsonIgnore
    @LastModifiedDate
    private LocalDateTime updatedTime;

    private ContatoGeralTelefone(Phone numero) {
        this.numero = numero;
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

    public static ContatoGeralTelefone of(Phone numero) {
        return new ContatoGeralTelefone(numero);
    }

}
