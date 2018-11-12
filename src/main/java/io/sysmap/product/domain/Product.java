package io.sysmap.product.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;
import static javax.persistence.CascadeType.ALL;
import static javax.persistence.GenerationType.IDENTITY;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Product")
@JsonInclude(NON_EMPTY)
public class Product {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "idt_product")
    private Long id;

    @Column(name = "nam_product", nullable = false)
    private String name;

    @Column(name = "desc_product", nullable = false)
    private String description;

    @Default
    @Column(name = "flg_enabled")
    private boolean enabled = true;

    @Column(name = "user_creation", nullable = false)
    private String userCreation;

    @Column(name = "dat_creation", updatable = false, nullable = false)
    private LocalDateTime creationDate;

    @Column(name = "dat_update", nullable = false)
    private LocalDateTime updateDate;

    @PrePersist
    public void prePersist() {
        creationDate = LocalDateTime.now();
        updateDate = LocalDateTime.now();
    }

    @OneToMany(cascade= ALL, mappedBy="product")
    public List<Bunch> bunch;
}
