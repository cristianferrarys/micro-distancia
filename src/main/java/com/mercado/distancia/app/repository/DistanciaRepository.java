package com.mercado.distancia.app.repository;

import com.mercado.distancia.app.model.Distancia;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DistanciaRepository extends JpaRepository<Distancia, Long> {
  Distancia findByAlpha2Code(String alpha2Code);
}
