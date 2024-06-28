package com.springbootpractice.orderservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class NotificationService {
  @KafkaListener(groupId = "my-group-id", topics = "inventory-topic")
  public void listen(String message){
    log.info(message);
  }
}
