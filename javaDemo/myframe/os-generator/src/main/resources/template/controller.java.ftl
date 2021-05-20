package ${package.Controller};

import com.siemens.ems.common.base.dto.PageResult;
import com.siemens.ems.common.base.dto.ResultWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.annotation.Validated;

import ${package.Entity}.${entity};
import ${package.Service}.${table.serviceName};
import ${package.DTO}.${entity}PageDTO;
import ${package.DTO}.${entity}DTO;
import ${package.VO}.${entity}VO;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

<#if restControllerStyle>
import org.springframework.web.bind.annotation.RestController;
<#else>
import org.springframework.stereotype.Controller;
</#if>
<#if superControllerClassPackage??>
import ${superControllerClassPackage};
</#if>

/**
 * <p>
 * ${table.comment!} 前端控制器
 * </p>
 *
 * @author ${author}
 * @date ${date}
 */
<#if swagger2>
@Api(value = "${table.comment!} 控制器",tags = "${table.comment!} 控制器")
</#if>
<#if restControllerStyle>
@RestController
<#else>
@Controller
</#if>
@RequestMapping("<#if package.ModuleName??>/${package.ModuleName}</#if>/<#if controllerMappingHyphenStyle??>${controllerMappingHyphen}<#else>${table.entityPath}</#if>")
@Validated
<#if kotlin>
class ${table.controllerName}<#if superControllerClass??> : ${superControllerClass}()</#if>
<#else>
<#if superControllerClass??>
public class ${table.controllerName} extends ${superControllerClass} {
<#else>
public class ${table.controllerName} {
</#if>


    @Autowired
    private ${table.serviceName} ${table.serviceName ? substring(1) ? uncap_first};


    @ApiOperation(value = "分页查询${table.comment!}")
    @PostMapping("/list")
    public ResultWrapper<PageResult<${entity}VO>> pageList${entity}(@Valid @RequestBody ${entity}PageDTO pageDto){
        return ${table.serviceName ? substring(1) ? uncap_first}.pageList${entity}(pageDto);
    }


    @ApiOperation(value = "添加${table.comment!}")
    @PostMapping("/add")
    public ResultWrapper<Integer> add${entity}(@Valid @RequestBody ${entity}DTO dto){
        return ${table.serviceName ? substring(1) ? uncap_first}.doSave(dto);
    }


    @ApiOperation(value = "删除${table.comment!}")
    @ApiParam(name = "${table.comment!}ID",value = "${table.comment!}ID",required = true)
    @GetMapping("/delete/{id}")
    public ResultWrapper<Integer> delete${entity}(@PathVariable("id") Long id){
        return ${table.serviceName ? substring(1) ? uncap_first}.doDelete(id);
    }


    @ApiOperation(value = "更新${table.comment!}")
    @PostMapping("/update")
    public ResultWrapper<Integer> update${entity}(@Valid @RequestBody ${entity}DTO dto){
        return ${table.serviceName ? substring(1) ? uncap_first}.doUpdate(dto);
    }

    @ApiOperation(value = "查询${table.comment!}详情")
    @ApiParam(name = "${table.comment!}ID",value = "${table.comment!}ID",required = true)
    @GetMapping("/detail/{id}")
    public ResultWrapper<${entity}VO> detail(@PathVariable("id") Long id){
        return ${table.serviceName ? substring(1) ? uncap_first}.detail(id);
    }
}
</#if>
