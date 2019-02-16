package com.zbin.cisp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@SpringBootApplication
public class CispApplication {

  public static void main(String[] args) {
    SpringApplication.run(CispApplication.class, args);
  }

  @Bean
  public WebServerFactoryCustomizer<ConfigurableWebServerFactory> webServerFactoryCustomizer() {
    return (container -> {
      ErrorPage error403Page = new ErrorPage(HttpStatus.UNAUTHORIZED, "/403");
      ErrorPage error404Page = new ErrorPage(HttpStatus.NOT_FOUND, "/404");
      ErrorPage error500Page = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/500");

      container.addErrorPages(error403Page, error404Page, error500Page);
    });
  }
}

