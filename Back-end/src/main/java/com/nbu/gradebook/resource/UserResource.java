package com.nbu.gradebook.resource;

import com.nbu.gradebook.commons.utilities.Mapper;
import com.nbu.gradebook.dto.UserDTO;
import com.nbu.gradebook.model.User;
import com.nbu.gradebook.service.UserService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.nbu.gradebook.commons.paths.UserConstants.IS_ANONYMOUS;

@Controller
@RequestMapping("users")
public class UserResource {

    private final ModelMapper modelMapper;
    private final Mapper mapper;
    private final UserService userService;
    private final Logger logger = LoggerFactory.getLogger(UserResource.class);

    public UserResource(ModelMapper modelMapper, Mapper mapper, UserService userService) {
        this.modelMapper = modelMapper;
        this.mapper = mapper;
        this.userService = userService;
    }

}
