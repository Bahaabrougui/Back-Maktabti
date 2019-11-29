package com.insat.maktabti.controller;

import com.insat.maktabti.DAO.TopicDao;
import com.insat.maktabti.domain.Topic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class TopicController {
    @Autowired
    private TopicDao topicDao;

    @GetMapping(value = "/Topics")
    public Iterable<Topic> getAllTopics() {
        Iterable<Topic> topics = topicDao.findAll();
        return topics;
    }

    @GetMapping(value = "/Topic/{id}")
    public Topic getOneTopic(@PathVariable int id) {
        return topicDao.findById(id);
    }

    @DeleteMapping(value = "/Topic/{id}")
    public void deleteTopic(@PathVariable int id) {
        topicDao.deleteById(id);
    }

    @PutMapping(value = "/Topic")
    public void updateProduit(@RequestBody Topic topic) {
        topicDao.save(topic);
    }

    @PostMapping(value = "/Topic")
    public Topic createTopic(@RequestBody Topic topic) {
        Topic topicAdded = topicDao.save(topic);
        return topicAdded;
    }

}
