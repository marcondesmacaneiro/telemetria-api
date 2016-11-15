package br.edu.unidavi.telemetria.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
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
@Table(name = "tb_leitura_ponto_sensor")
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Relation(value = "leitura_ponto_sensor", collectionRelation = "leitura_ponto_sensores")
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@ToString(of = {"id"})
public class LeituraPontoSensor implements Serializable, Persistable<Long>, Identifiable<Long> {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "gen_leitura_ponto_sensor_id", sequenceName = "seq_leitura_ponto_sensor_id", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen_leitura_ponto_sensor_id")
    private Long id;

    @NotNull
    @Column(nullable = false)
    private boolean ativo;

    @NotNull
    @Column(nullable = false)
    private Boolean manual;

    @JsonIgnore
    @ManyToOne(optional = false)
    private LeituraPonto leituraPonto;

    @JsonIgnore
    @ManyToOne(optional = false)
    private SensorLeitura sensorLeitura;

    @JsonIgnore
    @CreatedDate
    @Column(nullable = false)
    private LocalDateTime createdTime;

    @JsonIgnore
    @LastModifiedDate
    private LocalDateTime updatedTime;

    private LeituraPontoSensor(boolean ativo, Boolean manual) {
        this.ativo  = ativo;
        this.manual = manual;
    }

    @Override
    public Long getId() {
        return id;
    }
    
    @JsonProperty("nomeSensor")
    public String getNomeSensor(){
        return this.sensorLeitura.getNome();
    }

    @JsonIgnore
    @Override
    public boolean isNew() {
        return Objects.isNull(id);
    }

    public static LeituraPontoSensor of(boolean ativo, Boolean manual) {
        return new LeituraPontoSensor(ativo, manual);
    }

}
