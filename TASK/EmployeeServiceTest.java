package com.example.springbootfirst.sevices;

import com.example.springbootfirst.models.RegisterDetails;
import com.example.springbootfirst.models.Roles;
import com.example.springbootfirst.models.UserDetailsDto;
import com.example.springbootfirst.repository.RegisterDetailsRepository;
import com.example.springbootfirst.repository.RoleRepository;
import com.example.springbootfirst.services.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class EmployeeServiceTest {
    @Mock
    RegisterDetailsRepository registerDetailsRepository;

    @Mock
    RoleRepository roleRepository;

    @Mock
    PasswordEncoder passwordEncoder;

    @InjectMocks
    EmployeeService employeeService;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetMethod(){
        RegisterDetails emp1 = new RegisterDetails();
        RegisterDetails emp2 = new RegisterDetails();
        when(registerDetailsRepository.findAll()).thenReturn(Arrays.asList(emp1,emp2));
        List<RegisterDetails> result = employeeService.getAllEmployees();
        assertEquals(2,result.size());
    }

    @Test
    void testGetById(){
        RegisterDetails emp = new RegisterDetails();
        emp.setEmpId(1);
        emp.setName("Ramya");
        when(registerDetailsRepository.findById(1)).thenReturn(Optional.of(emp));
        RegisterDetails result = employeeService.getEmployeeById(1);
        assertEquals("Ramya", result.getName());
        assertEquals(1, result.getEmpId());
    }

    @Test
    void testAddEmployee() {
        UserDetailsDto dto = new UserDetailsDto();
        dto.setEmpId(1);
        dto.setName("Ramya");
        dto.setEmail("ramya@example.com");
        dto.setUserName("ramya123");
        dto.setPassword("plainPassword");
        dto.setRoleNames(Set.of("ADMIN"));

        Roles adminRole = new Roles();
        adminRole.setRoleId(1);
        adminRole.setRoleName("ADMIN");

        when(roleRepository.findByRoleName("ADMIN")).thenReturn(Optional.of(adminRole));
        when(passwordEncoder.encode("ramya")).thenReturn("ramya-encoded");

        String result = employeeService.addEmployee(dto);

        assertEquals("User added successfully!", result);
    }


}
