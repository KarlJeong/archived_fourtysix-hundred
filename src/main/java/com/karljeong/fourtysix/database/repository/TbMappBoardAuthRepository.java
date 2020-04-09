package com.karljeong.fourtysix.database.repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.karljeong.fourtysix.database.entity.TbMappBoardAuth;
import com.karljeong.fourtysix.database.entity.TbMappBoardAuthPK;

@RepositoryRestResource(collectionResourceRel = "mappBoardAuthEntity", path = "tbMappBoardAuthRepository")
public interface TbMappBoardAuthRepository extends PagingAndSortingRepository<TbMappBoardAuth, TbMappBoardAuthPK>,
		JpaSpecificationExecutor<TbMappBoardAuth> {

    @Query("SELECT c FROM TbMappBoardAuth c WHERE c.id.boardId = :boardId AND c.readableYn = 1")
    List<TbMappBoardAuth> findReadableByBoardId(@Param("boardId") BigInteger boardId);

    @Query("SELECT c FROM TbMappBoardAuth c WHERE c.id.boardId = :boardId AND c.writableYn = 1")
    List<TbMappBoardAuth> findWritableByBoardId(@Param("boardId") BigInteger boardId);


}
