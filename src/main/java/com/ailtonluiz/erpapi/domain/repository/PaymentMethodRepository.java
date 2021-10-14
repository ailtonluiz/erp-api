package com.ailtonluiz.erpapi.domain.repository;

import com.ailtonluiz.erpapi.domain.model.PaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;

@Repository
public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, Long> {
    @Query("select max(updateDate) from PaymentMethod")
    OffsetDateTime getEndUpdateDate();

    @Query("select updateDate from PaymentMethod where id = :paymentMethodId")
    OffsetDateTime getEndUpdateDateById(Long paymentMethodId);
}
