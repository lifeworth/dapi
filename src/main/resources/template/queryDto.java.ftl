package ${QueryDtoPackage};

import io.swagger.v3.oas.annotations.media.Schema;
import com.duzy.dto.BaseQueryDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
/**
 * <p>
 * ${table.comment!}
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Schema(name = "${entityQueryDto}")
public class ${entityQueryDto} extends BaseQueryDTO{

<#-- ----------  BEGIN 字段循环遍历  ---------->
<#list table.fields as field>
    <#if field.keyFlag>
        <#assign keyPropertyName="${field.propertyName}"/>
    </#if>
    @Schema(description = "${field.comment}")
        <#-- 普通字段 -->
    private ${field.propertyType} ${field.propertyName};
</#list>
}
