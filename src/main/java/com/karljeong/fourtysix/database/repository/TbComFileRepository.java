package com.karljeong.fourtysix.database.repository;

import java.math.BigInteger;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import com.karljeong.fourtysix.database.entity.TbComFile;

@RepositoryRestResource(collectionResourceRel = "fileEntity", path = "tbComFile")
public interface TbComFileRepository extends PagingAndSortingRepository<TbComFile, BigInteger>{

}
