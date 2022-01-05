package com.rkoyanagui.rest_api_demo.controllers;

import com.rkoyanagui.rest_api_demo.exceptions.ApiNotFoundException;
import com.rkoyanagui.rest_api_demo.models.UserModel;
import com.rkoyanagui.rest_api_demo.models.UserResponseModel;
import com.rkoyanagui.rest_api_demo.services.UserService;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest_api_demo")
public class UserController
{
  @Autowired
  UserService userService;

  @GetMapping(path = "/hello")
  public String hello()
  {
    return "Hello, World!";
  }

  @GetMapping(path = "/echo/{msg}")
  public String echo(@PathVariable String msg)
  {
    return msg;
  }

  @PostMapping(path = "/user")
  public ResponseEntity<UserResponseModel> createUser(@RequestBody @Valid UserModel user)
  {
    return ResponseEntity.status(201).body(userService.createUser(user));
  }

  @GetMapping(path = "/user/{id}")
  public ResponseEntity<UserModel> readUser(
      @PathVariable
      @NotNull(message = "'id' must not be null")
      @Positive(message = "'id' must be a positive number") Long id) throws ApiNotFoundException
  {
    UserModel user = userService.readUser(id);
    return ResponseEntity.ok().body(user);
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException x)
  {
    Map<String, String> errors = new HashMap<>();

    x.getBindingResult().getAllErrors().forEach(error ->
    {
      String fieldName = ((FieldError) error).getField();
      String errorMessage = error.getDefaultMessage();
      errors.put(fieldName, errorMessage);
    });

    return errors;
  }

  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ExceptionHandler(ApiNotFoundException.class)
  public Map<String, Object> handleNotFoundException(ApiNotFoundException x)
  {
    return Optional.ofNullable(x.getError()).map(error ->
    {
      Map<String, Object> errors = new HashMap<>();
      errors.put("code", error.getCode());
      errors.put("messages", error.getMessages());
      return errors;
    }).orElseGet(() -> new HashMap<>());
  }
}
