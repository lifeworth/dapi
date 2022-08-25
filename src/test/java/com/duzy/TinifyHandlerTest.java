package com.duzy;

import com.duzy.tinify.TinifyHandler;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

/**
 * @author zhiyuandu
 * @since 2022/8/25 13:30
 * @description
 */
public class TinifyHandlerTest extends BaseSpringTest {
    @Autowired
    TinifyHandler tinifyHandler;

    @Test
    public void testTinifyHandler() throws IOException {
        tinifyHandler.compressing();
    }
}
