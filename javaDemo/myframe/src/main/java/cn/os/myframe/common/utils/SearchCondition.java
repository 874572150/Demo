package cn.os.myframe.common.utils;

import lombok.Getter;
import lombok.Setter;

/**
 * @author oushuo
 * @description 搜索条件集合
 * @date 2020/10/29
 */
@Setter
@Getter
public class SearchCondition {
    public enum OprEnum {
        LIKE, IN, EQ, GT, LT, GTE, LTE
    }

    public enum ConnectorEnum {
        AND,OR
    }

    private String fieldName;

    private OprEnum opr;

    private Object value;

    private SearchCondition relSearchCondition;

    private ConnectorEnum connector;

    public SearchCondition(String fieldName, OprEnum opr, Object value) {
        this.fieldName = fieldName;
        this.opr = opr;
        this.value = value;
    }

    public SearchCondition or(SearchCondition searchCondition) {
        searchCondition.setConnector(ConnectorEnum.OR);
        this.setRelSearchCondition(searchCondition);
        return this;
    }

    public SearchCondition and(SearchCondition searchCondition) {
        searchCondition.setConnector(ConnectorEnum.AND);
        this.setRelSearchCondition(searchCondition);
        return this;
    }
}
