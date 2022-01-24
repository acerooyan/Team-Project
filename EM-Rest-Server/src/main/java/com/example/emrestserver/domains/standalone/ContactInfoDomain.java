package com.example.emrestserver.domains.standalone;
import com.example.emrestserver.domains.standalone.AddressDomain;
import com.google.gson.annotations.SerializedName;
import lombok.*;

import java.util.List;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContactInfoDomain {
    @SerializedName("cellphone")
    private String cellPhone;
    private String alternatePhone;
    private String email;

    @SerializedName("addressDaoList")
    AddressDomain[] addressDomains;

}
