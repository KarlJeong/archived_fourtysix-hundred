package com.karljeong.fourtysix.database.repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.karljeong.fourtysix.database.entity.TbComMenu;

@RepositoryRestResource(collectionResourceRel = "menuEntity", path = "tbComMenuRepository")
public interface TbComMenuRepository
		extends PagingAndSortingRepository<TbComMenu, BigInteger>, JpaSpecificationExecutor<TbComMenu> {

	@Override
	List<TbComMenu> findAll();

}
