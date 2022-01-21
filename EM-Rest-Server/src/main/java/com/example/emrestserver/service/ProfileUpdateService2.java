package com.example.emrestserver.service;

import com.example.emrestserver.dao.EmployeeDao;
import com.example.emrestserver.dao.PersonDao;
import com.example.emrestserver.dao.PersonDao2;
import com.example.emrestserver.domains.profile.EmergencyContactDomain;
import com.example.emrestserver.domains.profile.EmploymentDomain;
import com.example.emrestserver.domains.standalone.AddressDomain;
import com.example.emrestserver.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;

@Service
public class ProfileUpdateService2 {
    @Autowired
    PersonDao2 personDao2;

    @Autowired
    PersonDao personDao;

    @Autowired
    UtilService utilService;

    @Autowired
    EmployeeDao employeeDao;

    // addressDomain[] -> email 去找address List 然后for loop 吧address domain的信息填进去
    @Transactional
    public void changeAddress(AddressDomain[] addressDomains, String email){
        //Employee first
        Employee employee = utilService.getEmployeeByEmail(email);
        Person person  = employee.getPerson();
        // find address list with personId;
        Address[] addresses=employeeDao.getAddressByPersonId(person.getId());
        for(int i = 0; i < addresses.length; i++){
            addresses[i].setAddressLine1(addressDomains[i].getAddressLine1());
            addresses[i].setAddressLine2(addressDomains[i].getAddressLine2());
            addresses[i].setCity(addressDomains[i].getCity());
            addresses[i].setStateName(addressDomains[i].getState());
            addresses[i].setStateAbbr(addressDomains[i].getState());
            addresses[i].setZipcode(Integer.valueOf(addressDomains[i].getZipcode()));
            personDao2.updateAddress(addresses[i]);
        }
    }

    @Transactional
    public void changeEmergencyContact(EmergencyContactDomain emergencyContactDomain, String email){
        Employee employee = utilService.getEmployeeByEmail(email);
        Person person = employee.getPerson();
        Contact contact = employeeDao.getEmergencyByEmployeeId(employee.getId());
        Person contactPerson = contact.getPerson();
        // update contact
        contact.setRelationShip(emergencyContactDomain.getRelationship());
        personDao2.updateContact(contact);
        //update person
        String fullName = emergencyContactDomain.getFullName();
        String[] split = fullName.split("\\s+");
        person.setFirstname(split[0]);
        if(split.length == 2){
            person.setLastname(split[1]);
        }else{
            person.setMiddleName(split[1]);
            person.setFirstname(split[2]);
        }
        person.setCellPhone(emergencyContactDomain.getCellPhone());

        personDao2.updatePerson(person);
    }

    @Transactional
    public void changeEmployee(EmploymentDomain employmentDomain,String email){

        Employee employee = employeeDao.getEmployeeByEmail(email);
        VisaStatus visaStatus = employee.getVisaStatus();

        //update visaStatus
        visaStatus.setVisaType(employmentDomain.getWorkAuthorization());
        visaStatus.setModificationDate(new Date(System.currentTimeMillis()));
        personDao2.updateVisaStatus(visaStatus);

        //update employee
        employee.setVisaStartDate(Date.valueOf(employmentDomain.getWorkAuthorizationStartDate()));
        employee.setVisaEndDate(Date.valueOf(employmentDomain.getWorkAuthorizationEndDate()));
        personDao2.updateEmployee(employee);

    }
}
