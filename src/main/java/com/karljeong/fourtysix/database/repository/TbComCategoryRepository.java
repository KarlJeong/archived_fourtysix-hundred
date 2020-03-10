package com.karljeong.fourtysix.database.repository;

import java.math.BigInteger;
import org.springframework.data.repository.PagingAndSortingRepository;
import com.karljeong.fourtysix.database.entity.TbComCategory;

public interface TbComCategoryRepository extends PagingAndSortingRepository<TbComCategory, BigInteger> {

}
