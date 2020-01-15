package com.insat.maktabti.controller;

import com.insat.maktabti.DAO.UserDao;
import com.insat.maktabti.domain.User;
import com.insat.maktabti.domain.request.ReqUpdateUser;
import com.insat.maktabti.repositories.UserRepository;
import com.insat.maktabti.services.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.Principal;

@RestController
public class UserController {
    @Autowired
    private UserDao userDao;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private FileService fileService;
    @Autowired
    private PasswordEncoder encoder;

    @GetMapping(value = "/Users")
    public Iterable<User> getAllUsers() {
        Iterable<User> users = userDao.findAll();
        return users;
    }

    @GetMapping(value = "/user/{id}")
    public User getOneUser(@PathVariable int id) {
        return userDao.findById(id);
    }

    @DeleteMapping(value = "/User/{id}")
    public void deleteUser(@PathVariable int id) {
        userDao.deleteById(id);
    }

    @CrossOrigin
    @PutMapping(value = "/user")
    public ResponseEntity<?> updateUser(@ModelAttribute ReqUpdateUser user, Principal principal) throws IOException {
        System.out.println("AAAA" + user);
        User currentUser = userRepository.findByUsername(principal.getName()).orElseThrow(() -> new RuntimeException("User not found"));
        System.out.println("BBBB" + currentUser);

        currentUser.setName(user.getName());
        currentUser.setEmail(user.getEmail());
        currentUser.setUsername(user.getEmail());
        if (!user.getPassword().isEmpty()) {
            currentUser.setPassword(encoder.encode(user.getPassword()));
        }
        currentUser.setAddress(user.getAddress());
        currentUser.setAboutMe(user.getAboutMe());
        currentUser.setCity(user.getCity());
        currentUser.setCountry(user.getCountry());
        currentUser.setPostalCode(user.getPostalCode());

        if (!user.getImage().isEmpty()) {
            long id = userDao.count() + 1;
            String path = fileService.storeFile(user.getImage(), "US", id, "user");
            currentUser.setImageUrl(path);
        }

        userDao.save(currentUser);
        return new ResponseEntity<>(user, HttpStatus.OK);

    }

    @PostMapping(value = "/User")
    public User createUser(@RequestBody User user) {
        User userAdded = userDao.save(user);
        return userAdded;
    }

}
