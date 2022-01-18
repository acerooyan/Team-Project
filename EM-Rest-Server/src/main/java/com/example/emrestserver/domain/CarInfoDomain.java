package com.example.emrestserver.domain;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Date;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CarInfoDomain {

    private String driverLicence;

    private Date driverLicence_expirationDate;

    private MultipartFile driverLicenceFile;

    private String make;

    private String model;

    private String color;

}
