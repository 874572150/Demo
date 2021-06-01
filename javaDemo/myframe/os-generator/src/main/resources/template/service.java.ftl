package ${package.Service};

import ${package.Entity}.${entity};
import ${superServiceClassPackage};

import com.siemens.ems.common.base.dto.PageResult;
import com.siemens.ems.common.base.dto.ResultWrapper;
import ${package.DTO}.${entity}DTO;
import ${package.VO}.${entity}VO;

import java.util.List;

/**
 *
 * @author ${author}
 * @date ${date}
 * @description ${table.comment!} 业务层接口
 */
<#if kotlin>
interface ${table.serviceName} : ${superServiceClass}<${entity}>
<#else>
public interface ${table.serviceName} extends ${superServiceClass}<${entity}> {

    /**
     * 分页查询
     *
     * @param dto 分页实体
     * @return 分页数据
     */
    ResultWrapper<PageResult<${entity}VO>> doPage(${entity}DTO dto);

    /**
     * 新增
     *
     * @param dto 新增实体
     * @return 处理结果
     */
    ResultWrapper<Integer> doSave(${entity}DTO dto);

    /**
     * 更新
     *
     * @param dto 更新实体
     * @return 处理结果
     */
    ResultWrapper<Integer> doUpdate(${entity}DTO dto);

    /**
     * 删除
     *
     * @param id id
     * @return 处理结果
     */
    ResultWrapper<Integer> doDelete(Long id);

    /**
     * 批量删除
     *
     * @param ids id集合
     * @return 处理结果
     */
    ResultWrapper<Integer> doDeleteBatch(List<Long> ids);

    /**
     * 查询详情
     *
     * @param id id
     * @return 实体VO
     */
    ResultWrapper<${entity}VO> detail(Long id);

}
</#if>
