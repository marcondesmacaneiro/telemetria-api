package br.edu.unidavi.telemetria.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
    @Column(nullable = false, precision = 19, scale = 4)
    private BigDecimal leitura;

    @ManyToOne(optional = false)
    private LeituraPontoSensor leituraPontoSensor;

    @Column(nullable = false)
    private LocalDateTime dataHora;

    private LeituraSensor(BigDecimal leitura, LocalDateTime data) {
        this.leitura  = leitura;
        this.dataHora = data;
    }

    @Override
    public Long getId() {
        return id;
    }
    
    public String getDataHoraFormatada(){
        return dataHora.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
    }
    
    @JsonProperty("leituraFormatada")
    public String getLeituraFormatada(){
        String valor = String.valueOf(leitura);
        valor = !valor.contains(".") ? valor : valor.replaceAll("0*$", "").replaceAll("\\.$", "");
        return valor + " " + getComplementoLeitura();
    }
    
    private String getComplementoLeitura(){
        switch(leituraPontoSensor.getSensorLeitura().getId().intValue()){
            case SensorLeitura.SENSOR_NIVEL_RIO:
                return "m";
            case SensorLeitura.SENSOR_UMIDADE:
                return "%";
            case SensorLeitura.SENSOR_VENTO:
                return "km/h";
            case SensorLeitura.SENSOR_TEMPERATURA:
                return "ºC";
        }
        return "";
    }

    @JsonIgnore
    @Override
    public boolean isNew() {
        return Objects.isNull(id);
    }

    public static LeituraSensor of(BigDecimal leitura, LocalDateTime data) {
        return new LeituraSensor(leitura, data);
    }

}
