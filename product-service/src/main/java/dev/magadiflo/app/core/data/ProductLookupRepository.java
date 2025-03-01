package dev.magadiflo.app.core.data;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductLookupRepository extends JpaRepository<ProductLookupEntity, String> {
    Optional<ProductLookupEntity> findByProductIdOrTitle(String productId, String title);
}
