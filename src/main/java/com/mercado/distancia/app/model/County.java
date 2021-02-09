package com.mercado.distancia.app.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class County implements Serializable {

  private String countryCode;
  private String countryCode3;
  private String countryName;
  private String countryEmoji;

  private static final long serialVersionUID = -2923086408532478565L;


}
