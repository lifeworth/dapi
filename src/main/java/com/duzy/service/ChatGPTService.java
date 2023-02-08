package com.duzy.service;

import com.theokanning.openai.model.Model;

import java.util.List;

/**
 * @author zhiyuandu
 * @since 2023/2/4 19:52
 * @description
 */
public interface ChatGPTService {
    List<Model> models();

    String qa(String question);
}
