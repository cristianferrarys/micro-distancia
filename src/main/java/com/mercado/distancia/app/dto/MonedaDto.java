package com.mercado.distancia.app.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class MonedaDto implements Serializable {
  private String ratesKey;
  private Double rates;
  private static final long serialVersionUID = 649015269977098927L;

  public MonedaDto(String ratesKey, Double rates) {
    this.ratesKey = ratesKey;
    this.rates = rates;
  }

  public MonedaDto() {}

}
