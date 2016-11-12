package br.edu.unidavi.telemetria.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
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
@Table(name = "tb_leitura_ponto")
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Relation(value = "leitura_ponto", collectionRelation = "leitura_pontos")
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@ToString(of = {"id", "nome"})
public class LeituraPonto implements Serializable, Persistable<Long>, Identifiable<Long> {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "gen_leitura_ponto_id", sequenceName = "seq_leitura_ponto_id", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen_leitura_ponto_id")
    private Long id;

    @NotNull
    @Size(min = 1, max = 200)
    @Column(nullable = false, length = 200)
    private String nome;

    @NotNull
    @Size(min = 1, max = 250)
    @Column(nullable = false, length = 250)
    private String endereco;

    @NotNull
    @Size(min = 1, max = 100)
    @Column(nullable = false, length = 100)
    private String localizacao;

    @OneToMany(mappedBy = "leituraPonto")
    @Cascade(CascadeType.DELETE)
    private List<LeituraPontoSensor> sensores;

    @JsonIgnore
    @CreatedDate
    @Column(nullable = false)
    private LocalDateTime createdTime;

    @JsonIgnore
    @LastModifiedDate
    private LocalDateTime updatedTime;

    private LeituraPonto(String nome, String localizacao) {
        this.nome = nome;
        this.localizacao = localizacao;
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

    public static LeituraPonto of(String nome, String localizacao) {
        return new LeituraPonto(nome, localizacao);
    }

}
