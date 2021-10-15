package com.ailtonluiz.erpapi.domain.repository;

import com.ailtonluiz.erpapi.domain.model.Order;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface OrderRepository extends CustomJpaRepository<Order, Long>, JpaSpecificationExecutor<Order> {

    Optional<Order> findByCode(String code);



}
