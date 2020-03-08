package com.karljeong.fourtysix.application.article.diary.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.karljeong.fourtysix.database.entity.TbArticleDiary;
import com.karljeong.fourtysix.database.repository.TbArticleDiaryRepository;
import com.karljeong.fourtysix.utils.DateUtil;

@Service
public class DiaryService {
    @Autowired
    TbArticleDiaryRepository tbArticleDiaryRepository;

    public TbArticleDiary create(TbArticleDiary tbArticleDiary) {
        tbArticleDiary.setCreateDatetime(DateUtil.getTimestamp());
        TbArticleDiary save = tbArticleDiaryRepository.save(tbArticleDiary);
        return save;
    }
}
