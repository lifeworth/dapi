package com.duzy.util;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.Collections;

/**
 * @author zhiyuandu
 * @date 2021/12/16-14:02
 * @description 根据表生成java类
 **/
public class GenerateDb {
    public static void main(String[] args) {
        String url = "jdbc:mysql://roadss.mysql.database.azure.com/dapi?useUnicode=true&characterEncoding=utf-8&allowMultiQueries" +
                "=true&useSSL=false&zeroDateTimeBehavior=convertToNull&useAffectedRows=true";
        String username = "roadss_com";
        String password = "Duzhiyuan123";
        String outPutDir = "/Users/zhiyuandu/Documents/GitHub/dapi/src/main/java";
        String packageName = "com.duzy.generate";
        String mapperXmlPath = "/Users/zhiyuandu/Documents/GitHub/dapi/src/main/resources/mapper";
        FastAutoGenerator.create(url, username, password).globalConfig(builder -> {
                    builder.author("zhiyuandu") // 设置作者
                            .enableSwagger() // 开启 swagger 模式
                            .disableOpenDir().outputDir(outPutDir); // 指定输出目录
                }).packageConfig(builder -> {
                    builder.parent(packageName) // 设置父包名
                            .moduleName("") // 设置父包模块名
                            .entity("model").mapper("dao").service("service").serviceImpl("serviceImpl").controller(
                                    "controller").pathInfo(Collections.singletonMap(OutputFile.xml, mapperXmlPath)); // 设置mapperXml生成路径
                }).strategyConfig(builder -> {
                    builder.entityBuilder().disableSerialVersionUID().enableLombok().enableTableFieldAnnotation().naming(NamingStrategy.underline_to_camel).columnNaming(NamingStrategy.underline_to_camel).formatFileName("%sModel")
//                            .addTableFills(new Column("create_by", FieldFill.INSERT))
//                            .addTableFills(new Column("create_time", FieldFill.INSERT))
//                            .addTableFills(new Column("update_by", FieldFill.INSERT_UPDATE))
//                            .addTableFills(new Column("update_time", FieldFill.INSERT_UPDATE))
//                            .logicDeleteColumnName("is_delete")
//                            .logicDeletePropertyName("isDelete")
                            .enableFileOverride()
                            .idType(IdType.AUTO).build();
                    builder.mapperBuilder().enableFileOverride().enableBaseColumnList().enableBaseResultMap().enableMapperAnnotation().formatMapperFileName("%sDao").formatXmlFileName("%sXml").build();
                    builder.serviceBuilder().enableFileOverride().formatServiceFileName("%sService").formatServiceImplFileName("%sServiceImpl").build();
                    builder.controllerBuilder().enableFileOverride().enableRestStyle().build();
                    builder.addInclude("movie"); // 设置过滤表前缀
                })
//                .templateEngine(new VelocityTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }
}
