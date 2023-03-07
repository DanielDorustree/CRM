package com.emsapp.data.service;

import com.emsapp.data.entity.Company;
import com.emsapp.data.entity.Employee;
import com.emsapp.data.entity.Status;
import com.emsapp.data.repository.CompanyRepository;
import com.emsapp.data.repository.EmployeeRepository;
import com.emsapp.data.repository.StatusRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpMngService {

    private final EmployeeRepository employeeRepository;
    private final CompanyRepository companyRepository;
    private final StatusRepository statusRepository;

    public EmpMngService(EmployeeRepository employeeRepository,
                         CompanyRepository companyRepository,
                         StatusRepository statusRepository) {
        this.employeeRepository = employeeRepository;
        this.companyRepository = companyRepository;
        this.statusRepository = statusRepository;
    }

    public List<Employee> findAllContacts(String stringFilter) {
        if (stringFilter == null || stringFilter.isEmpty()) {
            return employeeRepository.findAll();
        } else {
            return employeeRepository.search(stringFilter);
        }
    }

    public long countContacts() {
        return employeeRepository.count();
    }

    public void deleteContact(Employee employee) {
        employeeRepository.delete(employee);
    }

    public void saveContact(Employee employee) {
        if (employee == null) {
            System.err.println("Employee is null. Are you sure you have connected your form to the application?");
            return;
        }
        employeeRepository.save(employee);
    }

    public List<Company> findAllCompanies() {
        return companyRepository.findAll();
    }

    public List<Status> findAllStatuses(){
        return statusRepository.findAll();
    }
}
