package ${package.Controller};

import com.siemens.ems.common.base.dto.PageResult;
import com.siemens.ems.common.base.dto.ResultWrapper;
import com.siemens.ems.common.base.validateGroup.CreateGroup;
import com.siemens.ems.common.base.validateGroup.PageGroup;
import com.siemens.ems.common.base.validateGroup.UpdateGroup;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.annotation.Validated;

<#--import ${package.Entity}.${entity};-->
import ${package.Service}.${table.serviceName};
<#--import ${package.DTO}.${entity}PageDTO;-->
import ${package.DTO}.${entity}DTO;
import ${package.VO}.${entity}VO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.annotation.Resource;
import java.util.List;
<#if restControllerStyle>
import org.springframework.web.bind.annotation.RestController;
<#else>
import org.springframework.stereotype.Controller;
</#if>
<#if superControllerClassPackage??>
import ${superControllerClassPackage};
</#if>

/**
 *
 * @author ${author}
 * @date ${date}
 * @description ${table.comment!} 前端控制器
 */
<#if swagger2>
@Api(value = "${table.comment!} 控制器", tags = "${table.comment!} 控制器")
</#if>
<#if restControllerStyle>
@RestController
<#else>
@Controller
</#if>
@RequestMapping("V1/<#--<#if package.ModuleName??>/${package.ModuleName}</#if>/--><#if controllerMappingHyphenStyle??>${controllerMappingHyphen}<#else>${table.entityPath}</#if>")
<#if kotlin>
class ${table.controllerName}<#if superControllerClass??> : ${superControllerClass}()</#if>
<#else>
<#if superControllerClass??>
public class ${table.controllerName} extends ${superControllerClass} {
<#else>
public class ${table.controllerName} {
</#if>

    @Resource
    private ${table.serviceName} ${table.serviceName ? substring(1) ? uncap_first};

    /**
     * 分页查询
     *
     * @param dto 分页实体
     * @return 分页数据
     */
    @ApiOperation(value = "分页查询")
    @GetMapping("/page")
    public ResultWrapper<PageResult<${entity}VO>> page(@Validated({PageGroup.class}) ${entity}DTO dto) {
        return ${table.serviceName ? substring(1) ? uncap_first}.doPage(dto);
    }

    /**
     * 新增
     *
     * @param dto 新增实体
     * @return 处理结果
     */
    @ApiOperation(value = "新增")
    @PostMapping
    public ResultWrapper<Integer> save(@Validated({CreateGroup.class}) @RequestBody ${entity}DTO dto) {
        return ${table.serviceName ? substring(1) ? uncap_first}.doSave(dto);
    }

    /**
     * 更新
     *
     * @param dto 更新实体
     * @return 处理结果
     */
    @ApiOperation(value = "更新")
    @PutMapping
    public ResultWrapper<Integer> update(@Validated({UpdateGroup.class}) @RequestBody ${entity}DTO dto) {
        return ${table.serviceName ? substring(1) ? uncap_first}.doUpdate(dto);
    }

    /**
     * 删除
     *
     * @param id id
     * @return 处理结果
     */
    @ApiOperation(value = "删除")
    @DeleteMapping("/{id}")
    public ResultWrapper<Integer> delete(@PathVariable("id") Long id) {
        return ${table.serviceName ? substring(1) ? uncap_first}.doDelete(id);
    }

    /**
     * 批量删除
     *
     * @param ids id集合
     * @return 处理结果
     */
    @ApiOperation(value = "批量删除")
    @DeleteMapping("/batch")
    public ResultWrapper<Integer> doDeleteBatch(@RequestBody List<Long> ids) {
        return ${table.serviceName ? substring(1) ? uncap_first}.doDeleteBatch(ids);
    }

    /**
     * 查询详情
     *
     * @param id id
     * @return 实体VO
     */
    @ApiOperation(value = "详情")
    @GetMapping("/{id}")
    public ResultWrapper<${entity}VO> detail(@PathVariable("id") Long id) {
        return ${table.serviceName ? substring(1) ? uncap_first}.detail(id);
    }
}
</#if>
