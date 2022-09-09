package com.mycs.server;

import com.mycs.entities.Roles;
import com.mycs.repository.RolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    @Autowired
    RolesRepository rolesRepository;

    public Roles getOrCreate(String roleName) {
        Roles isExisting = rolesRepository.findByName(roleName);
        if (isExisting != null) {
            return isExisting;
        }

        isExisting = new Roles();
        isExisting.setName(roleName);
        rolesRepository.save(isExisting);

        return isExisting;
    }
}
