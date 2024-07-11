package com.org.employee.controller;

import com.org.employee.request.EmployeeRequest;
import com.org.employee.response.EmployeeAddressResponse;
import com.org.employee.response.EmployeeResponse;
import com.org.employee.service.EmployeeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/employee")
@Tag(name = "Employee")
public class EmployeeController
{
    @Autowired
    private EmployeeService employeeService;

    @PostMapping(value = "/save")
    public ResponseEntity<EmployeeResponse> saveEmployee(@RequestBody EmployeeRequest employeeRequest)
    {
        System.out.println();
        EmployeeResponse employeeResponse = employeeService.saveEmployee(employeeRequest);
        return new ResponseEntity<>(employeeResponse, HttpStatus.CREATED);
    }

    @GetMapping(value = "/get/{id}")
    public ResponseEntity<EmployeeResponse> getEmployee(@PathVariable(value = "id") int id)
    {
        EmployeeResponse employeeResponse = employeeService.getEmployee(id);
        return new ResponseEntity<>(employeeResponse,HttpStatus.FOUND);
    }

    @GetMapping(value = "/get-all")
    public ResponseEntity<List<EmployeeResponse>> getAllEmployee()
    {
        List<EmployeeResponse> employeeResponseList = employeeService.getAllEmployees();
        return new ResponseEntity<>(employeeResponseList,HttpStatus.OK);
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<EmployeeResponse> updateEmployee(@PathVariable int id, @RequestBody EmployeeRequest employeeRequest)
    {
        EmployeeResponse employeeResponse = employeeService.updateEmployee(id,employeeRequest);
        return new ResponseEntity<>(employeeResponse, HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable int id)
    {
        String response = employeeService.deleteEmployee(id);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
}