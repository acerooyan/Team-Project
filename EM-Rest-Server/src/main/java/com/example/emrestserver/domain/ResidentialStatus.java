package com.example.emrestserver.domain;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Date;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResidentialStatus {
    private String citizenOrResident;
    private String workAuthorization; // visa status
    private String startDate;
    private MultipartFile workAuthorizationFile;
}
