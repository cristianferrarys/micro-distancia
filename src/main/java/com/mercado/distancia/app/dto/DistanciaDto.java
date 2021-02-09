package com.mercado.distancia.app.dto;

import lombok.Data;

import java.util.List;

@Data
public class DistanciaDto {
  private String ip;
  private String date;
  private String country;
  private String iso_code;
  private List<String> languages; 
  private List<String> currency;
  private List<String> times;
  private String estimated_distance;
  
}
