package com.example.emrestserver.domains.standalone;
import com.google.gson.annotations.SerializedName;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class BasicInfoDomain {

    private String firstName;
    private String lastName;
    private String middleName;

    @SerializedName("SSN")
    private String ssn;

    @SerializedName("DOB")
    private String dob;

    private String gender;
    //private MultipartFile file;

}
