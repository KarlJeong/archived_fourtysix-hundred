package com.karljeong.fourtysix.application.user.article.report.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.karljeong.fourtysix.database.entity.TbArticleReplyReport;
import com.karljeong.fourtysix.database.entity.TbArticleReport;
import com.karljeong.fourtysix.database.repository.TbArticleReplyReportRepository;
import com.karljeong.fourtysix.database.repository.TbArticleReportRepository;

@Service
public class ReportService {
    @Autowired
    TbArticleReportRepository tbArticleReportRepository;

    @Autowired
    TbArticleReplyReportRepository tbArticleReplyReportRepository;

    public TbArticleReport reportArticle(TbArticleReport tbArticleReport) {
        return tbArticleReportRepository.save(tbArticleReport);
    }

    public TbArticleReplyReport reportArticleReply(TbArticleReplyReport tbArticleReplyReport) {
        return tbArticleReplyReportRepository.save(tbArticleReplyReport);
    }
}
