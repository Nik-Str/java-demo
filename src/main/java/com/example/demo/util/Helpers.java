package com.example.demo.util;

import com.example.demo.model.entity.User;

public class Helpers {

  public MotorcycleType getMotorcycleTypeForUser(User user) {
    if (user.isActive()) {
      if (user.getAge() >= 15) {
        if (user.getAge() >= 18) {
          if (user.getAge() >= 24) {
            return MotorcycleType.Heavy;
          } else {
            return MotorcycleType.Middle;
          }
        } else {
          return MotorcycleType.Light;
        }
      } else {
        return MotorcycleType.None;
      }
    } else {
      return MotorcycleType.None;
    }
  }

  public Node findNodeById(Integer id, Node node) {
    var result = node;
    if (!node.getId().equals(id)) {
      result = findNodeById(id, node.getNode());
    }
    return result;
  }
  // This method behaves correctly only if ALL of the following are true:
  // - node is never null
  // - node.getNode() is never null
  // - The structure is finite
  // - The structure is acyclic
  // - A node with the given id always exists
  // - If any one of these assumptions is violated → ❌ runtime failure.
}
