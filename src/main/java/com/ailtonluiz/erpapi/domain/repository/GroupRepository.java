package com.ailtonluiz.erpapi.domain.repository;

import com.ailtonluiz.erpapi.domain.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {
}
