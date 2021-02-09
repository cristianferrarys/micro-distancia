package com.mercado.distancia.app.task;

import com.mercado.distancia.app.service.DistanciaService;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CargaTask {

  @Autowired
  private DistanciaService distanciaService;

  @Scheduled(cron = "0 08 11 * * *")
  public void cargarDbMoneda() {
    try {
      distanciaService.getApiDatosAll();
      log.info("Se carga de datos a las 10:03 todo los dias ");
    } catch (final Exception ex) {
      log.error("Error en la carga de la db ", ex);
    }
  }
  
}
