package com.example.demo.model.entity;

import jakarta.persistence.*;
import java.util.Date;
import lombok.Data;

@Entity
@Data
@Table(name = "users")
public class User {

  @Id @GeneratedValue private Long id;

  private String name;

  private String lastName;

  private Integer age;

  private boolean active;

  private Date created;
}
