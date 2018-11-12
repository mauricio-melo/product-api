package io.sysmap.product.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;
import static javax.persistence.CascadeType.ALL;
import static javax.persistence.GenerationType.IDENTITY;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Bunch")
@JsonInclude(NON_EMPTY)
public class Bunch {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "idt_bunch")
    private Long id;

    @Column(name = "nam_bunch")
    private String name;

    @Column(name = "desc_bunch")
    private String description;

    @Builder.Default
    @Column(name = "flg_enabled")
    private boolean enabled = true;

    @Column(name = "user_creation")
    private String userCreation;

    @Column(name = "dat_creation", updatable = false)
    private LocalDateTime creationDate;

    @Column(name = "dat_update")
    private LocalDateTime updateDate;

    @PrePersist
    public void prePersist() {
        creationDate = LocalDateTime.now();
        updateDate = LocalDateTime.now();
    }

    @ManyToOne
    @JoinColumn(name = "idt_product")
    private Product product;

    @OneToMany(cascade=ALL, mappedBy="bunch")
    public List<Feature> feature;
}
