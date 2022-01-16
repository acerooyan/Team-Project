package com.example.auth.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDomain {
    private Integer id;
    private String username;
    private String password;
    private Integer admin;
    private String firstName;
    private String lastName;

}
