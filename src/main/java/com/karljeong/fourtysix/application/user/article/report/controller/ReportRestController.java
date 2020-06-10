package com.karljeong.fourtysix.application.user.article.report.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.karljeong.fourtysix.application.user.article.report.service.ReportService;
import com.karljeong.fourtysix.database.entity.TbArticleReport;
import com.karljeong.fourtysix.resulthandler.ResultDto;
import com.karljeong.fourtysix.resulthandler.ResultDto.ResultCodeEnum;
import com.karljeong.fourtysix.resulthandler.ResultSetter;

@RestController
@RequestMapping("/v1/api/b/report")
public class ReportRestController {

    @Autowired
    ReportService reportService;

    @PostMapping("/article")
    public ResultDto reportArticle(@RequestBody TbArticleReport tbArticleReport, HttpServletRequest request) {
        tbArticleReport.setUserInfo(request);
        TbArticleReport createdTbArticleReport = reportService.reportArticle(tbArticleReport);
        return new ResultSetter(ResultCodeEnum.SUCCESS, createdTbArticleReport).getResultDto();
    }
}
