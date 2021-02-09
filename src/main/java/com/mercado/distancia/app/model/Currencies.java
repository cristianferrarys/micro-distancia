package com.mercado.distancia.app.model;

import lombok.Data;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "currencies")
public class Currencies implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  public Long id;
  public String code;
  public String name;
  public String symbol;
  private static final long serialVersionUID = -1373524006734636666L;

}
