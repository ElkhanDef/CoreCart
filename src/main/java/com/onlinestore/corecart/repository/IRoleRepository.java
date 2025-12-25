package com.onlinestore.corecart.repository;

import com.onlinestore.corecart.enums.RoleNames;
import com.onlinestore.corecart.model.Role;

import java.util.Optional;

public interface IRoleRepository {


    Optional<Role> getRoleByName(RoleNames roleName);
}
