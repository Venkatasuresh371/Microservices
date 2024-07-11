package com.org.address.service;

import com.org.address.entity.Address;
import com.org.address.repository.AddressRepository;
import com.org.address.request.AddressRequest;
import com.org.address.response.AddressResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class AddressServiceImpl implements AddressService
{
    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private Environment environment;

    @Override
    public AddressResponse saveAddress(AddressRequest addressRequest)
    {
        log.info("Address Request {} ",addressRequest);
        Address address = mapToAddress(addressRequest);
        Address savedAddress = addressRepository.save(address);
        AddressResponse addressResponse = mapToAddressResponse(savedAddress);
        return addressResponse;
    }

    private AddressResponse mapToAddressResponse(Address address)
    {
        AddressResponse addressResponse = new AddressResponse();
        addressResponse.setId(address.getId());
        addressResponse.setState(address.getState());
        addressResponse.setCity(address.getCity());
        addressResponse.setZipCode(address.getZipCode());
        return addressResponse;
    }

    private Address mapToAddress(AddressRequest addressRequest)
    {
        Address address = new Address();
        address.setState(addressRequest.getState());
        address.setCity(addressRequest.getCity());
        address.setZipCode(addressRequest.getZipCode());
        return address;
    }

    @Override
    public AddressResponse getAddress(int id)
    {
        log.info("Address id {}",id);
        System.out.println("id ---> "+id);
        Address address = addressRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Address Not Found ---> "+id));
        AddressResponse addressResponse = mapToAddressResponse(address);
        System.out.println(addressResponse+ " -----> "+environment.getProperty("server.port"));
        log.info("Address Response {}",addressResponse);
        return addressResponse;
    }

    @Override
    public List<AddressResponse> getAll()
    {
        List<Address> addressList = addressRepository.findAll();
        List<AddressResponse> addressResponseList = new ArrayList<>();
        for (Address address:addressList) {
            AddressResponse addressResponse = mapToAddressResponse(address);
            addressResponseList.add(addressResponse);
        }
        return addressResponseList;
    }

    @Override
    public AddressResponse updateAddress(int id,AddressRequest addressRequest)
    {
        Address oldAddress = addressRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Address Not Found"));

        oldAddress.setState(!addressRequest.getState().isBlank()?addressRequest.getState():oldAddress.getState());
        oldAddress.setCity(!addressRequest.getCity().isBlank()?addressRequest.getCity():oldAddress.getCity());
        oldAddress.setZipCode(!addressRequest.getZipCode().isBlank()?addressRequest.getZipCode():oldAddress.getZipCode());

        Address newAddress = addressRepository.save(oldAddress);
        AddressResponse addressResponse = mapToAddressResponse(newAddress);
        return addressResponse;
    }
    @Override
    public String deleteAddress(int id)
    {
        Address address = addressRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Address Not Found"));
        addressRepository.delete(address);

        return "Address Deleted Successfully";
    }
}