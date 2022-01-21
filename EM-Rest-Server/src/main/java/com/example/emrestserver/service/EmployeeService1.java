package com.example.emrestserver.service;

import com.example.emrestserver.dao.EmployeeDao;
import com.example.emrestserver.dao.EmployeeDao2;
import com.example.emrestserver.domains.profile.EmergencyContactDomain;
import com.example.emrestserver.domains.profile.EmploymentDomain;
import com.example.emrestserver.domains.profile.PersonalInfoDomain;
import com.example.emrestserver.domains.profile.ProfileDomain;
import com.example.emrestserver.domains.standalone.AddressDomain;
import com.example.emrestserver.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;

public class EmployeeService1 {
    @Autowired
    EmployeeDao employeeDao;

    @Transactional
    public Employee getEmpolyeeByEmail(String email){
        return employeeDao.getEmployeeByEmail(email);
    }

    @Transactional
    public Address[] getAddressByPersonId(Integer personId){
        return employeeDao.getAddressByPersonId(personId);
    }

    @Transactional
    public Contact getEmergencyByEmployeeId(Integer id){
        return getEmergencyByEmployeeId(id);
    }

    @Transactional
    public PersonalDocument[] getDocumentByEmployeeId(Integer id){
        return employeeDao.getDocumentByEmployeeId(id);
    }

    public AddressDomain addressToAddressDomain(Address address){
        AddressDomain addressDomain = AddressDomain.builder()
                .addressLine1(address.getAddressLine1())
                .addressLine2(address.getAddressLine2())
                .state(address.getStateName())
                .city(address.getCity())
                .zipcode(address.getZipcode().toString())
                .build();
        return addressDomain;
    }

    @Transactional
    public ProfileDomain getDataReady(String email){
        Employee employee = getEmpolyeeByEmail(email);

        Person person = employee.getPerson();
        Date dob = person.getDob();
        LocalDate birthdayDate =dob.toLocalDate();
        Integer age = Period.between(birthdayDate, LocalDate.now()).getYears();

        // PersonInfoDomain ready
        PersonalInfoDomain personalInfoDomain = PersonalInfoDomain
                .builder().dob(person.getDob().toString()).ssn(person.getSsn())
                .age(age).gender(person.getGender()).build();
        StringBuilder sb = new StringBuilder();
        // full name
        sb.append(person.getFirstname());
        sb.append(" ");
        if(person.getMiddleName()!= null){
            sb.append(person.getMiddleName());
            sb.append(" ");
        }
        sb.append(person.getLastname());
        personalInfoDomain.setFullName(sb.toString());

        //EmployeeDomain ready
        EmploymentDomain employmentDomain = EmploymentDomain.builder()
                .workAuthorization(employee.getVisaStatus().getVisaType())
                .workAuthorizationStartDate(employee.getStartDate().toString())
                .workAuthorizationEndDate(employee.getEndDate().toString())
                .title(employee.getTitle())
                .build();

        Address[] addresses = getAddressByPersonId(person.getId());

        //AddressDomain ready
        AddressDomain[] addressDomains = new AddressDomain[addresses.length];
        for(int i = 0; i < addressDomains.length;i++){
            addressDomains[i] = addressToAddressDomain(addresses[i]);
        }

        //EmergencyContactDomain ready
        Contact contact = employeeDao.getEmergencyByEmployeeId(employee.getId());
//        EmergencyContactDomain emergencyContactDomain =




    }
}
