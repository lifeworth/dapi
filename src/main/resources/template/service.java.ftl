package ${package.Service};

import ${package.Entity}.${entity};
import ${superServiceClassPackage};

import ${DtoPackage}.${entityDto};
import ${VoPackage}.${entityVo};

/**
 * <p>
 * ${table.comment!} 服务类
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
public interface ${table.serviceName} extends ${superServiceClass}<${entity},${entityVo},${entityDto}> {

}
