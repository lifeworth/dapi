package com.duzy.tinify;

import cn.hutool.core.io.FileUtil;
import com.tinify.Source;
import com.tinify.Tinify;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;

/**
 * @author zhiyuandu
 * @since 2022/8/25 11:28
 * @description
 */
@Slf4j
@Component
public class TinifyHandler {
    @Value("${tinypng.apikey}")
    String key;

    @PostConstruct
    public void init() {
        try {
            Tinify.setKey(key);
            Tinify.validate();
            int compressionsThisMonth = Tinify.compressionCount();
            log.info("Tinify key compressionsThisMonth count:{}", compressionsThisMonth);
        } catch (java.lang.Exception e) {
            log.error("Tinify key invalid.", e);
        }
    }

    /**
     * Compressing
     */
    public void compressing() throws IOException {
        //from url
        Source source = Tinify.fromUrl(
                "https://c-ssl.dtstatic.com/uploads/blog/202101/23/20210123215342_3bbf3.thumb.1000_0.jpeg");
        source.toFile("afterCompressing-url.png");
        Source copyrighted = source.preserve("copyright", "creation");
        copyrighted.toFile("optimized-copyright.jpg");

        //from file
        File file = ResourceUtils.getFile("classpath:image/demo.jpg");
        File newFile = new File("classpath:image/file.jpg");
        byte[] bytes = FileUtil.readBytes(file);
        byte[] resultData = Tinify.fromBuffer(bytes).toBuffer();
        FileUtil.writeBytes(resultData, newFile);

    }


}
