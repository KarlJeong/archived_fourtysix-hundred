package com.karljeong.fourtysix.application.user.article.diary.service;

import java.math.BigInteger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.karljeong.fourtysix.database.entity.TbArticleDiary;
import com.karljeong.fourtysix.database.repository.TbArticleDiaryRepository;
import com.karljeong.fourtysix.utils.DateUtil;

@Service
public class DiaryService {
    @Autowired
    TbArticleDiaryRepository tbArticleDiaryRepository;

    public Iterable<TbArticleDiary> getDiaries(TbArticleDiary tbArticleDiary) {
        return tbArticleDiaryRepository.findAll();
    }

    public TbArticleDiary getDiary(String articleId) {
        return tbArticleDiaryRepository.findById(new BigInteger(articleId)).orElseGet(() -> new TbArticleDiary());
    }

    public TbArticleDiary create(TbArticleDiary tbArticleDiary) {
        tbArticleDiary.setThumbnailFileId(new BigInteger("1"));
        tbArticleDiary.setCreateUserId(new BigInteger("88"));
        tbArticleDiary.setArticleWriterId(new BigInteger("88"));
        tbArticleDiary.setArticleWriteDatetime(DateUtil.getTimestamp());
        TbArticleDiary save = tbArticleDiaryRepository.save(tbArticleDiary);
        return save;
    }
}
