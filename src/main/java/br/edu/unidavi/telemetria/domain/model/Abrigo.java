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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
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
@Table(name = "tb_abrigo")
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Relation(value = "abrigo", collectionRelation = "abrigos")
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@ToString(of = {"id", "nome"})
public class Abrigo implements Serializable, Persistable<Long>, Identifiable<Long> {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "gen_abrigo_id", sequenceName = "seq_abrigo_id", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen_abrigo_id")
    private Long id;

    @NotNull
    @Size(min = 1, max = 100)
    @Column(nullable = false, length = 100)
    private String nome;

    @NotNull
    @Size(min = 1, max = 100)
    @Column(nullable = false, length = 100)
    private String responsavel;

    @NotNull
    @Size(min = 10, max = 300)
    @Column(nullable = false, length = 300)
    private String imagem;

    @NotNull
    @Column(nullable = false, length = 100)
    private Integer lotacaoMaxima;

    @NotNull
    @Column(nullable = false, length = 100)
    private Integer lotacaoAtual;

    @NotNull
    @Size(min = 1, max = 100)
    @Column(nullable = false, length = 100)
    private String localizacao;
    
    @Transient
    private String contatoPrincipal;

    @JsonIgnore
    @CreatedDate
    @Column(nullable = false)
    private LocalDateTime createdTime;

    @JsonIgnore
    @LastModifiedDate
    private LocalDateTime updatedTime;

    private Abrigo(String nome, String responsavel, String imagem, Integer lotacaoMaxima, Integer lotacaoAtual) {
        this.nome = nome;
        this.responsavel = responsavel;
        this.imagem = imagem;
        this.lotacaoMaxima = lotacaoMaxima;
        this.lotacaoAtual = lotacaoAtual;
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

    public static Abrigo of(String nome, String responsavel, String imagem, Integer lotacaoMaxima, Integer lotacaoAtual) {
        return new Abrigo(nome, responsavel, imagem, lotacaoMaxima, lotacaoAtual);
    }
}
