package com.insat.maktabti.controller;

import com.insat.maktabti.DAO.RoleDao;
import com.insat.maktabti.domain.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class RoleController {
    @Autowired
    private RoleDao roleDao;

    @GetMapping(value = "/Roles")
    public Iterable<Role> getAllRoles() {
        Iterable<Role> roles = roleDao.findAll();
        return roles;
    }

    @GetMapping(value = "/Role/{id}")
    public Role getOneRole(@PathVariable int id) {
        return roleDao.findById(id);
    }

    @DeleteMapping(value = "/Role/{id}")
    public void deleteRole(@PathVariable int id) {
        roleDao.deleteById(id);
    }

    @PutMapping(value = "/Role")
    public void updateProduit(@RequestBody Role role) {
        roleDao.save(role);
    }

    @PostMapping(value = "/Role")
    public Role createRole(@RequestBody Role role) {
        Role roleAdded = roleDao.save(role);
        return roleAdded;
    }

}
