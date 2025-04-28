package com.duzy.common.util;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.builder.CustomFile;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.duzy.controller.CustomerController;
import com.duzy.model.CustomerModel;
import com.duzy.service.CustomerService;
import com.duzy.service.impl.CustomerServiceImpl;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author zhiyuandu
 * @date 2021/12/16-14:02
 * @description 根据表生成java类
 **/
public class GenerateDb {
    public static void main(String[] args) {
        String url = "jdbc:mysql://101.200.180.203:13306/du?useUnicode=true&characterEncoding=utf-8&allowMultiQueries" +
                "=true&useSSL=false&zeroDateTimeBehavior=convertToNull&useAffectedRows=true";
        String username = "root";
        String password = "ffWrxsWpW5M6DCLF";
        String outPutDir = "F:\\IdeaProjects\\dapi\\src\\main\\java";
        String packageName = "com.duzy.generate";
        String mapperXmlPath = "F:\\IdeaProjects\\dapi\\src\\main\\resources\\mapper";

        FastAutoGenerator.create(url, username, password).globalConfig(builder -> {
                    builder.author("zhiyuandu") // 设置作者
                            .enableSwagger() // 开启 swagger 模式
                            .disableOpenDir()
                            .outputDir(outPutDir); // 指定输出目录
                }).packageConfig(builder -> {
                    builder.parent(packageName) // 设置父包名
                            .moduleName("") // 设置父包模块名
                            .entity("model")
                            .mapper("dao")
                            .service("service")
                            .serviceImpl("serviceImpl")
                            .controller("controller")
                            .pathInfo(Collections.singletonMap(OutputFile.xml, mapperXmlPath)); // 设置mapperXml生成路径
                }).strategyConfig(builder -> {
                    builder.entityBuilder()
                            .disableSerialVersionUID()
                            .enableLombok()
                            .enableTableFieldAnnotation()
                            .naming(NamingStrategy.underline_to_camel)
                            .columnNaming(NamingStrategy.underline_to_camel)
                            .formatFileName("%sModel")
                            .enableFileOverride()
                            .logicDeleteColumnName("is_delete")
                            .logicDeletePropertyName("is_delete")
                            .addSuperEntityColumns("id", "created_by", "created_time", "updated_by", "updated_time", "is_delete")
                            .superClass(CustomerModel.class)
                            .idType(IdType.AUTO).build();
                    builder.mapperBuilder()
                            .enableFileOverride()
                            .enableBaseColumnList()
                            .enableBaseResultMap()
                            .mapperAnnotation(Repository.class)
                            .formatMapperFileName("%sDao")
                            .formatXmlFileName("%sXml")
                            .build();
                    builder.serviceBuilder()
                            .enableFileOverride()
                            .superServiceClass(CustomerService.class)
                            .superServiceImplClass(CustomerServiceImpl.class)
                            .formatServiceFileName("%sService")
                            .formatServiceImplFileName("%sServiceImpl")
                            .build();
                    builder.controllerBuilder()
                            .enableFileOverride()
                            .enableRestStyle()
                            .superClass(CustomerController.class)
                            .build();
                    builder.addInclude("worker_bill"); // 设置过滤表前缀
                })
                .injectionConfig(consumer -> {
                    AtomicReference<String> entityName = new AtomicReference<>("");
                    CustomFile dtoFile = new CustomFile.Builder()
                            .fileName("Dto.java")
                            .filePath(outPutDir)
                            .packageName(packageName + ".dto")
                            .templatePath("template/dto.java.ftl")
                            .build();
                    CustomFile voFile = new CustomFile.Builder()
                            .fileName("Vo.java")
                            .filePath(outPutDir)
                            .packageName(packageName + ".vo")
                            .templatePath("template/vo.java.ftl")
                            .build();
                    CustomFile queryVoFile = new CustomFile.Builder()
                            .fileName("QueryDto.java")
                            .filePath(outPutDir)
                            .packageName(packageName + ".queryDto")
                            .templatePath("template/queryDto.java.ftl")
                            .build();
                    CustomFile convertorFile = new CustomFile.Builder()
                            .fileName("Convertor.java")
                            .filePath(outPutDir)
                            .packageName(packageName + ".convertor")
                            .templatePath("template/convertor.java.ftl")
                            .build();
                    consumer
                            .customFile(dtoFile)
                            .customFile(voFile)
                            .customFile(queryVoFile)
                            .customFile(convertorFile)
                            .beforeOutputFile((tableInfo, objectMap) -> {
                                Map<String, Object> packageMap = (Map<String, Object>) objectMap.get("package");
                                String entity = (String) packageMap.get("Entity");

                                String dtoPackage = entity.replace("model", "dto");
                                String voPackage = entity.replace("model", "vo");
                                String queryDtoPackage = entity.replace("model", "queryDto");
                                String convertorPackage = entity.replace("model", "convertor");
                                entityName.set(tableInfo.getEntityName().replace("Model", ""));
                                objectMap.put("entityVo", StrUtil.format("{}Vo", entityName.get()));
                                objectMap.put("entityDto", StrUtil.format("{}Dto", entityName.get()));
                                objectMap.put("entityQueryDto", StrUtil.format("{}QueryDto", entityName.get()));
                                objectMap.put("entityConvertor", StrUtil.format("{}Convertor", entityName.get()));
                                objectMap.put("DtoPackage", dtoPackage);
                                objectMap.put("VoPackage", voPackage);
                                objectMap.put("QueryDtoPackage", queryDtoPackage);
                                objectMap.put("ConvertorPackage", convertorPackage);
                            })
                            .build();

                })
                // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .templateConfig(builder -> builder
                        .controller("/template/controller.java")
                        .entity("/template/entity.java")
                        .mapper("/template/mapper.java")
                        .xml("/template/mapper.xml")
                        .service("/template/service.java")
                        .serviceImpl("/template/serviceImpl.java")
                        .build())
                .templateEngine(new EnhanceFreemarkerTemplateEngine())
                .execute();
    }


}
