package com.insat.maktabti.controller;

import com.insat.maktabti.DAO.PostDao;
import com.insat.maktabti.domain.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class PostController {
    @Autowired
    private PostDao postDao;

    @GetMapping(value = "/Posts")
    public Iterable<Post> getAllPosts() {
        Iterable<Post> posts = postDao.findAll();
        return posts;
    }

    @GetMapping(value = "/Post/{id}")
    public Post getOnePost(@PathVariable int id) {
        return postDao.findById(id);
    }

    @DeleteMapping(value = "/Post/{id}")
    public void deletePost(@PathVariable int id) {
        postDao.deleteById(id);
    }

    @PutMapping(value = "/Post")
    public void updateProduit(@RequestBody Post post) {
        postDao.save(post);
    }

    @PostMapping(value = "/Post")
    public Post createPost(@RequestBody Post post) {
        Post postAdded = postDao.save(post);
        return postAdded;
    }

}
