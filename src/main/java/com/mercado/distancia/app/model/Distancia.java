package com.mercado.distancia.app.model;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "destinos")
public class Distancia implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
  private String alpha2Code;
  private Double lat;
  private Double lng;
  @ElementCollection
  private List<String> timezones;
  @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinColumn(name = "destinos_id")
  private List<Lenguages> languages;
  @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinColumn(name = "destinos_id")
  private List<Currencies> currencies;
  private static final long serialVersionUID = 5558341366693950561L;
  private Integer invocaciones = 1;

  public void setLatlng(List<Double> latlng) {
    this.lat = 0.0;
    this.lng = 0.0;
    if (!latlng.isEmpty()) {
      this.lat = latlng.get(0);
      this.lng = latlng.get(1);
    }

  }

}
