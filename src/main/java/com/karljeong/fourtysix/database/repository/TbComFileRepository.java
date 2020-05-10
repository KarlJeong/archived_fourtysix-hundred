package com.karljeong.fourtysix.database.repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.karljeong.fourtysix.database.entity.TbComFile;

@RepositoryRestResource(collectionResourceRel = "fileEntity", path = "tbComFile")
public interface TbComFileRepository extends PagingAndSortingRepository<TbComFile, BigInteger> {

	@Query("SELECT f FROM TbComFile f WHERE f.id.fileId = :fileId")
	TbComFile findByFileId(BigInteger fileId);

	@Query("SELECT f FROM TbComFile f WHERE f.id.fileRefId = :fileRefId")
	List<TbComFile> findByFileRefId(BigInteger fileRefId);

	@Query("UPDATE TbComFile f SET f.deleteYn = 1 WHERE f.id.fileId = :fileId")
	int deleteFile(BigInteger fileId);

}
