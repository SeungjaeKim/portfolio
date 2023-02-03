package kr.co.portfolio.chatGpt.dto;

import lombok.Data;

import java.util.List;

@Data
public class ChatRes {

    private String id;

    private String object;

    private Long created;

    private String model;

    private List<Choice> choices;

    private Usage usage;

}
