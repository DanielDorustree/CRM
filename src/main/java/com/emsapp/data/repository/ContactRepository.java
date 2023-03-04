package com.emsapp.data.repository;

import com.emsapp.data.entity.Employee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ContactRepository extends JpaRepository<Employee, Integer> {

    @Query("select e from Employee e " +
        "where lower(e.firstName) like lower(concat('%', :searchTerm, '%')) " +
        "or lower(e.lastName) like lower(concat('%', :searchTerm, '%'))")
    List<Employee> search(@Param("searchTerm") String searchTerm);
}
