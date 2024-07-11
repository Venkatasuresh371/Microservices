package com.org.employee.response;

import lombok.Data;

@Data
public class AddressResponse
{
    private int id;
    private String state;
    private String city;
    private String zipCode;
}
