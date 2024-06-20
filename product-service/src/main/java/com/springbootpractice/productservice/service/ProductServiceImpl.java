package com.springbootpractice.productservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springbootpractice.productservice.dto.ProductRequest;
import com.springbootpractice.productservice.dto.ProductResponse;
import com.springbootpractice.productservice.model.Product;
import com.springbootpractice.productservice.repository.ProductRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements IProductService{

  private final ProductRepository productRepository;
  private final ObjectMapper objectMapper;

  @Override
  public void createProduct(final ProductRequest productRequest) {
    Product product = mapToProduct(productRequest);
    productRepository.save(product);
    log.info("product {} is saved", product.getId());
  }

  @Override
  public List<ProductResponse> getAllProducts() {
    List<Product> products = productRepository.findAll();
    return products.stream().map(this::mapToProductResponse).toList();
  }

  private Product mapToProduct(final ProductRequest productRequest){
    return Product.builder()
        .price(productRequest.getPrice())
        .name(productRequest.getName())
        .description(productRequest.getDescription()).build();
  }

  private ProductResponse mapToProductResponse(final Product product){
    return objectMapper.convertValue(product, ProductResponse.class);
  }
}
