package com.mercado.distancia.app.rest.service;

import com.mercado.distancia.app.dto.ResquestEnvioDto;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "micro-stats")
public interface ClienteStatsRest {

  @PostMapping("/envio")
  void getStats(@RequestBody final ResquestEnvioDto req); 
}
