package com.org.address.service;

import com.org.address.request.AddressRequest;
import com.org.address.response.AddressResponse;

import java.util.List;

public interface AddressService
{
    AddressResponse saveAddress(AddressRequest addressRequest);

    AddressResponse getAddress(int id);

    List<AddressResponse> getAll();

    AddressResponse updateAddress(int id,AddressRequest addressRequest);

    String deleteAddress(int id);

}
