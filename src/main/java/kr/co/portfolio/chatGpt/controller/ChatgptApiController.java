package kr.co.portfolio.chatGpt.controller;

import kr.co.portfolio.chatGpt.dto.ChatReq;
import kr.co.portfolio.chatGpt.dto.ResponseModel;
import kr.co.portfolio.chatGpt.service.ChatgptService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping("/chatgpt/api")
@RequiredArgsConstructor
public class ChatgptApiController {

    private final ChatgptService chatgptService;

    @PostMapping("/send")
    public ResponseModel sendMessage(HttpServletRequest req, @RequestBody ChatReq chatReq){

        String requestId = UUID.randomUUID().toString();
        log.info("requestId : {}, ip : {}, send a message : {}", requestId, req.getRemoteHost(), chatReq.getPrompt());

        if(!StringUtils.hasText(chatReq.getPrompt())){
            log.error("message can not be blank");
            return ResponseModel.fail("message can not be blank");
        }

        try {
            String responseMessage =  chatgptService.sendMessage(chatReq.getPrompt());
            log.info("requestId : {}, ip : {}, get a reply : {}", requestId, req.getRemoteHost(), responseMessage);
            return ResponseModel.success(responseMessage);
        } catch (Exception e) {
            log.error("requestId : {}, ip : {}, error", requestId, req.getRemoteHost(), e);
            return new ResponseModel(500, "error", e.getMessage());
        }
    }
}
