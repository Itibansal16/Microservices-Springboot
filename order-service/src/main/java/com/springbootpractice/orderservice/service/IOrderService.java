package com.springbootpractice.orderservice.service;

import com.springbootpractice.orderservice.dto.OrderRequest;

public interface IOrderService {
  void placeOrder(OrderRequest orderRequest);
}
