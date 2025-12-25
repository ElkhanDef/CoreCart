package com.onlinestore.corecart.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

public class UserCreateDto {


    @NotBlank(message = "This field must be set")
    @Size(min = 3, max = 100, message = "Firstname length must be between 3 and 100 characters")
    private String firstName;

    @NotBlank(message = "This field must be set")
    @Size(min = 3, max = 100, message = "Lastname length must be between 3 and 100 characters")
    private String lastName;

    @NotBlank(message = "This field must be set")
    @Email(message = "Please enter a valid email")
    private String email;

    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
            message = "Password must be at least 8 characters long and include at least one lowercase letter, one uppercase letter, one number, and one special character."
    )
    @NotBlank(message = "This field must be set")
    private String password;

    @Pattern(
            regexp = "^994\\d{9}$",
            message = "Phone number must start with 994 and contain exactly 12 digits in total."
    )
    @NotBlank(message = "This field must be set")
    private String phoneNumber;

    @Valid
    @NotNull
    private AddressDto address;


    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public AddressDto getAddress() {
        return address;
    }

    public void setAddress(AddressDto address) {
        this.address = address;
    }
}
