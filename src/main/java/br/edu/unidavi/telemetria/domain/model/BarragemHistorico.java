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
import javax.persistence.FetchType;
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
 * @author Bruno Pasqualini
 */
@Entity
@Table(name = "tb_barragem_historico")
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Relation(value = "barragem_historico", collectionRelation = "barragens_historicos")
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@ToString(of = {"id", "montante", "comportasAbertas", "comportasFechadas"})
public class BarragemHistorico implements Serializable, Persistable<Long>, Identifiable<Long> {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "gen_barragem_historico_id", sequenceName = "seq_barragem_historico_id", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen_barragem_historico_id")
    private Long id;

    @NotNull
    @Column(nullable = false, precision = 19, scale = 4)
    private BigDecimal montante;
    
    @NotNull
    @Column(nullable = false)
    private Integer comportasAbertas;
    
    @NotNull
    @Column(nullable = false)
    private Integer comportasFechadas;
    
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Barragem barragem;

    @Column(nullable = false)
    private LocalDateTime dataHora;

    public BarragemHistorico(BigDecimal montante, Integer comportasAbertas, Integer comportasFechadas, LocalDateTime dataHora) {
        this.montante = montante;
        this.comportasAbertas = comportasAbertas;
        this.comportasFechadas = comportasFechadas;
        this.dataHora = dataHora;
    }
    
    @Override
    public Long getId() {
        return id;
    }
    
    @JsonProperty("dataHoraFormatada")
    public String getDataHoraFormatada(){
        return dataHora.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
    }

    @JsonIgnore
    @Override
    public boolean isNew() {
        return Objects.isNull(id);
    }

    public static BarragemHistorico of(BigDecimal montante, Integer comportasAbertas, Integer comportasFechadas, LocalDateTime dataHora) {
        return new BarragemHistorico(montante, comportasAbertas, comportasFechadas, dataHora);
    }

}
