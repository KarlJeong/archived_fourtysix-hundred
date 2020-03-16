package com.karljeong.fourtysix.database.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.karljeong.fourtysix.database.entity.TbMappBoardAuth;
import com.karljeong.fourtysix.database.entity.TbMappBoardAuthPK;

@RepositoryRestResource(collectionResourceRel = "mappBoardAuthEntity", path = "tbMappBoardAuthRepository")
public interface TbMappBoardAuthRepository extends PagingAndSortingRepository<TbMappBoardAuth, TbMappBoardAuthPK>,
		JpaSpecificationExecutor<TbMappBoardAuth> {

}
