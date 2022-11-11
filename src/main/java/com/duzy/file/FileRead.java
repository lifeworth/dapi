package com.duzy.file;

import cn.hutool.core.io.file.FileReader;
import cn.hutool.core.util.ReUtil;
import cn.hutool.core.util.StrUtil;
import com.google.common.base.Strings;

import java.util.List;

/**
 * @author zhiyuandu
 * @description
 * @since 2022/10/31 16:16
 */
public class FileRead {
    public static void main(String[] args) {
        FileReader fileReader = new FileReader("/Users/zhiyuandu/Desktop/secure");
        List<String> strings = fileReader.readLines();
        strings.forEach(line -> {
            String s = ReUtil.get("(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)", line, 0);
            String sqlTemplate = "";
            if (!Strings.isNullOrEmpty(s)) {
                sqlTemplate = StrUtil.format("insert into ssh_log (ip,source) values ({},{})", s, line);
            } else {
                sqlTemplate = StrUtil.format("insert into ssh_log (source) values ({})", line);
            }


        });
    }
}
