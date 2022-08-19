package com.nbu.gradebook.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignInDTO {
    private String username;
    private String password;

//    public SignInDTO() {
//
//    }

//    public SignInDTO(String username, String password) {
//        this.username = username;
//        this.password = password;
//    }
}
