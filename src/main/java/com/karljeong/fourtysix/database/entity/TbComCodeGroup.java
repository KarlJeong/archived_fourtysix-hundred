package com.karljeong.fourtysix.database.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
//@Table(name = "TB_COM_CODE_GROUP")
public class TbComCodeGroup extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codeGroupId;
    private Long createUserId;
    private Long updateUserId;
    private String codeGroupValue;
    private String codeGroupName;
    private String codeGroupType;
    private String codeGroupDescription;
    private Integer useYn;

    @JsonManagedReference
    @OneToMany(mappedBy = "codeGroupId")
    private List<TbComCode> tbComCode = new ArrayList<TbComCode>();

}
