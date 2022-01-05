package com.rkoyanagui.rest_api_demo.services;

import com.rkoyanagui.rest_api_demo.daos.UserRepository;
import com.rkoyanagui.rest_api_demo.entities.User;
import com.rkoyanagui.rest_api_demo.exceptions.ApiNotFoundException;
import com.rkoyanagui.rest_api_demo.models.ErrorResponseModel;
import com.rkoyanagui.rest_api_demo.models.UserModel;
import com.rkoyanagui.rest_api_demo.models.UserResponseModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService
{
  @Autowired
  UserRepository userRepository;

  public UserResponseModel createUser(UserModel userModel)
  {
    log.debug("POST /user: {}", userModel);

    User user = toUser(userModel);
    User savedUser = userRepository.save(user);

    UserResponseModel responseModel = new UserResponseModel();
    responseModel.setCreatedUserId(savedUser.getCode());

    return responseModel;
  }

  public UserModel readUser(Long id) throws ApiNotFoundException
  {
    return userRepository.findById(id)
        .map(user -> toUserModel(user))
        .orElseThrow(() -> new ApiNotFoundException(
            ErrorResponseModel.builder()
                .code("404")
                .message(String.format("User '%d' not found", id))
                .build()
        ));
  }

  User toUser(UserModel model)
  {
    User user = new User();
    user.setFirstName(model.getFirstName());
    user.setLastName(model.getLastName());
    return user;
  }

  UserModel toUserModel(User user)
  {
    UserModel userModel = new UserModel();
    userModel.setFirstName(user.getFirstName());
    userModel.setLastName(user.getLastName());
    return userModel;
  }
}
