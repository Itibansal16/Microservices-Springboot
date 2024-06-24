package com.springbootpractice.orderservice.repository;

import com.springbootpractice.orderservice.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface IOrderRepository extends JpaRepository<Order, Long> {

}
