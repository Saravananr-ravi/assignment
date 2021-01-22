package com.bank.employee;

import com.bank.employee.bean.Employee;
import com.bank.employee.manager.EmployeeServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.function.Consumer;

@SpringBootApplication
public class EmployeeManagementApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(EmployeeManagementApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        EmployeeServiceImpl employeeService = new EmployeeServiceImpl();
        Consumer<String> display=System.out::println;


        display.accept("**************** Print 1 to 100 ****************");
        employeeService.printValues(1,100);
        display.accept("****************");

        display.accept("**************** Get Top 5 employee based on joiningDate****************");
        employeeService.printTopFiveEmpDetails();
        display.accept("****************");


        display.accept("**************** Get employee based on joiningDateTime****************");
        employeeService.getEmployeeByJoiningDateTime();
        display.accept("****************");


        display.accept("**************** Categorize employee****************");
        employeeService.categorizeEmployee();
        display.accept("****************");

        display.accept("**************** Get employee> 7 years****************");
        List<Employee> candidateList=employeeService.getCandidateList();
        candidateList.forEach(System.out::println);
        display.accept("****************");

        display.accept("**************** Next week working days of an employees****************");
        employeeService.getWorkingDays();
        display.accept("****************");
    }
}
