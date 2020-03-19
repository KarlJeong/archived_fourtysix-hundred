package com.karljeong.fourtysix.database.repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.karljeong.fourtysix.database.entity.TbComMenu;

@RepositoryRestResource(collectionResourceRel = "menuEntity", path = "tbComMenuRepository")
public interface TbComMenuRepository
		extends PagingAndSortingRepository<TbComMenu, BigInteger>, JpaSpecificationExecutor<TbComMenu> {

	@Override
	List<TbComMenu> findAll();

	@Query(value = "WITH RECURSIVE C AS (" +
	        "    SELECT CREATE_DATETIME" +
	        "         , CREATE_USER_ID" +
	        "         , UPDATE_DATETIME" +
            "         , UPDATE_USER_ID" +
	        "         , MENU_DESCRIPTION" +
	        "         , MENU_ID" +
	        "         , PRIOR_MENU_ID" +
	        "         , MENU_NAME" +
	        "         , MENU_LEVEL" +
	        "         , MENU_ORDER" +
	        "         , MENU_PATH" +
	        "         , MENU_TYPE" +
	        "         , USE_YN" +
	        "         , CONCAT(MENU_LEVEL, MENU_ORDER) AS DISPLAY_ORD" +
	        "    FROM TB_COM_MENU" +
	        "    WHERE MENU_ID = 8" +
	        "    UNION ALL" +
	        "    SELECT M.CREATE_DATETIME" +
            "         , M.CREATE_USER_ID" +
            "         , M.UPDATE_DATETIME" +
            "         , M.UPDATE_USER_ID" +
            "         , M.MENU_DESCRIPTION" +
            "         , M.MENU_ID" +
	        "         , M.PRIOR_MENU_ID" +
	        "         , M.MENU_NAME" +
	        "         , M.MENU_LEVEL" +
	        "         , M.MENU_ORDER" +
	        "         , M.MENU_PATH" +
	        "         , M.MENU_TYPE" +
	        "         , M.USE_YN" +
	        "         , CONCAT(C.DISPLAY_ORD, M.MENU_ORDER) AS DISPLAY_ORD" +
	        "    FROM TB_COM_MENU M JOIN C ON M.PRIOR_MENU_ID = C.MENU_ID" +
	        ")" +
	        "SELECT * FROM C WHERE MENU_TYPE = ?1 ORDER BY DISPLAY_ORD ", nativeQuery=true)
    List<TbComMenu> findAll(String menuType);

}
