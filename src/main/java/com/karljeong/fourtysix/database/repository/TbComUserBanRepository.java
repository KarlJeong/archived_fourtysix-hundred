package com.karljeong.fourtysix.database.repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.karljeong.fourtysix.database.entity.TbComUserBan;

@RepositoryRestResource(collectionResourceRel = "userBanEntity", path = "tbComUserBanRepository")
public interface TbComUserBanRepository
		extends PagingAndSortingRepository<TbComUserBan, BigInteger>, JpaSpecificationExecutor<TbComUserBan> {

    List<TbComUserBan> findByBanUserIdAndBanYn(BigInteger userId, byte banYn);

}
