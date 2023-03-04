package com.emsapp.data.service;

import com.emsapp.data.entity.Company;
import com.emsapp.data.entity.Employee;
import com.emsapp.data.entity.Status;
import com.emsapp.data.repository.CompanyRepository;
import com.emsapp.data.repository.ContactRepository;
import com.emsapp.data.repository.StatusRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CrmService {

    private final ContactRepository contactRepository;
    private final CompanyRepository companyRepository;
    private final StatusRepository statusRepository;

    public CrmService(ContactRepository contactRepository,
                      CompanyRepository companyRepository,
                      StatusRepository statusRepository) {
        this.contactRepository = contactRepository;
        this.companyRepository = companyRepository;
        this.statusRepository = statusRepository;
    }

    public List<Employee> findAllContacts(String stringFilter) {
        if (stringFilter == null || stringFilter.isEmpty()) {
            return contactRepository.findAll();
        } else {
            return contactRepository.search(stringFilter);
        }
    }

    public long countContacts() {
        return contactRepository.count();
    }

    public void deleteContact(Employee employee) {
        contactRepository.delete(employee);
    }

    public void saveContact(Employee employee) {
        if (employee == null) {
            System.err.println("Employee is null. Are you sure you have connected your form to the application?");
            return;
        }
        contactRepository.save(employee);
    }

    public List<Company> findAllCompanies() {
        return companyRepository.findAll();
    }

    public List<Status> findAllStatuses(){
        return statusRepository.findAll();
    }
}
