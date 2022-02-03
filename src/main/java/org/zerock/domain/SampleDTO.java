package org.zerock.domain;

import lombok.Data;

@Data
public class SampleDTO { //Data Transfer Object

  private String name;
  private int age;

  public void setAge(int age) {
    this.age = age;
  }

  public void setName(String name) {
    this.name = name;
  }

  // getter, setter, equals(), toString(), »ý¼ºÀÚ
}
