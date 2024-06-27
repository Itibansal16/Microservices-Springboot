package com.springbootpractice.inventoryservice.service;

import com.springbootpractice.inventoryservice.dto.InventoryResponse;
import com.springbootpractice.inventoryservice.repository.InventoryRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryServiceImpl implements InventoryService{

  private final InventoryRepository inventoryRepository;

  @Override
  @Transactional(readOnly = true)
  public List<InventoryResponse> isInStock(List<String> skuCodes) throws InterruptedException {
    return inventoryRepository.findBySkuCodeIn(skuCodes).stream()
        .map(inventory ->
          InventoryResponse.builder()
              .skuCode(inventory.getSkuCode())
              .isInStock(inventory.getQuantity() > 0)
              .build()
        ).toList();
  }
}
