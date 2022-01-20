package com.example.emrestserver.service;

import com.example.emrestserver.dao.EmployeeDao;
import com.example.emrestserver.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("utilService")
public class UtilService {
    @Autowired
    private EmployeeDao employeeDao;

    @Transactional
    public Employee getEmployeeById(Integer id){
        return employeeDao.getEmployeeById(id);
    }
}
