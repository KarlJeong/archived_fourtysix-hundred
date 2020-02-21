package com.karljeong.fourtysix.database.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.karljeong.fourtysix.database.entity.TbComBoard;

@RepositoryRestResource(collectionResourceRel = "boardEntity", path = "tbComBoard")
public interface TbComBoardRepository extends PagingAndSortingRepository<TbComBoard, Long> {

    List<TbComBoard> findByBoardName(@Param("boardName") String boardName);
}
