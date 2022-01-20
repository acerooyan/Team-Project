package com.example.emrestserver.service;

import com.example.emrestserver.dao.RegisterDao;
import com.example.emrestserver.domains.standalone.*;
import com.example.emrestserver.domains.combined.MergeDomain;
import com.example.emrestserver.entity.Address;
import com.example.emrestserver.entity.Person;
import com.example.emrestserver.entity.VisaStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;

@Service("registerService")
public class RegisterService {


    @Autowired
    RegisterDao registerDao;
    @Transactional
    public void mainService(MergeDomain mergeDomain){
        // basicInfoDomain contains:
        BasicInfoDomain basicInfoDomain = mergeDomain.getBasicInfoDomain();
        ContactInfoDomain contactInfoDomain = mergeDomain.getContactInfoDomain();

        //transfer domain to entity with empty id, then return the id to save the rest of the entity
        /* order:
        * 1. Person(who is employee)
        * 2. Addresses
        * 3. person(who is/are contact)
        * 4. visaStatus(no employeeId needed)
        * 5. Employee (get personal ID and Visastatus Id house set to null)
        * 6. PersonalDocument (employeeId needed)
        * */
    }

    @Transactional
    public Person convertBasicInfoToPerson(BasicInfoDomain basicInfoDomain, ContactInfoDomain contactInfoDomain){

        Person p = Person.builder().firstname(basicInfoDomain.getFirstName())
                .lastname(basicInfoDomain.getLastName())
                .middleName(basicInfoDomain.getMiddleName())
                .email(contactInfoDomain.getEmail())
                .cellPhone(contactInfoDomain.getCellPhone())
                .alternatePhone(contactInfoDomain.getAlternatePhone())
                .ssn(basicInfoDomain.getSsn())
                .dob(Date.valueOf(basicInfoDomain.getDob()))
                .userId(12345)
                .gender(basicInfoDomain.getGender())
                .build();
        Person newP = addPerson(p);
        System.out.println("print from service"+newP.getId());
        return newP;
    }
    @Transactional
    public Person addPerson(Person person){
        return registerDao.addPerson(person);
    }

    @Transactional
    public void addAddress(Person p, AddressDomain[] addressDomains){
        Address address;
        for(int i = 0; i < addressDomains.length; i++){
            address = Address.builder()
                    .person(p)
                    .addressLine1(addressDomains[i].getAddressLine1())
                    .addressLine2(addressDomains[i].getAddressLine2())
                    .city(addressDomains[i].getCity())
                    .stateAbbr(addressDomains[i].getState())
                    .stateName(addressDomains[i].getState())
                    .zipcode(Integer.parseInt(addressDomains[i].getZipcode()))
                    .build();
            registerDao.addAddress(address);

        }
    }


    @Transactional
    public void addContactReference(ContactReferenceDomain contactReferenceDomain){
        Person contactPerson = Person.builder()
                .firstname(contactReferenceDomain.getFirstName())
                .lastname(contactReferenceDomain.getLastName())
                .middleName(contactReferenceDomain.getMiddleName())
                .cellPhone(contactReferenceDomain.getCellPhone())
                .email(contactReferenceDomain.getEmail())
                .build();
        Person newP = addPerson(contactPerson);
        // todo: need employee
    }

    @Transactional
    public VisaStatus addVisaStatus(ResidentialStatusDomain residentialStatusDomain){
        // 判断多种 身份
        // 身份种类：Citizen, Resident, H1B, OPT EAD, OPT STEM
        VisaStatus visaStatus = new VisaStatus();
        if(residentialStatusDomain.getIsCitizenOrResident().equalsIgnoreCase("yes")){
            if(residentialStatusDomain.getCitizenOrGreenCard().equalsIgnoreCase("citizen")){
                visaStatus.setVisaType("Citizen");
            }else if(residentialStatusDomain.getCitizenOrGreenCard().equalsIgnoreCase("greenCard")){

                visaStatus.setVisaType("GreenCard");
            }
        }else{
            visaStatus.setVisaType(residentialStatusDomain.getWorkAuthorization());
            visaStatus.setActive("0");
            visaStatus.setModificationDate(new Date(System.currentTimeMillis()));
        }

        VisaStatus visaStatus1 = registerDao.addVisaStatus(visaStatus);
        return visaStatus1;

    }
}
