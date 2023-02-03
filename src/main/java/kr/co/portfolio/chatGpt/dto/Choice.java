package kr.co.portfolio.chatGpt.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Choice {
    private String text;
    private Integer index;
    private LogProbResult logprobs;

    @JsonProperty("finish_reason")
    private String finishReason;
}
