package com.springbootpractice.inventoryservice.service;

import com.springbootpractice.inventoryservice.dto.InventoryResponse;
import java.util.List;

public interface InventoryService {
  List<InventoryResponse> isInStock(List<String> skuCodes);

}
