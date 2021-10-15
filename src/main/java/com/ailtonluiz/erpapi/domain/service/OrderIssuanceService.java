package com.ailtonluiz.erpapi.domain.service;

import com.ailtonluiz.erpapi.domain.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderIssuanceService {

    @Autowired
    private OrderRepository orderRepository;


}
