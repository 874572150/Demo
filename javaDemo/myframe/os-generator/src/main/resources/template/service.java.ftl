package ${package.Service};

import ${package.Entity}.${entity};
import ${superServiceClassPackage};

import com.siemens.ems.common.base.dto.PageResult;
import com.siemens.ems.common.base.dto.ResultWrapper;
import ${package.DTO}.${entity}PageDTO;
import ${package.DTO}.${entity}DTO;
import ${package.VO}.${entity}VO;

/**
 * <p>
 * ${table.comment!} 服务类
 * </p>
 *
 * @author ${author}
 * @date ${date}
 */
<#if kotlin>
interface ${table.serviceName} : ${superServiceClass}<${entity}>
<#else>
public interface ${table.serviceName} extends ${superServiceClass}<${entity}> {

  ResultWrapper<PageResult<${entity}VO>> pageList${entity}(${entity}PageDTO pageDTO);

  ResultWrapper<Integer> doSave(${entity}DTO dto);

  ResultWrapper<Integer> doDelete(Long id);

  ResultWrapper<Integer> doUpdate(${entity}DTO dto);

  ResultWrapper<${entity}VO> detail(Long id);
}
</#if>
