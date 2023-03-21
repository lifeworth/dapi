package ${ConvertorPackage};

import com.duzy.converter.CustomerConverter;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;
import ${package.Entity}.${entity};
import ${DtoPackage}.${entityDto};
import ${VoPackage}.${entityVo};
/**
 * <p>
 * ${table.comment!} 转换器
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
@Component
public interface ${entityConvertor} extends CustomerConverter<${entityDto},${entity},${entityVo}> {

}
