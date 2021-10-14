package com.ailtonluiz.erpapi.domain.repository;

import com.ailtonluiz.erpapi.domain.model.Provider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProviderRepository extends JpaRepository<Provider, Long> {
}
