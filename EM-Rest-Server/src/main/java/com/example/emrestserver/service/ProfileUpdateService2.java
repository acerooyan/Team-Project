package com.example.emrestserver.service;

import com.example.emrestserver.dao.EmployeeDao;
import com.example.emrestserver.dao.PersonDao2;
import com.example.emrestserver.domains.standalone.AddressDomain;
import com.example.emrestserver.entity.Address;
import com.example.emrestserver.entity.Employee;
import com.example.emrestserver.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
public class ProfileUpdateService2 {
    @Autowired
    PersonDao2 personDao2;

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


}
