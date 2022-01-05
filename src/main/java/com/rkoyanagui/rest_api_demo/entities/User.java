package com.rkoyanagui.rest_api_demo.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "USERS")
@Data
public class User
{
  @Id
  @GeneratedValue
  Long code;

  String firstName;

  String lastName;
}
