package com.example.emrestserver.domains.combined;

import com.example.emrestserver.domains.standalone.*;
import com.google.gson.annotations.SerializedName;
import lombok.*;

import java.util.List;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TestDomain {

    @SerializedName("basicInfo")
    BasicInfoDomain basicInfoDomain;

    @SerializedName("contactInfo")
    ContactInfoDomain contactInfoDomain;

    @SerializedName("contactReference")
    ContactReferenceDomain contactReferenceDomain;

    @SerializedName("contactEmergency")
    ContactEmergencyDomain contactEmergencyDomain;

    @SerializedName("residentialStatus")
    ResidentialStatusDomain residentialStatusDomain;

    @SerializedName("carInfo")
    CarInfoDomain carInfoDomain;
}
