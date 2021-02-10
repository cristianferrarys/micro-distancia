package com.mercado.distancia.app.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class ResquestEnvioDto implements Serializable {

  private Double distancia;
  private String pais;
  private static final long serialVersionUID = 988721422186783961L;

}
