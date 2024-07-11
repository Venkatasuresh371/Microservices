package com.org.employee.addressClient;

import com.org.employee.response.AddressResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//@FeignClient(name = "ADDRESS-SERVICE")
public interface ApiClient
{
    @GetMapping(value = "/address/get/{id}")
    ResponseEntity<AddressResponse> getAddress(@PathVariable int id);
}
