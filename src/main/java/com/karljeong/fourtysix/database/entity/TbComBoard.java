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
public class TbComBoard extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long boardId;
    private String boardName;
    private String boardDescription;
    private String boardCode;
    private Integer useYn;
    private Integer sysBoardYn;

}
