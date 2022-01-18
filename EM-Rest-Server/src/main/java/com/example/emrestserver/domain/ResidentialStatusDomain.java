package com.example.emrestserver.domain;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResidentialStatusDomain {
    private String citizenOrResident;
    private String workAuthorization; // visa status
    private String startDate;
    private MultipartFile workAuthorizationFile;
}
