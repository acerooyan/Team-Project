package com.example.emrestserver.domains.standalone;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class BasicInfoDomain {

    private String firstName;
    private String lastName;
    private String middleName;
    private String ssn;
    private String dob;
    private String gender;
    //private MultipartFile file;

}
