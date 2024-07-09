package com.example.hexagonal.application;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ProductService {

  public String getProducts() {
    // Implementación de obtención de productos
    return "Lista de productos de España (Murcia). Curso Hexagonal";
  }
}
