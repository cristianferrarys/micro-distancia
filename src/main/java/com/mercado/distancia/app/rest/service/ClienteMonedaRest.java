package com.mercado.distancia.app.rest.service;

import com.mercado.distancia.app.dto.MonedaDto;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "micro-moneda")
public interface ClienteMonedaRest {

  @GetMapping("/moneda")
  public List<MonedaDto> getFechaMoneda(@RequestParam(name = "fecha", defaultValue = "NA") String fecha,
      @RequestParam(name = "monedaOrigen", defaultValue = "EUR") String monedaOrigen,
      @RequestParam(name = "moneda", defaultValue = "EUR") String moneda);
}
