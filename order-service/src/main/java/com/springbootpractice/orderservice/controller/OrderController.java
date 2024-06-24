package com.springbootpractice.orderservice.controller;

import com.springbootpractice.orderservice.dto.OrderRequest;
import com.springbootpractice.orderservice.service.IOrderService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/order")
public class OrderController {
  private final IOrderService iOrderService;

  public OrderController(IOrderService iOrderService) {
    this.iOrderService = iOrderService;
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public String placeOrder(@RequestBody OrderRequest orderRequest){
    try {
      iOrderService.placeOrder(orderRequest);
      return "placed successfully";
    } catch(IllegalArgumentException e) {
      return e.getMessage();
    } catch (Exception e) {
      return e.getMessage();
    }
  }
}
