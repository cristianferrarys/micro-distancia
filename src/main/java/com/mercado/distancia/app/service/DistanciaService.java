package com.mercado.distancia.app.service;

import com.mercado.distancia.app.dto.MonedaDto;
import com.mercado.distancia.app.model.County;
import com.mercado.distancia.app.model.Distancia;

import java.util.List;

public interface DistanciaService {

  void getApiDatosAll();

  Distancia findByAlphaCode(String alpha2Code);

  void invocar(Long id);

  List<MonedaDto> getMoneda(String fecha, String monedaOrigen, String moneda);

  void setStats(String pais, Double distancia);
  
  County getCountry(String ip);
  
  
}
