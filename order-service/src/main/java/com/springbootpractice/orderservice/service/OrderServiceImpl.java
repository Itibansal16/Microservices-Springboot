package com.springbootpractice.orderservice.service;

import com.springbootpractice.orderservice.dto.InventoryResponse;
import com.springbootpractice.orderservice.dto.OrderLineItemsDto;
import com.springbootpractice.orderservice.dto.OrderRequest;
import com.springbootpractice.orderservice.model.Order;
import com.springbootpractice.orderservice.model.OrderLineitems;
import com.springbootpractice.orderservice.repository.IOrderRepository;
import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderServiceImpl implements IOrderService{

  private final IOrderRepository iOrderRepository;
  private final WebClient.Builder webClientBuilder;

  @Override
  public void placeOrder(OrderRequest orderRequest) {
    Order order = buildOrderModel(orderRequest);

    List<String> skuCodes = order.getOrderLineItemsList().stream().map(OrderLineitems::getSkuCode).toList();

    //Before proceeding, check if the product is in stock via Inventory microservice
    InventoryResponse[] inventoryResponseArray = webClientBuilder.build().get()
        .uri("http://inventory-service/api/inventory", uriBuilder ->
          uriBuilder.queryParam("skuCode", skuCodes).build()
        )
            .retrieve()
                .bodyToMono(InventoryResponse[].class)
                    .block();
    Boolean areAllProductsInStock = Arrays.stream(inventoryResponseArray).allMatch(
        InventoryResponse::isInStock);
    if(areAllProductsInStock) {
      iOrderRepository.save(order);
    } else {
      throw new IllegalArgumentException("Product out of Stock, Please try later");
    }
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
