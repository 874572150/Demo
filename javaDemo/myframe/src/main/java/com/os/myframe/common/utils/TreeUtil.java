package com.os.myframe.common.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author oushuo
 * @description 封装成树形结构工具类
 * @date 2020/09/26
 */
public class TreeUtil {
    /**
     * 将数据集合转成树形结构
     * @param dataCollection 数据集合
     * @param rootCondition 根节点条件
     * @param parentAttr 父节点属性名
     * @param referenceAttr 子节点中关联父节点的属性名
     * @param childrenAttr 子节点属性名
     * @param objectCls 对象cls
     * @param <T> 集合类型
     * @param <M> 对象类型
     * @return 树形集合
     */
    public static <T extends Collection, M> T dataToTree(T dataCollection, Map<String, Object> rootCondition, String parentAttr, String referenceAttr, String childrenAttr, Class<M> objectCls) {
        if (dataCollection == null) {
            return null;
        }
        JSONArray rootArray = new JSONArray();
        JSONArray dataJsonArray = JSONArray.parseArray(JSON.toJSONString(dataCollection));
        dataJsonArray.forEach(data -> {
            JSONObject dataJson = JSONObject.parseObject(JSON.toJSONString(data));
            if (isRoot(dataJson, rootCondition)) {
                rootArray.add(data);
            }
        });
        getTreeNode(rootArray, dataJsonArray, parentAttr, referenceAttr,childrenAttr);
        return (T) JSONObject.parseArray(JSON.toJSONString(rootArray), objectCls);
    }

    /**
     * 获取根节点下的子节点
     *
     * @param parentArray
     * @param dataJsonArray
     * @param parentAttr
     * @param referenceAttr
     */
    private static void getTreeNode(JSONArray parentArray, JSONArray dataJsonArray, String parentAttr, String referenceAttr, String childrenAttr) {
        removeParentNode(parentArray, dataJsonArray);
        if (dataJsonArray.size() == 0) {
            return;
        }
        Map<String, Integer> parentsMap = new HashMap<>();
        for (Object obj : parentArray) {
            JSONObject parentObject = JSONObject.parseObject(JSON.toJSONString(obj));
            parentsMap.put(parentObject.getString(parentAttr), parentArray.indexOf(obj));
        }
        Set<String> parentKey = parentsMap.keySet();
        JSONArray childrenArrays = new JSONArray();
        for (Object obj : dataJsonArray) {
            JSONObject node = JSONObject.parseObject(JSON.toJSONString(obj));
            String referenceValue = node.getString(referenceAttr);
            if (parentKey.contains(referenceValue)) {
                childrenArrays.add(obj);
                JSONArray childrenArray = parentArray.getJSONObject(parentsMap.get(referenceValue)).getJSONArray(childrenAttr);
                if (childrenArray == null) {
                    childrenArray = new JSONArray();
                    childrenArray.add(obj);
                    parentArray.getJSONObject(parentsMap.get(referenceValue)).put(childrenAttr,childrenArray);
                } else {
                    childrenArray.add(obj);
                }
            }
        }
        getTreeNode(childrenArrays, dataJsonArray, parentAttr, referenceAttr,childrenAttr);
    }

    /**
     * 移除父级节点
     * @param parentArray
     * @param dataJsonArray
     */
    private static void removeParentNode(JSONArray parentArray, JSONArray dataJsonArray) {
        for (Object obj : parentArray) {
            dataJsonArray.remove(obj);
        }
    }

    /**
     * 判断当前是否为根节点
     *
     * @param data
     * @param rootCondition
     * @return
     */
    private static boolean isRoot(JSONObject data, Map<String, Object> rootCondition) {
        for (String key : rootCondition.keySet()) {
            if (!rootCondition.get(key).toString().equals(data.getString(key))) {
                return false;
            }
        }
        return true;
    }
}
