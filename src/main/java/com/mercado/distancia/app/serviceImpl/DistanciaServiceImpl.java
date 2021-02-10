package com.mercado.distancia.app.serviceImpl;

import com.mercado.distancia.app.config.CountryConfig;
import com.mercado.distancia.app.config.DistanciaConfig;
import com.mercado.distancia.app.model.County;
import com.mercado.distancia.app.model.Distancia;
import com.mercado.distancia.app.repository.DistanciaRepository;
import com.mercado.distancia.app.service.DistanciaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

@Service
public class DistanciaServiceImpl implements DistanciaService {

  @Autowired
  private RestTemplate restTemplate;
  @Autowired
  private DistanciaRepository repository;
  @Autowired
  private DistanciaConfig distanciaConfig;
  @Autowired
  private CountryConfig countryConfig;


  @Override
  public void getApiDatosAll() {
    Distancia[] response = restTemplate.getForObject(distanciaConfig.getUrl() + "all", Distancia[].class);
    List<Distancia> distancia = Arrays.asList(response);
    repository.deleteAll();
    repository.saveAll(distancia);
  }


  @Override
  public Distancia findByAlphaCode(String alpha2Code) {
    Distancia distancia = repository.findByAlpha2Code(alpha2Code);
    return distancia;
  }


  @Override
  public void invocar(final Long id) {
    Distancia distancia = repository.findById(id).get();
    distancia.setInvocaciones(distancia.getInvocaciones() + 1);
    repository.save(distancia);
  }


  @Override
  public County getCountry(String ip) {
    final URI targetUrl = UriComponentsBuilder.fromUriString(countryConfig.getUrl() + ip).build().toUri();
    County response = restTemplate.getForObject(targetUrl, County.class);
    return response;
  }

}
