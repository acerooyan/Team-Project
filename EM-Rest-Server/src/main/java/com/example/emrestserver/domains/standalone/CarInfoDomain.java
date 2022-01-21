package com.example.emrestserver.domains.standalone;

import com.google.gson.annotations.SerializedName;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CarInfoDomain {
    @SerializedName("driverlicense")
    private String driverLicence;
    private String driverLicence_expirationDate;

    private String make;
    private String model;
    private String color;

}
