package com.example.demo.controller;

import com.example.demo.model.dto.UpdateUserRequest;
import com.example.demo.model.dto.UserSettingsDTO;
import com.example.demo.model.entity.User;
import com.example.demo.service.UserService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;

  @GetMapping("/{id}")
  public User getUserById(@PathVariable("id") Long id) {
    return userService.getUserById(id);
  }

  @GetMapping
  public List<User> getUsers() {
    return userService.getUsers();
  }

  @PutMapping("/{id}")
  public User updateUser(@PathVariable Long id, @Valid @RequestBody UpdateUserRequest request) {
    return userService.updateUser(id, request);
  }

  @GetMapping("/settings/{id}")
  public UserSettingsDTO getSettings(@PathVariable Long id) {
    return userService.getSettings(id);
  }
}
