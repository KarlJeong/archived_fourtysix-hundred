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

    @Query(value = "SELECT B.AUTH_CODE, C.URI_PATTERN "
                + "FROM TB_MAPP_PATTERN_AUTH A "
                + "INNER JOIN TB_COM_AUTH B ON A.AUTH_ID = B.AUTH_ID "
                + "INNER JOIN TB_COM_PATTERN C ON A.PATTERN_ID = C.PATTERN_ID "
            + "WHERE A.DELETE_YN = 0", nativeQuery=true)
    List<String[]> findAllPatternAuth();

    @Transactional
    @Modifying
    @Query("DELETE FROM TbMappPatternAuth c WHERE c.id.patternId = :patternId")
    int deleteByPatternId(@Param("patternId") BigInteger patternId);


}
