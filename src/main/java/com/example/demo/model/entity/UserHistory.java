package com.example.demo.model.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class UserHistory {
  @Id @GeneratedValue private Long id;

  @Enumerated(EnumType.STRING)
  private UserActionType userActionType;

  public enum UserActionType {
    NAME_CHANGE,
    ETC
  }

  @ManyToOne private User user;
}
