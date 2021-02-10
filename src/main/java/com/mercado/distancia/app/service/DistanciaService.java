package com.mercado.distancia.app.service;

import com.mercado.distancia.app.model.County;
import com.mercado.distancia.app.model.Distancia;

public interface DistanciaService {

  void getApiDatosAll();

  Distancia findByAlphaCode(String alpha2Code);

  void invocar(Long id);

  County getCountry(String ip);


}
