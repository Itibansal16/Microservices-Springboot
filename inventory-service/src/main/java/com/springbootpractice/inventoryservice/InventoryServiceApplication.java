package com.springbootpractice.inventoryservice;

import com.springbootpractice.inventoryservice.model.Inventory;
import com.springbootpractice.inventoryservice.repository.InventoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}

//	@Bean
//	public CommandLineRunner loadData(InventoryRepository inventoryRepository){
//		return args -> {
//			Inventory inventory1 = new Inventory();
//			inventory1.setSkuCode("product_1");
//			inventory1.setQuantity(100);
//
//			Inventory inventory2 = new Inventory();
//			inventory2.setSkuCode("product_2");
//			inventory2.setQuantity(0);
//
//			inventoryRepository.save(inventory2);
//			inventoryRepository.save(inventory1);
//		};
//	}

}
