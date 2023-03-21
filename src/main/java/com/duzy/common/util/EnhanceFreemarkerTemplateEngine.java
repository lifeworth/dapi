package com.duzy.common.util;

import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.builder.CustomFile;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

/**
 * @author zhiyuandu
 * @since 2023/3/21 11:20
 * @description
 */
@Slf4j
public class EnhanceFreemarkerTemplateEngine extends FreemarkerTemplateEngine {

    @Override
    protected void outputCustomFile(@NotNull List<CustomFile> customFiles, @NotNull TableInfo tableInfo, @NotNull Map<String, Object> objectMap) {
        String entityName = tableInfo.getEntityName();
        customFiles.forEach((file) -> {
            String dtoFileName = file.getFilePath() + "/" + file.getPackageName().replaceAll("\\.", "/") + "/" + entityName.replace("Model", "") + file.getFileName();
            String templatePath = file.getTemplatePath();
            this.outputFile(new File(dtoFileName), objectMap, templatePath, true);
        });
    }
}
