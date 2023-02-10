package com.duzy.service.impl;

import cn.hutool.json.JSONUtil;
import com.duzy.service.ChatGPTService;
import com.theokanning.openai.OpenAiService;
import com.theokanning.openai.completion.CompletionChoice;
import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.model.Model;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhiyuandu
 * @since 2023/2/4 19:52
 * @description
 */
@Service
@Slf4j
public class ChatGPTServiceImpl implements ChatGPTService {

    @Value("${chat-token}")
    String token;

    /**
     * 获取模型
     * @return
     */
    @Override
    public List<Model> models() {
        return null;
    }

    @Override
    public String qa(String question) {
        OpenAiService service = new OpenAiService(token);
        CompletionRequest completionRequest = CompletionRequest.builder()
                .prompt(question)
                .model("ada")
                .echo(true)
                .build();
        List<CompletionChoice> choices = service.createCompletion(completionRequest).getChoices();
        log.info("收到:{},回答:{},", question, choices);
        return JSONUtil.toJsonStr(choices);
    }
}
