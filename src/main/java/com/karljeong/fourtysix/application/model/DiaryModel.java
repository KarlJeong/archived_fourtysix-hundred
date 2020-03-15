package com.karljeong.fourtysix.application.model;

import org.springframework.web.multipart.MultipartFile;
import com.karljeong.fourtysix.database.entity.TbArticleDiary;
import lombok.Data;

@Data
public class DiaryModel {

    private TbArticleDiary tbArticleDiary;
    private MultipartFile articleUploadFile;
}
