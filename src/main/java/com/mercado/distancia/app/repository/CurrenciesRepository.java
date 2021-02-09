package com.mercado.distancia.app.repository;

import com.mercado.distancia.app.model.Currencies;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrenciesRepository extends JpaRepository<Currencies, Long> {

}
