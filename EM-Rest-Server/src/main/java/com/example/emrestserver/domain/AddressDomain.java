package com.example.emrestserver.domain;

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
    private String state;
    private String Zipcode;
}
