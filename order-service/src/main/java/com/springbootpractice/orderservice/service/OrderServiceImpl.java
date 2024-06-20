package com.springbootpractice.orderservice.service;

import com.springbootpractice.orderservice.dto.OrderLineItemsDto;
import com.springbootpractice.orderservice.dto.OrderRequest;
import com.springbootpractice.orderservice.model.Order;
import com.springbootpractice.orderservice.model.OrderLineitems;
import com.springbootpractice.orderservice.repository.IOrderRepository;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderServiceImpl implements IOrderService{

  private final IOrderRepository iOrderRepository;

  @Override
  public void placeOrder(OrderRequest orderRequest) {
    Order order = buildOrderModel(orderRequest);
    iOrderRepository.save(order);
  }

  private Order buildOrderModel(OrderRequest orderRequest) {
    Order order = new Order();
    order.setOrderNumber(UUID.randomUUID().toString());
    order.setOrderLineItemsList(orderRequest.getOrderLineItemsDtoList()
        .stream()
        .map(this::mapToDto)
        .toList());

    return order;
  }

  private OrderLineitems mapToDto(OrderLineItemsDto orderLineItemsDto) {
    return OrderLineitems
        .builder()
        .price(orderLineItemsDto.getPrice())
        .quantity(orderLineItemsDto.getQuantity())
        .skuCode(orderLineItemsDto.getSkuCode())
        .build();
  }
}
