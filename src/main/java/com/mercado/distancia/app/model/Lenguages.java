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
@Table(name = "lenguages")
public class Lenguages implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String iso639_1;
  private String iso639_2;
  private String nativeName;
  private static final long serialVersionUID = 5481009520922538844L;

}
