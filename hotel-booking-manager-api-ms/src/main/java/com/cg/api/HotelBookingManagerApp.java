package com.cg.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class HotelBookingManagerApp {
  public static void main(String[] args) {
    log.info(">>Inside SpringBootJPA MS hotel Manager API with in-memory H2 DB");
    SpringApplication.run(HotelBookingManagerApp.class, args);
  }
}
