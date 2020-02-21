package com.karljeong.fourtysix.database.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class TbComFile extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private String fileId;
    private String fileRefId;
    private String fileOrder;
    private String fileOrginalName;
    private String filePath;
    private String fileName;
    private String fileExtention;
    private String fileSize;
    private String fileParamName;
    private String fileType;
    private String deleteYn;
}
