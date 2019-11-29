package com.insat.maktabti.controller;

import com.insat.maktabti.DAO.ChapterDao;
import com.insat.maktabti.domain.Chapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ChapterController {
    @Autowired
    private ChapterDao chapterDao;

    @GetMapping(value = "/Chapters")
    public Iterable<Chapter> getAllChapters() {
        Iterable<Chapter> chapters = chapterDao.findAll();
        return chapters;
    }

    @GetMapping(value = "/Chapter/{id}")
    public Chapter getOneChapter(@PathVariable int id) {
        return chapterDao.findById(id);
    }

    @DeleteMapping(value = "/Chapter/{id}")
    public void deleteChapter(@PathVariable int id) {
        chapterDao.deleteById(id);
    }

    @PutMapping(value = "/Chapter")
    public void updateProduit(@RequestBody Chapter chapter) {
        chapterDao.save(chapter);
    }

    @PostMapping(value = "/Chapter")
    public Chapter createChapter(@RequestBody Chapter chapter) {
        Chapter chapterAdded = chapterDao.save(chapter);
        return chapterAdded;
    }

}
