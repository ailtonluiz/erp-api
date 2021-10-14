package com.ailtonluiz.erpapi.domain.repository;

import com.ailtonluiz.erpapi.domain.model.Unit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnitRepository extends JpaRepository<Unit, Long> {
}
