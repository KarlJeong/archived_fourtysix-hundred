package com.karljeong.fourtysix.application.model;

import org.springframework.web.multipart.MultipartFile;
import com.karljeong.fourtysix.database.entity.TbArticleBlog;
import lombok.Data;

@Data
public class DiaryModel {

    private TbArticleBlog tbArticleDiary;
    private MultipartFile articleUploadFile;
}
