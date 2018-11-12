package io.sysmap.product.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;
import static javax.persistence.GenerationType.IDENTITY;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Feature")
@JsonInclude(NON_EMPTY)
public class Feature {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "idt_feature")
    private Long id;

    @Column(name = "nam_feature", nullable = false)
    private String name;

    @Column(name = "desc_feature", nullable = false)
    private String description;

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

    @ManyToOne
    @JoinColumn(name = "idt_bunch")
    private Bunch bunch;
}
