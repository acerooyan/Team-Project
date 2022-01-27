package com.example.emrestserver.service;

import com.example.emrestserver.dao.EmployeeDao;
import com.example.emrestserver.domains.profile.*;
import com.example.emrestserver.domains.standalone.AddressDomain;
import com.example.emrestserver.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;

@Service
public class EmployeeService {
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
        return employeeDao.getEmergencyByEmployeeId(id);
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

        //AvatarDomain ready
        AvatarDomain avatarDomain = AvatarDomain.builder().avatar(employee.getAvatar()).build();
        // PersonInfoDomain ready
        PersonalInfoDomain personalInfoDomain = PersonalInfoDomain
                .builder().dob(person.getDob().toString()).ssn(person.getSsn())
                .age(age).gender(person.getGender()).build();
        StringBuilder sb = new StringBuilder();

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
//                .workAuthorizationStartDate(employee.getStartDate().toString())
//                .workAuthorizationEndDate(employee.getEndDate().toString())
                .title(employee.getTitle())
                .build();

        if(employee.getVisaStatus().getVisaType().equalsIgnoreCase("greenCard")||
                employee.getVisaStatus().getVisaType().equalsIgnoreCase("citizen")){
            employmentDomain.setWorkAuthorizationStartDate("");
            employmentDomain.setWorkAuthorizationEndDate("");
        }else{
            employmentDomain.setWorkAuthorizationStartDate(employee.getStartDate().toString());
            employmentDomain.setWorkAuthorizationEndDate(employee.getEndDate().toString());
        }

        Address[] addresses = getAddressByPersonId(person.getId());

        //AddressDomain ready
        AddressDomain[] addressDomains = new AddressDomain[addresses.length];
        for(int i = 0; i < addressDomains.length;i++){
            addressDomains[i] = addressToAddressDomain(addresses[i]);
        }

        //EmergencyContactDomain ready
        Contact contact = employeeDao.getEmergencyByEmployeeId(employee.getId());
        Person contactPerson = contact.getPerson();
        StringBuilder sb1 = new StringBuilder();
        sb1.append(contactPerson.getFirstname());
        sb1.append(" ");
        if(contactPerson.getMiddleName()!= null){
            sb1.append(contactPerson.getMiddleName());
            sb1.append(" ");
        }
        sb1.append(contactPerson.getLastname());
        String contactFullName = sb1.toString();
        EmergencyContactDomain emergencyContactDomain = EmergencyContactDomain.builder()
                .fullName(contactFullName)
                .relationship(contact.getRelationShip())
                .cellPhone(contactPerson.getCellPhone())
                .email(contactPerson.getEmail())
                .build();


        // contactInfo ready
        ContactInfoDomain contactInfoDomain = ContactInfoDomain.builder()
                .cellPhone(person.getCellPhone())
                .alternatePhone(person.getAlternatePhone())
                .email(person.getEmail())
                .build();


        PersonalDocument[] personalDocuments = getDocumentByEmployeeId(employee.getId());
        String[] documentArr = new String[personalDocuments.length];
        for(int i  = 0; i < documentArr.length; i++){
            documentArr[i] = personalDocuments[i].getPath();
        }
        DocumentDomain documentDomain = DocumentDomain.builder()
                .fileName(documentArr).build();

        // finally, create profile Domain
        ProfileDomain profileDomain = ProfileDomain.builder()
                .personalInfoDomain(personalInfoDomain)
                .addressDomains(addressDomains)
                .employmentDomain(employmentDomain)
                .emergencyContactDomain(emergencyContactDomain)
                .contactInfoDomain(contactInfoDomain)
                .documentDomain(documentDomain)
                .avatarDomain(avatarDomain)
                .build();
//        System.out.println(profileDomain);

        return profileDomain;
    }

    @Transactional
    public Employee updateEmployee(Employee employee){
        return employeeDao.updateEmployee(employee);
    }

    @Transactional
    public VisaStatus updateVisaStatus(String email,String visaType){
        return employeeDao.updateVisaStatus(email,visaType);
    }

    @Transactional
    public ApplicationWorkFlow updateWorkFlowByType(String type, String email, String comments, String status){
        return employeeDao.updateWorkFlowByType(type, email,comments,status);
    }




}
