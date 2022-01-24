package com.example.emrestserver.service;

import com.example.emrestserver.dao.RegisterDao;
import com.example.emrestserver.domains.standalone.*;
import com.example.emrestserver.domains.combined.MergeDomain;
import com.example.emrestserver.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Date;
import java.time.LocalDate;

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
        *
        * 3. visaStatus(no employeeId needed)
        * 4. Employee (get personal ID and Visastatus Id house set to null)
        * 5. person(who is/are contact)
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
    public void addContactReference(ContactReferenceDomain contactReferenceDomain, Employee employee){
        Person contactPerson = Person.builder()
                .firstname(contactReferenceDomain.getFirstName())
                .lastname(contactReferenceDomain.getLastName())
                .middleName(contactReferenceDomain.getMiddleName())
                .cellPhone(contactReferenceDomain.getCellPhone())
                .email(contactReferenceDomain.getEmail())
                .build();
        Person newP = addPerson(contactPerson);
        Address address = Address.builder()
                .addressLine1(contactReferenceDomain.getAddressLine1())
                .addressLine2(contactReferenceDomain.getAddressLine2())
                .city(contactReferenceDomain.getCity())
                .stateAbbr(contactReferenceDomain.getState())
                .stateName(contactReferenceDomain.getState())
                .zipcode(Integer.valueOf(contactReferenceDomain.getZipcode()))
                .person(newP)
                .build();
        registerDao.addAddress(address);
        // todo: need employee
        Contact contact = Contact.builder()
                .employee(employee)
                .person(newP)
                .relationShip(contactReferenceDomain.getRelationship())
                .isReference((byte)1)
                .isEmergency((byte)0)
                .isLandlord((byte)0)
                .build();
        registerDao.addContact(contact);
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

    @Transactional
    public Employee addEmployee(Person p, VisaStatus visaStatus, ResidentialStatusDomain residentialStatusDomain, CarInfoDomain carInfoDomain, String avatarName){
        LocalDate yearAfter = LocalDate.now().plusYears(1);
        Employee employee = Employee.builder()
                .person(p)
                .startDate(new Date(System.currentTimeMillis()))
                .endDate(Date.valueOf(yearAfter))
                .visaStatus(visaStatus)
                .title("employee")
                .build();

        // 判断 car
        StringBuilder sb = new StringBuilder();
        if( carInfoDomain.getDriverLicence()!= null){
            employee.setDriverLicence(carInfoDomain.getDriverLicence());
            employee.setDriverLicence_ExpirationDate(Date.valueOf(carInfoDomain.getDriverLicence_expirationDate()));
            if (carInfoDomain.getColor()!=null || !carInfoDomain.getColor().equals("")){
                sb.append(carInfoDomain.getColor());
                sb.append(" ");
            }
            if(carInfoDomain.getMake() != null){
                sb.append(carInfoDomain.getMake());
                sb.append(" ");
            }
            if (carInfoDomain.getModel()!= null){
                sb.append(carInfoDomain.getModel());
            }
        }
        String car = sb.toString();
        if (!car.equals("")){
            employee.setCar(car);
        }

        if(!residentialStatusDomain.getIsCitizenOrResident().equalsIgnoreCase("yes")){
            // visa status start date
            employee.setVisaStartDate(Date.valueOf(residentialStatusDomain.getStartDate()));
            //visa status end date
            employee.setVisaEndDate(Date.valueOf(residentialStatusDomain.getExpirationDate()));
        }

        // avatar

        employee.setAvatar(avatarName);

        System.out.println(employee);
        return registerDao.addEmployee(employee);
    }

    @Transactional
    public Contact addContactEmergency(ContactEmergencyDomain contactEmergencyDomain, Employee employee){
        Person p = Person.builder()
                .firstname(contactEmergencyDomain.getFirstName())
                .lastname(contactEmergencyDomain.getLastName())
                .middleName(contactEmergencyDomain.getMiddleName())
                .email(contactEmergencyDomain.getEmail())
                .cellPhone(contactEmergencyDomain.getCellPhone())
                .build();
        Person newP = registerDao.addPerson(p);
        Contact contact = Contact.builder()
                .isEmergency((byte)1)
                .isLandlord((byte)0)
                .isReference((byte)0)
                .relationShip(contactEmergencyDomain.getRelationship())
                .employee(employee)
                .person(newP)
                .build();
        return registerDao.addContact(contact);
    }

    @Transactional
    public ApplicationWorkFlow addApplicationWorkFlow(Employee employee){
        ApplicationWorkFlow applicationWorkFlow = ApplicationWorkFlow
                .builder()
                .employee(employee)
                .createdDate(new Date(System.currentTimeMillis()))
                .modificationDate(new Date(System.currentTimeMillis()))
                .status("pending")
                .type("onBoarding")
                .build();
        return registerDao.addApplicationWorkFlow(applicationWorkFlow);
    }
}
