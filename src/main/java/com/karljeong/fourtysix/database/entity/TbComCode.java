package com.karljeong.fourtysix.database.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
//@Table(name = "TB_COM_CODE")
public class TbComCode extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codeId;
    private Long createUserId;
    private Long updateUserId;

    @Column(name = "codeGroupId", insertable = false, updatable = false)
    private Long codeGroupId;
    private String codeValue;
    private String codeName;
    private String codeDescription;
    private Integer codeOrder;
    private Integer useYn;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "codeGroupId" )
    private TbComCodeGroup tbComCodeGroup;

}
