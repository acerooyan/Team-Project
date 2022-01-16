package com.example.auth.domain;

import lombok.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDomain {


    @NotNull
    private String userName;
    @NotNull
    private String password;
    @NotNull
    private String email;


}
