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

import com.karljeong.fourtysix.database.entity.TbComUser;
import com.karljeong.fourtysix.database.entity.TbMappUserAuth;
import com.karljeong.fourtysix.database.entity.TbMappUserAuthPK;

@RepositoryRestResource(collectionResourceRel = "mappUserAuthEntity", path = "tbMappUserAuthRepository")
public interface TbMappUserAuthRepository
		extends PagingAndSortingRepository<TbMappUserAuth, TbMappUserAuthPK>, JpaSpecificationExecutor<TbMappUserAuth> {

    @Query("SELECT u FROM TbMappUserAuth c INNER JOIN TbComUser u ON u.userId = c.id.userId WHERE c.id.authId = :authId")
    List<TbComUser> findByAuthId(@Param("authId") BigInteger authId);

    @Transactional
    @Modifying
    @Query("DELETE FROM TbMappUserAuth c WHERE c.id.authId = :authId")
    int deleteByAuthId(@Param("authId") BigInteger authId);

}
