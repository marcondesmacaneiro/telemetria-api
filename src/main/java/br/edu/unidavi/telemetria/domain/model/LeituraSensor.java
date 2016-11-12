package br.edu.unidavi.telemetria.domain.model;

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
import org.springframework.data.domain.Persistable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.hateoas.Identifiable;
import org.springframework.hateoas.core.Relation;

/**
 *
 * @author marcondes 09/11/2016
 */
@Entity
@Table(name = "tb_leitura_sensor")
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Relation(value = "leitura_sensor", collectionRelation = "leituras_sensores")
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@ToString(of = {"id", "nome"})
public class LeituraSensor implements Serializable, Persistable<Long>, Identifiable<Long> {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "gen_leitura_sensor_id", sequenceName = "seq_leitura_sensor_id", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen_leitura_sensor_id")
    private Long id;

    @NotNull
    @Size(min = 1, max = 200)
    @Column(nullable = false, length = 200)
    private String nome;

    @NotNull
    @Column(nullable = false)
    private float leitura;

    @ManyToOne(optional = false)
    private LeituraPontoSensor leituraPontoSensor;

    @JsonIgnore
    @CreatedDate
    @Column(nullable = false)
    private LocalDateTime createdTime;

    private LeituraSensor(String nome, float leitura) {
        this.nome    = nome;
        this.leitura = leitura;
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

    public static LeituraSensor of(String nome, float leitura) {
        return new LeituraSensor(nome, leitura);
    }

}
