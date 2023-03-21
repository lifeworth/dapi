package ${VoPackage};

import io.swagger.v3.oas.annotations.media.Schema;
import com.duzy.vo.CustomerVo;
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
@Schema(name = "${entityVo}")
@EqualsAndHashCode(callSuper = false)
public class ${entityVo} extends CustomerVo{

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
