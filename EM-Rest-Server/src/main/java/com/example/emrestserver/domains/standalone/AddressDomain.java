package com.example.emrestserver.domains.standalone;

import com.google.gson.annotations.SerializedName;
import lombok.*;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressDomain {
    private String addressLine1;
    private String addressLine2;
    private String city;

    @SerializedName("status")
    private String state;
    @SerializedName("Zipcode")
    private String zipcode;
}
