${entity}DTO.id.NotNull = id不能为空
<#list table.fields as field>
${entity}DTO.${field.propertyName}.NotNull = ${field.comment}不能为空
</#list>