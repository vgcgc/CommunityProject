package com.zerobase.communityproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class CommunityProjectApplication {

  public static void main(String[] args) {
    SpringApplication.run(CommunityProjectApplication.class, args);
  }

}
