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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Check;
import org.hibernate.annotations.Type;
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
@Table(name = "tb_doacao")
@Check(constraints = "tipo = ANY(array["+Doacao.TIPO_ENTIDADE+", "+Doacao.TIPO_INDIVIDUAL+"])")
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Relation(value = "doacao", collectionRelation = "doacoes")
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@ToString(of = {"id", "nome"})
public class Doacao implements Serializable, Persistable<Long>, Identifiable<Long> {
    
    public static final int TIPO_ENTIDADE   = 1;
    public static final int TIPO_INDIVIDUAL = 2;

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "gen_doacao_id", sequenceName = "seq_doacao_id", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen_doacao_id")
    private Long id;

    @NotNull
    @Column(nullable = false)
    private Integer tipo;

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
    
    @NotNull
    @Type(type = "text")
    private String informacoes;

    @JsonIgnore
    @CreatedDate
    @Column(nullable = false)
    private LocalDateTime createdTime;

    @JsonIgnore
    @LastModifiedDate
    private LocalDateTime updatedTime;

    private Doacao(Integer tipo, String nome, String endereco, String localizacao, String informacoes) {
        this.tipo = tipo;
        this.nome = nome;
        this.endereco = endereco;
        this.localizacao = localizacao;
        this.informacoes = informacoes;
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

    public static Doacao of(Integer tipo, String nome, String endereco, String localizacao, String informacoes) {
        return new Doacao(tipo, nome, endereco, localizacao, informacoes);
    }

}
