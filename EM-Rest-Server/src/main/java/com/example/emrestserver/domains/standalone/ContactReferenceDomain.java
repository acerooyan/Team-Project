package com.example.emrestserver.domains.standalone;

import lombok.*;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContactReferenceDomain {

    private String firstName;
    private String lastName;
    private String middleName;
    private String cellPhone;
    private String email;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private Integer zipcode;
    private String state;
    private String relationship;

}
