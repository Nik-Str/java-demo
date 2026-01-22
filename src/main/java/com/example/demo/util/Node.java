package com.example.demo.util;

import lombok.Data;

@Data
public class Node {

  private Node node;

  private String data;

  private Integer id;
}
