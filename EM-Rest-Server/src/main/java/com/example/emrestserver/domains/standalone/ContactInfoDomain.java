package com.example.emrestserver.domains.standalone;
import com.example.emrestserver.domains.standalone.AddressDomain;
import lombok.*;

import java.util.List;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContactInfoDomain {
    private String cellphone;
    private String alternatePhone;
    private String email;

    //List<AddressDomain> addressDaoList;
    AddressDomain[] addressDomains;

}
