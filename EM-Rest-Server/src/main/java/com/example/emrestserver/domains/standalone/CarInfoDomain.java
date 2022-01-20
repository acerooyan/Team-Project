package com.example.emrestserver.domains.standalone;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CarInfoDomain {

    private String driverLicence;

    private String driverLicence_expirationDate;

//    private MultipartFile driverLicenceFile;

    private String make;

    private String model;

    private String color;

}
