package com.zbin.cisp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@SpringBootApplication
public class CispApplication {

  public static void main(String[] args) {
    SpringApplication.run(CispApplication.class, args);
  }

}

