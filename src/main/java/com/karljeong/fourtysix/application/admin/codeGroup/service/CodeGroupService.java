package com.karljeong.fourtysix.application.admin.codeGroup.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.karljeong.fourtysix.database.entity.TbComCodeGroup;
import com.karljeong.fourtysix.database.repository.TbComCodeGroupRepository;

@Service
public class CodeGroupService {
    @Autowired
    TbComCodeGroupRepository tbComCodeGroupRepository;

    public TbComCodeGroup create(TbComCodeGroup tbComCodeGroup) {
        tbComCodeGroup.setCreateUserId(Long.valueOf(11111));
        TbComCodeGroup save = tbComCodeGroupRepository.save(tbComCodeGroup);
        return save;
    }
}
