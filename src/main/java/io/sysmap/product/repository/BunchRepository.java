package io.sysmap.product.repository;

import io.sysmap.product.domain.Bunch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BunchRepository extends JpaRepository<Bunch, Long> {
}
