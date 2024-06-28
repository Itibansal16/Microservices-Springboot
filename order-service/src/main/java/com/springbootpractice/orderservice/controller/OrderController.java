package com.springbootpractice.orderservice.controller;

import com.springbootpractice.orderservice.dto.OrderRequest;
import com.springbootpractice.orderservice.service.IOrderService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import java.util.concurrent.CompletableFuture;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/order")
@Slf4j
public class OrderController {
  private final IOrderService iOrderService;

  public OrderController(IOrderService iOrderService) {
    this.iOrderService = iOrderService;
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  @CircuitBreaker(name = "inventory", fallbackMethod = "fallBack")
  @Retry(name = "inventory")
  @TimeLimiter(name = "inventory")
  public CompletableFuture<String> placeOrder(@RequestBody OrderRequest orderRequest){
    log.info("Order Place received at Controller");
    log.info("calling OrderService");
    return CompletableFuture.supplyAsync(() -> iOrderService.placeOrder(orderRequest));
  }

  public CompletableFuture<String> fallBack(OrderRequest orderRequest, Exception ex){
    return CompletableFuture.supplyAsync(() -> "Called fallback as downstream is down" + ex.getMessage());
  }
}
