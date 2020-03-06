package com.karljeong.fourtysix.database.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.karljeong.fourtysix.database.entity.TbComCodeGroup;

@RepositoryRestResource(collectionResourceRel = "codeGroupEntity", path = "tbComCodeGroupRepository")
public interface TbComCodeGroupRepository
		extends PagingAndSortingRepository<TbComCodeGroup, Long>, JpaSpecificationExecutor<TbComCodeGroup> {

	@Override
	List<TbComCodeGroup> findAll();

	@Override
	Page<TbComCodeGroup> findAll(Pageable pageable);
}
