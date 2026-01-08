package com.example.demo.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UpdateUserRequest {
  @NotBlank(message = "Property name is required")
  private String name;
}
