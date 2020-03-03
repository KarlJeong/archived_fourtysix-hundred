package com.karljeong.fourtysix.database.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.karljeong.fourtysix.database.entity.TbComCodeGroup;

@RepositoryRestResource(collectionResourceRel = "codeGroupEntity", path = "tbComCodeGroupRepository")
public interface TbComCodeGroupRepository extends PagingAndSortingRepository<TbComCodeGroup, Long> {
}
