package com.karljeong.fourtysix.database.repository;

import java.math.BigInteger;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.karljeong.fourtysix.database.entity.TbComAuth;
import com.karljeong.fourtysix.database.entity.TbMappPatternAuth;
import com.karljeong.fourtysix.database.entity.TbMappPatternAuthPK;

@RepositoryRestResource(collectionResourceRel = "mappPatternAuthEntity", path = "tbMappPatternAuthRepository")
public interface TbMappPatternAuthRepository
		extends PagingAndSortingRepository<TbMappPatternAuth, TbMappPatternAuthPK>, JpaSpecificationExecutor<TbMappPatternAuth> {

    @Query("SELECT u FROM TbMappPatternAuth c INNER JOIN TbComAuth u ON u.authId = c.id.authId WHERE c.id.patternId = :patternId")
    List<TbComAuth> findByPatternId(@Param("patternId") BigInteger patternId);

    @Transactional
    @Modifying
    @Query("DELETE FROM TbMappPatternAuth c WHERE c.id.patternId = :patternId")
    int deleteByPatternId(@Param("patternId") BigInteger patternId);


}
