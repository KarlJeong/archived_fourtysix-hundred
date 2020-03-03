package com.karljeong.fourtysix.database.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
//@Table(name = "TB_COM_CODE_GROUP")
public class TbComCodeGroup extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codeGroupId;
    private String codeGroupValue;
    private String codeGroupName;
    private String codeGroupType;
    private String codeGroupDescription;
    private Integer useYn;

}
