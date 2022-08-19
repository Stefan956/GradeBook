package com.nbu.gradebook.service;

import com.nbu.gradebook.model.BaseUser;
import org.springframework.stereotype.Service;

@Service
public interface BaseUserService {

    BaseUser findUserByUsername(String username);

}
