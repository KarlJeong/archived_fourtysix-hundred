package com.karljeong.fourtysix.database.specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.karljeong.fourtysix.database.entity.TbComCode;

public class TbComCodeSpec {

    public enum SearchKey {
        CODEGROUPNAME("codeGroupName"), CODEGROUPVALUE("codeGroupValue"), CODENAME("codeName"), CODEVALUE("codeValue"), USEYN("useYn");

        private final String value;

        SearchKey(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    public static Specification<TbComCode> searchWithKeys(Map<SearchKey, Object> searchKeys) {
        return (Specification<TbComCode>) ((root, query, builder) -> {
            List<Predicate> predicate = getPredicateWithKeyword(searchKeys, root, builder);
            return builder.and(predicate.toArray(new Predicate[0]));
        });
    }

    private static List<Predicate> getPredicateWithKeyword(Map<SearchKey, Object> searchKeyword,
            Root<TbComCode> root, CriteriaBuilder builder) {
        List<Predicate> predicate = new ArrayList<>();
        for (SearchKey key : searchKeyword.keySet()) {
            switch (key) {
            case CODEGROUPNAME:
                predicate.add(builder.equal(root.get(key.value), searchKeyword.get(key)));
                break;
            case CODEGROUPVALUE:
                predicate.add(builder.equal(root.get(key.value), searchKeyword.get(key)));
                break;
            case CODENAME:
                predicate.add(builder.equal(root.get(key.value), searchKeyword.get(key)));
                break;
            case CODEVALUE:
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
