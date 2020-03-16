package com.karljeong.fourtysix.database.specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.karljeong.fourtysix.database.entity.TbComBoard;

public class TbComBoardSpec {

	public enum SearchKey {
		BOARDNAME("boardName"), BOARDCODE("boardCode"), SYSBOARDYN("sysBoardYn"), USEYN("useYn");

		private final String value;

		SearchKey(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}
	}

	public static Specification<TbComBoard> searchWithKeys(Map<SearchKey, Object> searchKeys) {
		return (Specification<TbComBoard>) ((root, query, builder) -> {
			List<Predicate> predicate = getPredicateWithKeyword(searchKeys, root, builder);
			return builder.and(predicate.toArray(new Predicate[0]));
		});
	}

	private static List<Predicate> getPredicateWithKeyword(Map<SearchKey, Object> searchKeyword, Root<TbComBoard> root,
			CriteriaBuilder builder) {
		List<Predicate> predicate = new ArrayList<>();
		for (SearchKey key : searchKeyword.keySet()) {
			switch (key) {
			case BOARDNAME:
				predicate.add(builder.equal(root.get(key.value), searchKeyword.get(key)));
				break;
			case BOARDCODE:
				predicate.add(builder.equal(root.get(key.value), searchKeyword.get(key)));
				break;
			case SYSBOARDYN:
				predicate.add(builder.equal(root.get(key.value), searchKeyword.get(key)));
				break;
			case USEYN:
				predicate.add(builder.equal(root.get(key.value), Integer.valueOf(searchKeyword.get(key).toString())));
				break;
			default:
				break;
			}
		}
		return predicate;
	}
}
