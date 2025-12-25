package com.onlinestore.corecart.repository;

import com.onlinestore.corecart.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAddressRepository extends JpaRepository<Address,Long> {


   // Address findByAddressAndDistrictAndCity(String address, String district, String city);


}
