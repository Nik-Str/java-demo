package com.example.demo.util;

import com.example.demo.model.entity.User;
import java.util.Objects;
import java.util.Optional;

public class HelpersCopy {

  // Suggested
  public MotorcycleType getMotorcycleTypeForUser(User user) {
    if (!user.isActive() || user.getAge() < 15) {
      return MotorcycleType.None;
    }

    if (user.getAge() >= 24) {
      return MotorcycleType.Heavy;
    }

    if (user.getAge() >= 18) {
      return MotorcycleType.Middle;
    }

    return MotorcycleType.Light;
  }

  // Suggested
  // The bad one does not validate if node param is null and will throw
  // Each recursive call consumes stack space
  // Deep node chains → StackOverflowError
  public Optional<Node> findNodeById(Integer id, Node node) {
    var current = node;

    while (current != null) {
      if (Objects.equals(current.getId(), id)) {
        return Optional.of(current);
      }
      current = current.getNode();
    }

    return Optional.empty();
  }
}
