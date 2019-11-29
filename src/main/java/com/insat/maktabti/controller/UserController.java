package com.insat.maktabti.controller;

import com.insat.maktabti.DAO.UserDao;
import com.insat.maktabti.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
    private UserDao userDao;

    @GetMapping(value = "/Users")
    public Iterable<User> getAllUsers() {
        Iterable<User> users = userDao.findAll();
        return users;
    }

    @GetMapping(value = "/User/{id}")
    public User getOneUser(@PathVariable int id) {
        return userDao.findById(id);
    }

    @DeleteMapping(value = "/User/{id}")
    public void deleteUser(@PathVariable int id) {
        userDao.deleteById(id);
    }

    @PutMapping(value = "/User")
    public void updateProduit(@RequestBody User user) {
        userDao.save(user);
    }

    @PostMapping(value = "/User")
    public User createUser(@RequestBody User user) {
        User userAdded = userDao.save(user);
        return userAdded;
    }

}
