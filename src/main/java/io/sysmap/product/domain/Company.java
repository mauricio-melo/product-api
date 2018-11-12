package io.sysmap.product.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
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
@Table(name = "Company")
@JsonInclude(NON_EMPTY)
public class Company {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "idt_company")
    private Long id;

    @Column(name = "cnpj_company", nullable = false)
    private Long cnpj;

    @Builder.Default
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

    @ManyToMany
    @JoinTable(name = "CompanyProduct", joinColumns = @JoinColumn(name = "idt_company"),
            inverseJoinColumns = @JoinColumn(name = "idt_product"))
    private Set<Product> product;
}
