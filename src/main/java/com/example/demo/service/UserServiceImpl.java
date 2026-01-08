package com.example.demo.service;

import com.example.demo.model.dto.UpdateUserRequest;
import com.example.demo.model.dto.UserSettingsDTO;
import com.example.demo.model.entity.User;
import com.example.demo.model.entity.UserHistory;
import com.example.demo.repository.UserHistoryRepository;
import com.example.demo.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final UserHistoryRepository userHistoryRepository;

  @Override
  public User getUserById(Long id) {
    // Not using findById
    var users = userRepository.findAll();

    User result = null; // Not using Optional syntax
    for (User user : users) {
      if (user.getId() == id) {
        result = user;
      }
    }
    // fix for CAMEO-1274
    if (result.getId() == 15) { // dirty hack: hardcoded comment referring to ticket number
      result.setName("Mr President " + result.getName());
    }

    // all this if block is ugly and have to be refactored
    if (result.getName().equals("Bill")) {
      if (result.getAge() > 65) {
        result.setName(result.getName() + " Sr.");
      }
      if (result.getAge() < 8) {
        result.setName(result.getName() + " Jr.");
      }
    }

    result.setActive(isHoursLeft(result.getCreated(),24));

    return result; // no validation for empty / null values
  }

  // this function could be oneliner
  boolean isHoursLeft(Date creationDate, int validHours) {
    Date currentDate = new Date();

    Instant instant1 = creationDate.toInstant();
    Instant calculatedInstant = instant1.plus(validHours, ChronoUnit.HOURS);

    Instant instant2 = currentDate.toInstant();
    if (calculatedInstant.isAfter(instant2)) {
      return true;
    } else {
      return false;
    }
  }

  @Override
  public List<User> getUsers() {
    // Should be done by sql, ORDER BY statement
    var users = userRepository.findAll();
    users.sort(Comparator.comparing(User::getName));
    return users;
  }

  @Override
  // Missing @Transactional annotation
  public User updateUser(Long id, UpdateUserRequest request) {
    var user =
        userRepository
            .findById(id)
            .orElseThrow(() -> new EntityNotFoundException("User not found"));
    user.setName(request.getName());
    userRepository.save(user);

    var history = new UserHistory();
    history.setUserActionType(UserHistory.UserActionType.NAME_CHANGE);
    history.setUser(user);
    userHistoryRepository.save(history);

    return user;
  }

  @Override
  public UserSettingsDTO getSettings(Long id) {
    // Should use CompletableFuture and run the api calls in parallel
    var resultA = apiCallA();
    var resultB = apiCallB();
    return UserSettingsDTO.builder().a(resultA).b(resultB).build();
  }

  private String apiCallA() {
    try {
      Thread.sleep(3000);
    } catch (InterruptedException e) {
      // Not handling errors
    }
    return "A";
  }

  private String apiCallB() {
    try {
      Thread.sleep(3000);
    } catch (InterruptedException e) {
      // Not handling errors
    }
    return "B";
  }
}
