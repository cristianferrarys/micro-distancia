package com.mercado.distancia.app.controller;

import com.mercado.distancia.app.dto.DistanciaDto;
import com.mercado.distancia.app.dto.MonedaDto;
import com.mercado.distancia.app.dto.RequestIpDto;
import com.mercado.distancia.app.dto.ResquestEnvioDto;
import com.mercado.distancia.app.model.County;
import com.mercado.distancia.app.model.Distancia;
import com.mercado.distancia.app.rest.service.ClienteMonedaRest;
import com.mercado.distancia.app.rest.service.ClienteStatsRest;
import com.mercado.distancia.app.service.DistanciaService;
import com.mercado.distancia.app.utils.CalcularDistancia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@RestController
public class DistanciaController {
  @Autowired
  private DistanciaService distanciaService;

  @Autowired
  private ClienteStatsRest clienteRest;
  @Autowired
  private ClienteMonedaRest monedaRest;
  
  @PostMapping("/trace")
  public ResponseEntity<?> trace(@RequestBody final RequestIpDto ipDto) {

    String ips = ipDto.getIp();
    String[] ipSplit = ips.split(Pattern.quote("."));
    if(ipSplit.length-1 != 3 ) {
      return new ResponseEntity<>("Ingrese una Ip valida..", HttpStatus.BAD_REQUEST);
    }

    County country = distanciaService.getCountry(ips);
    Distancia dist = distanciaService.findByAlphaCode(country.getCountryCode());
    dist.setTimezones(addTime(dist));
    Double latBsAs = -34.603722;
    Double lngBsAs = -58.381592;

    Double estimated_distancia = CalcularDistancia.distance(latBsAs, lngBsAs, dist.getLat(), dist.getLng(), 'K');
    
    ResquestEnvioDto requestStats = new ResquestEnvioDto();
    requestStats.setDistancia(estimated_distancia);
    requestStats.setPais(dist.getName());
    
    clienteRest.getStats(requestStats);
    ///distanciaService.setStats(requestStats);

    String dateNow = LocalDate.now().toString();
    
    List<MonedaDto> monedas = monedaRest.getFechaMoneda(dateNow, "", dist.getCurrencies().get(0).getCode());

    String countryFull =  country.getCountryName()+ " (" + country.getCountryName() + ")";
    
    LocalDateTime fecha = LocalDateTime.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    String fechaString = fecha.format(formatter);
    
    DistanciaDto distanciaDto = new DistanciaDto();
    distanciaDto.setIp(ips);
    distanciaDto.setCountry(countryFull);
    distanciaDto.setIso_code(dist.getLanguages().get(0).getIso639_1());
    distanciaDto.setDate(fechaString);
    distanciaDto.setLanguages(addLenguages(dist));
    distanciaDto.setTimes(dist.getTimezones());
    distanciaDto.setEstimated_distance(estimated_distancia + " kms");
    distanciaDto.setCurrency(addMonedas(monedas));
    return new ResponseEntity<>(distanciaDto, HttpStatus.OK);
  }


  public List<String> addTime(Distancia dist) {
    List<String> timeZone = dist.getTimezones().stream().map(x -> {
      LocalDateTime ldt = LocalDateTime.now();
      ZonedDateTime ldtZoned = ldt.atZone(ZoneId.systemDefault());
      ZonedDateTime utcZoned = ldtZoned.withZoneSameInstant(ZoneId.of(x));
      LocalDateTime dateFinal = utcZoned.toLocalDateTime();
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
      String formatDateTime = dateFinal.format(formatter);
      return formatDateTime + " (" + x + ")";
    }).collect(Collectors.toList());
    return timeZone;
  }

  public List<String> addLenguages(Distancia dist) {
    List<String> lengu = dist.getLanguages().stream().map(leng -> {
      String lenguaje = leng.getNativeName() + " (" + leng.getIso639_1() + ")";
      return lenguaje;
    }).collect(Collectors.toList());
    return lengu;
  }

  public List<String> addMonedas(List<MonedaDto> monedas) {
    List<String> moned = monedas.stream().map(m -> {
      String mone = "EUR (1 EUR = " + m.getRates() + " " + m.getRatesKey()+")";
      return mone;
    }).collect(Collectors.toList());
    return moned;
  }



}
