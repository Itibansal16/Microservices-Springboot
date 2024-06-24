package com.springbootpractice.productservice.service;

import com.springbootpractice.productservice.dto.ProductRequest;
import com.springbootpractice.productservice.dto.ProductResponse;
import java.util.List;

public interface IProductService {
  void createProduct(ProductRequest productRequest);

  List<ProductResponse> getAllProducts();
}
