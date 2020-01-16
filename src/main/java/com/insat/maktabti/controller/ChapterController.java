package com.insat.maktabti.controller;

import com.insat.maktabti.DAO.ChapterDao;
import com.insat.maktabti.DAO.StoryDao;
import com.insat.maktabti.ModelView.ChapterMV;
import com.insat.maktabti.domain.Chapter;
import com.insat.maktabti.domain.Story;
import com.insat.maktabti.domain.User;
import com.insat.maktabti.repositories.UserRepository;
import com.insat.maktabti.services.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @Autowired
    private StoryDao storyDao;


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

    @GetMapping(value = "/story/{idStory}/chapter/{id}")
    @CrossOrigin
    public Chapter getChaptersByStory(@PathVariable int id, @PathVariable int idStory) {
        return chapterDao.findByStoryIdAndAndNumero(idStory, id);
    }

    @GetMapping(value = "/story/{idStory}/chapter")
    @CrossOrigin
    public Page<Chapter> getChaptersStory(@PathVariable int idStory, Pageable pageable) {
        return chapterDao.findAllByStoryId(idStory, pageable);
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

    @PostMapping(value = "/chapters-story/{id}")
    @CrossOrigin
    public Chapter createChapter(@RequestBody Chapter chapter,@PathVariable int id, Principal principal) {

       User currentUser = userDao.findByUsername(principal.getName()).orElseThrow(() -> new RuntimeException("User not found"));
        Story story = storyDao.findById(id);
        if (story!= null && story.getOwner().getId() != currentUser.getId()){
            throw new RuntimeException("Access not authorised");
        }
        chapter.setStory(story);
        int count = chapterDao.countAllByStoryId(story.getId()) +1;
        chapter.setNumero(count);
        Chapter chapterAdded = chapterDao.save(chapter);
        return chapterAdded;
    }
}
