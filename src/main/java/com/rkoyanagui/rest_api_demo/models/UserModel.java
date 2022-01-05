package com.rkoyanagui.rest_api_demo.models;

import javax.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserModel
{
  @NotBlank(message = "'firstName' is mandatory")
  String firstName;

  @NotBlank(message = "'lastName' is mandatory")
  String lastName;
}
