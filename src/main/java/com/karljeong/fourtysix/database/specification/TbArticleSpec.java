package com.karljeong.fourtysix.database.specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.karljeong.fourtysix.database.entity.TbArticle;

public class TbArticleSpec {

	public enum SearchKey {
		ARTICLECODE("articleCode"), ARTICLEWRITERID("articleWriterId");

		private final String value;

		SearchKey(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}
	}

	public static Specification<TbArticle> searchWithKeys(Map<SearchKey, Object> searchKeys) {
		return (Specification<TbArticle>) ((root, query, builder) -> {
			List<Predicate> predicate = getPredicateWithKeyword(searchKeys, root, builder);
			return builder.and(predicate.toArray(new Predicate[0]));
		});
	}

	private static List<Predicate> getPredicateWithKeyword(Map<SearchKey, Object> searchKeyword, Root<TbArticle> root,
			CriteriaBuilder builder) {
		List<Predicate> predicate = new ArrayList<>();
		for (SearchKey key : searchKeyword.keySet()) {
			switch (key) {
			case ARTICLECODE:
				predicate.add(builder.equal(root.get(key.value), searchKeyword.get(key)));
				break;
			case ARTICLEWRITERID:
				predicate.add(builder.equal(root.get(key.value), searchKeyword.get(key)));
				break;
			default:
				break;
			}
		}
		return predicate;
	}
}
