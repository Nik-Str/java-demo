package com.example.demo.model.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "users")
public class User {

  @Id @GeneratedValue private Long id;

  private String name;
}
