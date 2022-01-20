package com.example.emrestserver.domains.standalone;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResidentialStatusDomain {
    private String isCitizenOrResident; //yes or no
    private String citizenOrGreenCard; //citizen    resident   ""

    private String workAuthorization; // visa status
    private String startDate;
    private String expirationDate;
//    private MultipartFile workAuthorizationFile;
}
