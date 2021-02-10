package com.mercado.distancia.app.config;

import lombok.Data;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties("service-countries")
public class DistanciaConfig {
  private String url;
}
