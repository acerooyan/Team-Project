package com.example.emrestserver.service;

import com.example.emrestserver.dao.HrHomeDao;
import com.example.emrestserver.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("hrHomeService")
public class HrHomeService {
    @Autowired
    private HrHomeDao hrHomeDao;

    @Transactional
    public List<Employee> getFetchedAllEmployee(){
        System.out.println("service" + hrHomeDao.getEmployeeListWithVisaStatusActive().size());
        return hrHomeDao.getEmployeeListWithVisaStatusActive();
    }
}
