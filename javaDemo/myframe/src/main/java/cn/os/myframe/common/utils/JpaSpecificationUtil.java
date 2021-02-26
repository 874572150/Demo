package cn.os.myframe.common.utils;

import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * @author oushuo
 * @description JpaSpecification工具类
 * @date 2020/10/29
 */
public class JpaSpecificationUtil {
    public static <T> Specification<T> createSpecification(List<SearchCondition> searchConditions, Class<T> t) {
        Specification<T> tSpecification = (root, query, criteriaBuilder) -> {
            return bySearchCondition(searchConditions,t,root,query,criteriaBuilder);
        };

        return tSpecification;
    }

    private static <T> Predicate bySearchCondition(List<SearchCondition> searchConditions,Class<T> t, Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();
        for (SearchCondition searchCondition : searchConditions) {
            try {
                Field field = t.getField(searchCondition.getFieldName());

                switch (searchCondition.getOpr()) {
                    case EQ:
                        criteriaBuilder.equal(root.get(searchCondition.getFieldName()).as(field.getType()),field.getType().cast(searchCondition.getValue()));
                        break;
                    case IN: break;
                    case LIKE: break;
                    case GT: break;
                    case LT: break;
                }
            } catch (NoSuchFieldException e) {
                System.out.println("该字段不存在");
                return null;
            }
        }

        return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
    }
}
