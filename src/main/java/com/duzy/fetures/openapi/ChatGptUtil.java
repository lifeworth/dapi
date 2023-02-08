package com.duzy.fetures.openapi;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import com.theokanning.openai.OpenAiService;
import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.completion.CompletionResult;
import com.theokanning.openai.model.Model;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author zhiyuandu
 * @since 2023/2/4 18:22
 * @description
 */
@Component
public class ChatGptUtil {
    public static void main(String[] args) {
        OpenAiService service = new OpenAiService("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");
        CompletionRequest completionRequest = CompletionRequest.builder()
                .prompt("推荐三本关于炒股的书籍")
                .model("text-davinci-003")
                .temperature(0.5)
                .maxTokens(100)
                .topP(1.0)
                .frequencyPenalty(0.0)
                .presencePenalty(0.0)
                .echo(true)
                .build();

        CompletionResult completion = service.createCompletion(completionRequest);
        System.out.println(completion);
    }
}
