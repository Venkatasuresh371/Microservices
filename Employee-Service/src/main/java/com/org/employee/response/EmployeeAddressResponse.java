package com.org.employee.response;

import lombok.Data;

@Data
public class EmployeeAddressResponse
{
    private EmployeeResponse employeeResponse;
    private AddressResponse addressResponse;
}
