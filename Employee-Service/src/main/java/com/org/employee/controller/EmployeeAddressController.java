package com.org.employee.controller;

import com.org.employee.response.EmployeeAddressResponse;
import com.org.employee.service.EmployeeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/employee-address")
@Tag(name = "Employee-Address")
public class EmployeeAddressController
{
    @Autowired
    private EmployeeService employeeService;

    @GetMapping(value = "/get-employee-address/{id}")
    public ResponseEntity<EmployeeAddressResponse> getEmployeeAddressById(@PathVariable int id)
    {
        EmployeeAddressResponse employeeAddressResponse = employeeService.getEmployeewithAddressById(id);
        return new ResponseEntity<>(employeeAddressResponse, HttpStatus.OK);
    }
}
