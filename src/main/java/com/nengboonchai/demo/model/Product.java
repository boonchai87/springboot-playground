package com.nengboonchai.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="products")  
public class Product {
    @Id
    private Long id;
}
