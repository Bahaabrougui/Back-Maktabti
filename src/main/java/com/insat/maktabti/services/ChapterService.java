package com.insat.maktabti.services;

import com.insat.maktabti.DAO.ChapterDao;
import com.insat.maktabti.ModelView.ChapterMV;
import com.insat.maktabti.domain.Chapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChapterService {

    @Autowired
    private ChapterDao chapterDao;

    public void save(ChapterMV chapterMV) {
        System.out.println(chapterMV.getId());
        System.out.println(chapterMV.getContent());
        Chapter chaptre = chapterDao.findById(Long.parseLong(chapterMV.getId()));
        chaptre.setContent(chapterMV.getContent());
        chapterDao.save(chaptre);
    }
}
