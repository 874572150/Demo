package ${package.ServiceImpl};

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.siemens.ems.common.base.constant.SysConstant;
import com.siemens.ems.common.base.enums.StateCodeEnum;
import com.siemens.ems.common.util.BeanUtils;
import com.siemens.ems.common.util.SortUtil;
import ${package.Entity}.${entity};
import ${package.Mapper}.${table.mapperName};
import ${package.Service}.${table.serviceName};
import ${superServiceImplClassPackage};
import org.springframework.stereotype.Service;

import com.siemens.ems.common.base.dto.PageResult;
import com.siemens.ems.common.base.dto.ResultWrapper;
<#--import ${package.DTO}.${entity}PageDTO;-->
import ${package.DTO}.${entity}DTO;
import ${package.VO}.${entity}VO;

import java.util.List;

/**
 * <p>
 * ${table.comment!} 服务实现类
 * </p>
 *
 * @author ${author}
 * @date ${date}
 * @description ${table.comment!} 业务层实现类
 */
@Service
<#if kotlin>
open class ${table.serviceImplName} : ${superServiceImplClass}<${table.mapperName}, ${entity}>(), ${table.serviceName} {

}
<#else>
public class ${table.serviceImplName} extends ${superServiceImplClass}<${table.mapperName}, ${entity}> implements ${table.serviceName} {

    /**
     * 分页查询
     *
     * @param dto 分页实体
     * @return 分页数据
     */
    @Override
    public ResultWrapper<PageResult<${entity}VO>> doPage(${entity}DTO dto) {
        // 查询条件
        QueryWrapper<${entity}> queryWrapper = SortUtil.buildSortQueryWrapper(dto);
        // 分页查询
        Page<${entity}> page = new Page<>(dto.getPageIndex(), dto.getLength());
        IPage<${entity}> iPage = baseMapper.selectPage(page, queryWrapper);
        // entity集合
        List<${entity}> ${entity ? substring(1) ? uncap_first}s = iPage.getRecords();
        // entity转vo
        List<${entity}VO> voList = BeanUtils.copyListBean(${entity ? substring(1) ? uncap_first}s, ${entity}VO.class);
        // 响应数据
        PageResult<${entity}VO> result = new PageResult<>();
        result.setContent(voList);
        result.setTotalPages(iPage.getPages());
        result.setTotalElements(iPage.getTotal());
        return ResultWrapper.success(result);
    }

    /**
     * 新增
     *
     * @param dto DTO
     * @return 处理结果
     */
    @Override
    public ResultWrapper<Integer> doSave(${entity}DTO dto) {
        ${entity} ${entity ? substring(1) ? uncap_first} = BeanUtils.copyBeanNoException(dto, ${entity}.class);
        baseMapper.insert(${entity ? substring(1) ? uncap_first});
        return ResultWrapper.success(StateCodeEnum.INSERT_SUCCESS.getMsg());
    }

    /**
     * 更新
     *
     * @param dto DTO
     * @return 处理结果
     */
    @Override
    public ResultWrapper<Integer> doUpdate(${entity}DTO dto) {
        ${entity} ${entity ? substring(1) ? uncap_first} = BeanUtils.copyBeanNoException(dto, ${entity}.class);
        baseMapper.updateById(${entity ? substring(1) ? uncap_first});
        return ResultWrapper.success(StateCodeEnum.UPDATE_SUCCESS.getMsg());
    }

    /**
     * 删除
     *
     * @param id id
     * @return 处理结果
     */
    @Override
    public ResultWrapper<Integer> doDelete(Long id) {
        ${entity} ${entity ? substring(1) ? uncap_first} = baseMapper.selectById(id);
        ${entity ? substring(1) ? uncap_first}.setIsDelete(SysConstant.IS_DELETE_1);
        baseMapper.updateById(${entity ? substring(1) ? uncap_first});
        return ResultWrapper.success(StateCodeEnum.DELETE_SUCCESS.getMsg());
    }

    /**
     * 批量删除
     *
     * @param ids id集合
     * @return 处理结果
     */
    @Override
    public ResultWrapper<Integer> doDeleteBatch(List<Long> ids) {
        List<${entity}> ${entity ? substring(1) ? uncap_first}List = baseMapper.selectBatchIds(ids);
        for (${entity} ${entity ? substring(1) ? uncap_first} : ${entity ? substring(1) ? uncap_first}List) {
            ${entity ? substring(1) ? uncap_first}.setIsDelete(SysConstant.IS_DELETE_1);
            baseMapper.updateById(${entity ? substring(1) ? uncap_first});
        }
        return ResultWrapper.success(StateCodeEnum.DELETE_SUCCESS.getMsg());
    }

    /**
     * 查询详情
     *
     * @param id id
     * @return 实体VO
     */
    @Override
    public ResultWrapper<${entity}VO> detail(Long id) {
        ${entity} ${entity ? substring(1) ? uncap_first} = baseMapper.selectById(id);
        ${entity}VO ${entity ? substring(1) ? uncap_first}VO = BeanUtils.copyBeanNoException(${entity ? substring(1) ? uncap_first}, ${entity}VO.class);
        return ResultWrapper.success(${entity ? substring(1) ? uncap_first}VO);
    }
}
</#if>
