package com.springbootpractice.productservice.controller;

import com.springbootpractice.productservice.dto.ProductRequest;
import com.springbootpractice.productservice.dto.ProductResponse;
import com.springbootpractice.productservice.service.IProductService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
@Slf4j
public class ProductController {

  private final IProductService iProductService;
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public void createProduct(@RequestBody ProductRequest productRequest){
    log.info("creating product");
    iProductService.createProduct(productRequest);
    log.info("product created");
  }

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<ProductResponse> listProducts(){
    log.info("fetching all products");
    return iProductService.getAllProducts();
  }
}
