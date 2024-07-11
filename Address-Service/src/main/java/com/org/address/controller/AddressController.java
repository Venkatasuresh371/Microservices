package com.org.address.controller;

import com.org.address.request.AddressRequest;
import com.org.address.response.AddressResponse;
import com.org.address.service.AddressService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/address")
@Tag(name = "Address")
public class AddressController
{
    @Autowired
    private AddressService addressService;

    @PostMapping(value = "/save")
    public ResponseEntity<AddressResponse> saveAddress(@RequestBody AddressRequest addressRequest)
    {
        AddressResponse addressResponse = addressService.saveAddress(addressRequest);
        return new ResponseEntity<>(addressResponse, HttpStatus.CREATED);
    }

    @GetMapping(value = "/get-all")
    public ResponseEntity<List<AddressResponse>> saveAddress()
    {
        List<AddressResponse> addressResponseList = addressService.getAll();
        return new ResponseEntity<>(addressResponseList, HttpStatus.OK);
    }
    @GetMapping(value = "/get/{id}")
    public ResponseEntity<AddressResponse> getAddress(@PathVariable(value = "id") int id)
    {
        System.out.println("id ----> "+id);
        AddressResponse addressResponse = addressService.getAddress(id);
        System.out.println("Address Response ---> "+addressResponse);
        return new ResponseEntity<>(addressResponse, HttpStatus.FOUND);
    }
    @PutMapping(value = "/update/{id}")
    public ResponseEntity<AddressResponse> updateAddress(@PathVariable int id,@RequestBody AddressRequest addressRequest)
    {
        AddressResponse addressResponse = addressService.updateAddress(id, addressRequest);
        return new ResponseEntity<>(addressResponse, HttpStatus.OK);
    }
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<String>  deleteAddress(@PathVariable int id)
    {
        String response = addressService.deleteAddress(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}