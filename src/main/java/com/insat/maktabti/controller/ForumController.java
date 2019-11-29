package com.insat.maktabti.controller;

import com.insat.maktabti.DAO.ForumDao;
import com.insat.maktabti.domain.Forum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ForumController {
    @Autowired
    private ForumDao forumDao;

    @GetMapping(value = "/Forums")
    public Iterable<Forum> getAllForums() {
        Iterable<Forum> forums = forumDao.findAll();
        return forums;
    }

    @GetMapping(value = "/Forum/{id}")
    public Forum getOneForum(@PathVariable int id) {
        return forumDao.findById(id);
    }

    @DeleteMapping(value = "/Forum/{id}")
    public void deleteForum(@PathVariable int id) {
        forumDao.deleteById(id);
    }

    @PutMapping(value = "/Forum")
    public void updateProduit(@RequestBody Forum forum) {
        forumDao.save(forum);
    }

    @PostMapping(value = "/Forum")
    public Forum createForum(@RequestBody Forum forum) {
        Forum forumAdded = forumDao.save(forum);
        return forumAdded;
    }

}
