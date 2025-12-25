package com.onlinestore.corecart.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class AddressDto {



    @NotBlank(message = "This field must be set")
    @Size(min = 3, max = 100 ,message = "Address length must be between 3 and 100 characters")
    private String address;
    @NotBlank(message = "This field must be set")
    @Size(min=3,max = 100, message = "District length must be between 3 and 100 characters")
    private String district;
    @NotBlank(message = "This field must be set")
    @Size(min = 2 ,max = 50,message = "City length must be between 2 and 50 characters")
    private String city;


    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
