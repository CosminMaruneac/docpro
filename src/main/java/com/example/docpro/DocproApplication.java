package com.example.docpro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class DocproApplication {

  public static void main(String[] args) {
    SpringApplication.run(DocproApplication.class, args);
  }

}
