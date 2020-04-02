package com.karljeong.fourtysix.database.repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.karljeong.fourtysix.database.entity.TbComPattern;

@RepositoryRestResource(collectionResourceRel = "patternEntity", path = "tbComPatternRepository")
public interface TbComPatternRepository
		extends PagingAndSortingRepository<TbComPattern, BigInteger>, JpaSpecificationExecutor<TbComPattern> {

	@Override
	List<TbComPattern> findAll();
}
