package br.edu.unidavi.telemetria.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
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
@Table(name = "tb_sensor_leitura")
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Relation(value = "sensor_leitura", collectionRelation = "sensor_leituras")
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@ToString(of = {"id", "nome"})
public class SensorLeitura implements Serializable, Persistable<Long>, Identifiable<Long> {
    
    public static final int SENSOR_NIVEL_RIO   = 1;
    public static final int SENSOR_UMIDADE     = 2;
    public static final int SENSOR_VENTO       = 3;
    public static final int SENSOR_TEMPERATURA = 4;

    private static final long serialVersionUID = 1L;

    @Id
    private Long id;

    @NotNull
    @Size(min = 1, max = 200)
    @Column(nullable = false, length = 200)
    private String nome;

    private SensorLeitura(String nome) {
        this.nome = nome;
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

    public static SensorLeitura of(String nome) {
        return new SensorLeitura(nome);
    }

}
