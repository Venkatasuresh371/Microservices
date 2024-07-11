package com.org.employee.service;

import com.org.employee.request.EmployeeRequest;
import com.org.employee.response.EmployeeAddressResponse;
import com.org.employee.response.EmployeeResponse;

import java.util.List;

public interface EmployeeService
{
    EmployeeResponse saveEmployee(EmployeeRequest employeeRequest);

    EmployeeResponse getEmployee(int id);

    List<EmployeeResponse> getAllEmployees();

    String deleteEmployee(int id);

    EmployeeResponse updateEmployee(int id,EmployeeRequest employeeRequest);

    EmployeeAddressResponse getEmployeewithAddressById(int id);
}
