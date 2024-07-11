package com.org.address.response;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class AddressResponse
{
    private int id;
    private String state;
    private String city;
    private String zipCode;
}