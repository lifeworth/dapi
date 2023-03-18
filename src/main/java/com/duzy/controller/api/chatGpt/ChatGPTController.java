package com.duzy.controller.api.chatGpt;

import com.duzy.service.ChatGPTService;
import com.duzy.vo.ResultVO;
import com.theokanning.openai.model.Model;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * @author zhiyuandu
 * @since 2023/1/7 16:39
 * @description
 */
@RestController
@RequestMapping("/api/chatGPT")
@Tag(name = "chatGPT")
public class ChatGPTController {
    @Autowired
    ChatGPTService chatGPTService;

    @GetMapping
    @Operation(summary = "查询models", description = "查询models")
    public ResultVO<List<Model>> models() {
        List<Model> result = chatGPTService.models();
        return ResultVO.success(result);
    }

    @PostMapping
    @Operation(summary = "回答问题", description = "回答问题")
    public ResultVO<String> qa(String question) {
        String result = chatGPTService.qa(question);
        return ResultVO.success(result);
    }
}
