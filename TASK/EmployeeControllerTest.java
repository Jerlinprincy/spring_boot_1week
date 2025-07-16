package com.example.springbootfirst.controller;

import com.example.springbootfirst.controllers.EmployeeController;
import com.example.springbootfirst.models.RegisterDetails;
import com.example.springbootfirst.models.UserDetailsDto;
import com.example.springbootfirst.services.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class EmployeeControllerTest {

    @Mock
    EmployeeService employeeService;

    @InjectMocks
    EmployeeController employeeController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testRoute(){
        String result = employeeController.route();
        assertEquals("hello",result);
    }

    @Test
    void testGetAllEmployees(){
        RegisterDetails emp1 =  new RegisterDetails();
        RegisterDetails emp2 =  new RegisterDetails();
        when(employeeService.getAllEmployees()).thenReturn(Arrays.asList(emp1,emp2));
        List<RegisterDetails> result = employeeController.getEmployees();
        assertEquals(2,result.size());
    }

    @Test
    void testGetByIdMethod(){
        int empId = 1;
        RegisterDetails emp1 = new  RegisterDetails();
        emp1.setEmpId(empId);
        when(employeeService.getEmployeeById(empId)).thenReturn(emp1);
        RegisterDetails result = employeeController.getEmployeeById(empId);
        assertEquals(empId,result.getEmpId());
    }

    @Test
    void testAddNewEmployee(){
        UserDetailsDto user =  new UserDetailsDto();
        user.setUserName("Ramya");
        user.setEmail("ramya@gmail.com");
        user.setPassword("ramya123");
        String expectedMessage = "Employee added successfully";
        when(employeeService.addEmployee(user)).thenReturn(expectedMessage);
        String result = employeeController.addEmployee(user);
        assertEquals(expectedMessage,result);
    }

    @Test
    void testUpdateEmployee() {
        int empId = 1;
        UserDetailsDto dto = new UserDetailsDto();
        dto.setName("ramya");
        dto.setEmail("ramya@gmail.com");
        dto.setUserName("ramya");
        dto.setPassword("ramya123");
        dto.setRoleNames(Set.of("USER"));

        when(employeeService.updateEmployeeById(empId, dto)).thenReturn("Employee updated successfully");

        String result = employeeController.updateEmployee(empId, dto);
        assertEquals("Employee updated successfully", result);
    }

}
