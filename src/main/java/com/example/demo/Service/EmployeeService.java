package com.example.demo.Service;

import com.example.demo.Domain.Employee;
import com.example.demo.Repository.RepositoryEmployee;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    private final RepositoryEmployee repositoryEmployee;


    public EmployeeService(RepositoryEmployee repositoryEmployee) {
        this.repositoryEmployee = repositoryEmployee;
    }

    public Employee save(Employee employee) {
        return repositoryEmployee.save(employee);
    }

    public List<Employee> list() {
        return repositoryEmployee.findAll();
    }

    public Employee findByID(long id) {
        return repositoryEmployee.findById(id).get();
    }

    public List<Employee> findByName(String name) {
        return repositoryEmployee.findByNameQueryNative(name);
    }

    public List<Employee> findAllByParam(String name) {
        return repositoryEmployee.findAllByLike(name);
    }

    public void delate(Long id) {
        Employee employee = repositoryEmployee.getReferenceById(id);
        repositoryEmployee.delete(employee);
    }

    @Scheduled(cron = "0 6 13 * * *")
    public Employee saveScheduled() {
        Employee employee1 = new Employee();
        employee1.setName("Sherzod");
        employee1.setLastname("Toshboyev");
        return repositoryEmployee.save(employee1);
    }

}
