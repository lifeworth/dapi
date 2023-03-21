package ${package.Controller};

import org.springframework.web.bind.annotation.RequestMapping;
import ${superControllerClassPackage};
import org.springframework.web.bind.annotation.RestController;

import ${package.Entity}.${entity};
import ${DtoPackage}.${entityDto};
import ${VoPackage}.${entityVo};
import ${QueryDtoPackage}.${entityQueryDto};
import io.swagger.v3.oas.annotations.tags.Tag;
/**
 * <p>
 * ${table.comment!} 前端控制器
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
@RestController
@RequestMapping("<#if package.ModuleName?? && package.ModuleName != "">/${package.ModuleName}</#if>/<#if controllerMappingHyphenStyle>${controllerMappingHyphen}<#else>${table.entityPath}</#if>")
@Tag(name = "${table.comment}")
public class ${table.controllerName} extends ${superControllerClass}<${entity},${entityVo},${entityDto},${entityQueryDto}> {
}
