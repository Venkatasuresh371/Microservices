package com.org.address.request;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class AddressRequest
{
    private String state;
    private String city;
    private String zipCode;
}
