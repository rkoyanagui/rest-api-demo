package com.rkoyanagui.rest_api_demo.exceptions;

import com.rkoyanagui.rest_api_demo.models.ErrorResponseModel;
import lombok.Getter;

@Getter
public class ApiNotFoundException extends Exception
{
  protected final transient ErrorResponseModel error;

  public ApiNotFoundException()
  {
    this.error = null;
  }

  public ApiNotFoundException(String message)
  {
    super(message);
    this.error = null;
  }

  public ApiNotFoundException(String message, Throwable cause)
  {
    super(message, cause);
    this.error = null;
  }

  public ApiNotFoundException(Throwable cause)
  {
    super(cause);
    this.error = null;
  }

  public ApiNotFoundException(String message,
                              Throwable cause,
                              boolean enableSuppression,
                              boolean writableStackTrace)
  {
    super(message, cause, enableSuppression, writableStackTrace);
    this.error = null;
  }

  public ApiNotFoundException(ErrorResponseModel error)
  {
    this.error = error;
  }

  private static final long serialVersionUID = -7662964594685315021L;
}
