package com.example.emrestserver.domain;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;


import java.sql.Date;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class BasicInfoDomain {

    private String firstName;

    private String lastName;

    private String middleName;

    private String SSN;

    private Date DOB;

    private String gender;

    private MultipartFile file;

}
