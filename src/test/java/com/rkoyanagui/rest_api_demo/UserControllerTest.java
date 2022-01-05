package com.rkoyanagui.rest_api_demo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.is;

import com.rkoyanagui.rest_api_demo.controllers.UserController;
import com.rkoyanagui.rest_api_demo.daos.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@WebMvcTest
@AutoConfigureMockMvc
public class UserControllerTest
{
  @MockBean
  private UserRepository userRepository;

  @Autowired
  UserController userController;

  @Autowired
  private MockMvc mockMvc;

  @Test
  public void controllerNotNull()
  {
    assertThat(userController).isNotNull();
  }

  @Test
  public void readUserNone() throws Exception
  {
    mockMvc.perform(MockMvcRequestBuilders.get("/three_o_clock_demo/user/1")
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isNotFound());
  }

  @Test
  public void createValidUser() throws Exception
  {
    String user = "{\"firstName\": \"Bob\", \"lastName\" : \"Martin\"}";
    mockMvc.perform(MockMvcRequestBuilders.post("/three_o_clock_demo/user")
            .content(user)
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isCreated());
  }

  @Test
  public void createInvalidUser() throws Exception
  {
    String user = "{\"firstName\": \"\", \"lastName\" : \"Martin\"}";
    mockMvc.perform(MockMvcRequestBuilders.post("/three_o_clock_demo/user")
            .content(user)
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isBadRequest())
        .andExpect(MockMvcResultMatchers.jsonPath("$.firstName", is("'firstName' is mandatory")));
  }
}
