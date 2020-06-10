package com.karljeong.fourtysix.application.user.article.report.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.karljeong.fourtysix.database.entity.TbArticleReport;
import com.karljeong.fourtysix.database.repository.TbArticleReportRepository;

@Service
public class ReportService {
    @Autowired
    TbArticleReportRepository tbArticleReportRepository;

    public TbArticleReport reportArticle(TbArticleReport tbArticleReport) {
        return tbArticleReportRepository.save(tbArticleReport);
    }
}
