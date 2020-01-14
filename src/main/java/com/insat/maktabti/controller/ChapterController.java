package com.insat.maktabti.controller;

import com.insat.maktabti.DAO.ChapterDao;
import com.insat.maktabti.ModelView.ChapterMV;
import com.insat.maktabti.domain.Chapter;
import com.insat.maktabti.domain.User;
import com.insat.maktabti.repositories.UserRepository;
import com.insat.maktabti.services.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
public class ChapterController {
    @Autowired
    private ChapterDao chapterDao;

    @Autowired
    private ChapterService chapterService;

    @Autowired
    private UserRepository userDao;


    @GetMapping(value = "/Chapters")
    @CrossOrigin
    public Iterable<Chapter> getAllChapters() {
        Iterable<Chapter> chapters = chapterDao.findAll();
        return chapters;
    }

    @GetMapping(value = "/Chapter/{id}")
    @CrossOrigin
    public Chapter getOneChapter(@PathVariable int id) {
        return chapterDao.findById(Long.parseLong(String.valueOf(id)));
    }

    @DeleteMapping(value = "/Chapter/{id}")
    @CrossOrigin
    public void deleteChapter(@PathVariable int id) {
        chapterDao.deleteById(id);
    }


    @PutMapping(value = "/Chapter")
    @CrossOrigin
    public void updateProduit(@RequestBody ChapterMV chapter) {
        chapterService.save(chapter);
    }

    @PostMapping(value = "/Chapter")
    @CrossOrigin
    public Chapter createChapter(@RequestBody Chapter chapter, Principal principal) {
        System.out.println("ffffffff" + chapter.getName());
        User currentUser = userDao.findByUsername(principal.getName()).orElseThrow(() -> new RuntimeException("User not found"));
        chapter.setUser(currentUser);
        Chapter chapterAdded = chapterDao.save(chapter);
        return chapterAdded;
    }
}
