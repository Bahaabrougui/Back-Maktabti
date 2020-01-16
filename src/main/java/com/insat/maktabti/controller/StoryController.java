package com.insat.maktabti.controller;

import com.insat.maktabti.DAO.StoryDao;
import com.insat.maktabti.DAO.UserDao;
import com.insat.maktabti.domain.Story;
import com.insat.maktabti.domain.User;
import com.insat.maktabti.domain.enumerations.StoryStatus;
import com.insat.maktabti.domain.enumerations.Type;
import com.insat.maktabti.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;


import java.security.Principal;

@RestController
public class StoryController {
    @Autowired
    private StoryDao storyDao;

    @Autowired
    private UserRepository userDao;

    @GetMapping(value = "/my-stories")
    @CrossOrigin
    public Page<Story> getAllMyStores(Pageable pageable, Principal principal) {
        User currentUser = userDao.findByUsername(principal.getName()).orElseThrow(() -> new RuntimeException("User not found"));
        return storyDao.findAllByStatusAndOwnerId(StoryStatus.INPROGRESS.name(),currentUser.getId(), pageable);
    }

    @GetMapping(value = "/story")
    @CrossOrigin
    public Page<Story> getAllStores(Pageable pageable, Principal principal) {
        User currentUser = userDao.findByUsername(principal.getName()).orElseThrow(() -> new RuntimeException("User not found"));
        return storyDao.findAllByStatusAndOwnerIdNot(StoryStatus.INPROGRESS.name(),currentUser.getId(), pageable);
    }

    @GetMapping(value = "/story/{id}")
    @CrossOrigin
    public Story getOneStory(@PathVariable int id) {
        return storyDao.findById(id);
    }

    @DeleteMapping(value = "/Story/{id}")
    public void deleteStory(@PathVariable int id) {
        storyDao.deleteById(id);
    }

    @PutMapping(value = "/Story")
    public void updateStory(@RequestBody Story story) {
        storyDao.save(story);
    }

    @PostMapping(value = "/story")
    @CrossOrigin
    public Story createStory(@RequestBody Story story, Principal principal) {
        User currentUser = userDao.findByUsername(principal.getName()).orElseThrow(() -> new RuntimeException("User not found"));
        story.setOwner(currentUser);
        story.setStatus(StoryStatus.INPROGRESS.name());
        Story storyAdded = storyDao.save(story);
        return storyAdded;
    }

}
