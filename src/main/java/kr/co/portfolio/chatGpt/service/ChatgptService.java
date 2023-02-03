package kr.co.portfolio.chatGpt.service;

import kr.co.portfolio.chatGpt.dto.ChatReq;
import kr.co.portfolio.chatGpt.dto.ChatRes;
import kr.co.portfolio.chatGpt.exception.ChatgptException;
import kr.co.portfolio.chatGpt.property.ChatgptProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class ChatgptService {

    protected final ChatgptProperties chatgptProperties;

    private final String URL = "https://api.openai.com/v1/completions";

    private final String AUTHORIZATION;

    private final String ORGANIZATION;

    public ChatgptService(ChatgptProperties chatgptProperties) {
        this.chatgptProperties = chatgptProperties;
        AUTHORIZATION = "Bearer " + chatgptProperties.getApiKey();
        ORGANIZATION = chatgptProperties.getOrganization();

    }

    public String sendMessage(String message){
        ChatReq chatReq = new ChatReq(chatgptProperties.getModel(), message
                , chatgptProperties.getTemperature(), chatgptProperties.getMaxTokens(), chatgptProperties.getTopP());
        ChatRes chatRes =  sendChatRequest(chatReq);
        try {
            return chatRes.getChoices().get(0).getText();
        }catch (Exception e){
            log.error("parse chatgpt message error", e);
            throw e;
        }
    }

    public ChatRes sendChatRequest(ChatReq chatReq){
        return this.getResponse(this.buildHttpEntity(chatReq));
    }

    public HttpEntity<ChatReq> buildHttpEntity(ChatReq chatReq){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", AUTHORIZATION);
        headers.add("OpenAI-Organization", ORGANIZATION);
        return new HttpEntity<>(chatReq, headers);
    }

    public ChatRes getResponse(HttpEntity<ChatReq> chatReqHttpEntity) {
        log.info("request url : {}, httpEntity : {}", URL, chatReqHttpEntity);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<ChatRes> responseEntity = restTemplate.postForEntity(URL, chatReqHttpEntity, ChatRes.class);
        if(responseEntity.getStatusCode().isError()){
            log.error("error response status : {}", responseEntity);
            throw new ChatgptException("error response status : " + responseEntity.getStatusCode().value());
        } else {
            log.info("response : {}", responseEntity);
        }
        return responseEntity.getBody();
    }
}
