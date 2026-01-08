package com.example.demo.service;

import com.example.demo.model.dto.UpdateUserRequest;
import com.example.demo.model.dto.UserSettingsDTO;
import com.example.demo.model.entity.User;
import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;

public interface UserService {
  User getUserById(Long id);

  List<User> getUsers();

  User updateUser(Long id, UpdateUserRequest request);

  UserSettingsDTO getSettings(@PathVariable Long id);
}
