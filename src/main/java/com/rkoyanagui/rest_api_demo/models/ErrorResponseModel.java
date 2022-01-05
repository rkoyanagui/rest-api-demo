package com.rkoyanagui.rest_api_demo.models;

import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Singular;

@Builder
@Getter
public class ErrorResponseModel
{
  private String code;
  @Singular
  private List<String> messages;
}
