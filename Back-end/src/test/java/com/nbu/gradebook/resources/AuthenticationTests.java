package com.nbu.gradebook.resources;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.nbu.gradebook.ApplicationContext;
import com.nbu.gradebook.commons.paths.UserConstants;
import com.nbu.gradebook.dao.RoleDAO;
import com.nbu.gradebook.dto.JWTAuthResponse;
import com.nbu.gradebook.dto.SignInDTO;
import com.nbu.gradebook.model.Role;
import com.nbu.gradebook.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Collections;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class AuthenticationTests extends ApplicationContext {

    @Autowired
    private RoleDAO roleRepository;

    @BeforeEach
    public void setUp() {
        Role userRole = new Role("ROLE_USER");
        roleRepository.save(userRole);

        Role adminRole = new Role("ROLE_ADMIN");
        roleRepository.save(adminRole);
    }

    @Test
    public void registerUser() throws Exception {
        Role admin = roleRepository.findByAuthority("ROLE_ADMIN").get();

        User user = new User("test", "test", "Test", "Test", Collections.singleton(admin));

        mvc.perform(
                MockMvcRequestBuilders.post(UserConstants.AUTHENTICATION + UserConstants.SIGN_UP)
                        .content(objectMapper.writeValueAsString(user))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void login() throws Exception {
        registerUser();
        SignInDTO signInDTO = new SignInDTO("test", "test");

        mvc.perform(
                        MockMvcRequestBuilders.post(UserConstants.AUTHENTICATION + UserConstants.SIGN_IN)
                                .content(objectMapper.writeValueAsString(signInDTO))
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

//        The code below will be used when operations requiring a logged-in user are performed.

//        MvcResult result = mvc.perform(MockMvcRequestBuilders.post(UserConstants.AUTHENTICATION + UserConstants.SIGN_IN)
//                .content(objectMapper.writeValueAsString(signInDTO))
//                .contentType(MediaType.APPLICATION_JSON))
//                .andReturn();
//        JWTAuthResponse response = new JWTAuthResponse(result.getResponse().getContentAsString());
    }

}
